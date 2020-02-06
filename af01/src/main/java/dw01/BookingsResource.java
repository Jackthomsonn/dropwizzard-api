package dw01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/bookings" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class BookingsResource
{
  ArrayList<Booking> bookings = new ArrayList<Booking>();
  Map<Long, Booking> bookingMap = new HashMap<>();
  private final AtomicLong counter;

  public BookingsResource()
  {
    this.counter = new AtomicLong();
  }

  @GET
  public BookingsWithMeta getBookings()
  {
    Meta metaBlock = new Meta(
        bookings.size(),
        bookings.stream()
            .map( Booking::getTotalBookings ).max( Long::compare )
            .orElse( 0l ),
        bookings.stream().map( Booking::getTotalBookings ).min( Long::compare )
            .orElse( 0l ) );

    return new BookingsWithMeta( metaBlock, bookings );
  }

  @GET
  @Path( "/{id}" )
  public Booking getBooking( @PathParam( "id" ) long id )
  {
    if ( bookingMap.containsKey( id ) )
    {
      return bookingMap.get( id );
    }

    throw new WebApplicationException(
        "booking not found",
        Response.Status.NOT_FOUND
    );
  }

  @POST
  public Response createBooking( @NotNull @Valid Booking booking )
  {
    if ( isDuplicateEventName( booking ) )
    {
      throw new WebApplicationException(
          "An event with that name already exists. Please choose another event name",
          Response.Status.BAD_REQUEST
      );
    }

    booking.setId( counter.incrementAndGet() );

    bookingMap.put( booking.getId(), booking );
    bookings.add( booking );

    return Response.ok().build();
  }

  private Boolean isDuplicateEventName( Booking booking )
  {
    return bookings.stream()
        .map( Booking::getName )
        .collect( Collectors.toSet() )
        .contains( booking.getName() );
  }
}