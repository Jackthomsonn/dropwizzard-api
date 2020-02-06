package dw01;


public class Meta
{
  private long totalCount;
  private long mostBooked;
  private long leastBooked;

  public Meta( long total, long mostBookedEvent, long leastBookedEvent )
  {
    totalCount = total;
    mostBooked = mostBookedEvent;
    leastBooked = leastBookedEvent;
  }

  public long getTotalCount()
  {
    return totalCount;
  }

  public long getMostBooked()
  {
    return mostBooked;
  }

  public long getLeastBooked()
  {
    return leastBooked;
  }
}
