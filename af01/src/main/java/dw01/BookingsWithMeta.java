package dw01;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingsWithMeta
{
  private Meta meta;
  private ArrayList<Booking> bookings;

  public BookingsWithMeta( Meta metaBlock, ArrayList<Booking> bookingsA )
  {
    meta = metaBlock;
    bookings = bookingsA;
  }

  @JsonProperty
  public Meta getMeta()
  {
    return meta;
  }

  @JsonProperty
  public ArrayList<Booking> getBookings()
  {
    return bookings;
  }
}
