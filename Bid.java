/**
 * A class that models an auction bid.
 * It contains a reference to the Person bidding and the amount bid.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class Bid
{
    // The person making the bid.
    private final Person bidder;
    // The value of the bid. This could be a large number so
    // the long type has been used.
    private final long value;  // a very long integer

    /**
     * Create a bid.
     * @param bidder Who is bidding for the lot. //stores the person's name
     * @param value The value of the bid. // a very long integer
     */
    public Bid(Person bidder, long value)
    {
        this.bidder = bidder;
        this.value = value;
    }

    /**
     * @return The bidder.
     */
    public Person getBidder()
    {
        return bidder;
    }

    /**
     * @return The value of the bid.
     */
    public long getValue()
    {
        return value;
    }
}
