package dw01;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Booking
{
  private long id;
  @NotEmpty
  private String name;
  @Min( 0 )
  private long totalBookings;

  public Booking()
  {
    // jackson constructor
  }

  public Booking( String name, long totalBookings )
  {
    this.name = name;
    this.totalBookings = totalBookings;
  }

  public void setId( long id )
  {
    this.id = id;
  }

  @JsonProperty
  public long getId()
  {
    return id;
  }

  @JsonProperty
  public String getName()
  {
    return name;
  }

  @JsonProperty
  public long getTotalBookings()
  {
    return totalBookings;
  }
}
