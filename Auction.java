import java.util.ArrayList;
import java.util.Iterator;
/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {   //creating an object and we are adding it to the arrayList
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }

    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            Bid bid = new Bid(bidder, value);
            boolean successful = selectedLot.bidFor(bid);
            // OR
            //boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                    lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                    " already has a bid of: " +
                    highestBid.getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        if((lotNumber >= 1) && (lotNumber < nextLotNumber)) {
            // The number seems to be reasonable.
            Lot selectedLot = lots.get(lotNumber - 1); //how would you change it so it doesn't depend on the index
            // Include a confidence check to be sure we have the
            // right lot.
            if(selectedLot.getNumber() != lotNumber) { //incase of free memory
                System.out.println("Internal error: Lot number " +
                    selectedLot.getNumber() +
                    " was returned instead of " +
                    lotNumber);
                // Don't return an invalid lot.
                selectedLot = null;
            }
            return selectedLot;
        }
        else {
            System.out.println("Lot number: " + lotNumber +
                " does not exist.");
            return null;
        }
    }

    /**
     * Any lot that has atlest one bid for it is considered to be sold. so we are looking for lots 
     * whose hightest bid isn't null
     */
    public void close(){
        for(Lot lot : lots){
            if(lot.getHighestBid()!= null){
                System.out.println("**************************");
                //so we've to print the names of the sucessful bidder
                System.out.println("Name of winning bidder: "+lot.getHighestBid().getBidder().getName());
                System.out.println("Value of winning bidder: "+lot.getHighestBid().getValue());
                System.out.println("**************************");
            }else{
                System.out.println("lot "+lot.getNumber()
                +" has no bid");
            }
        }
    }

    /**
     * Returns the lists of unsold elements
     */
    public ArrayList<Lot> getUnSold(){
        ArrayList <Lot> unSold = new ArrayList<>(); //creating an object. we've to always create an object
        if(nextLotNumber >= 1){ //checking ot see if there's atleast an item in the list
            for(Lot lot : lots){
                if(lot.getHighestBid()== null){ // that means it has no bidder to it
                    unSold.add(lot);

                }
            }
            return unSold;
        }else{
            return null;
        }
    }

    /**
     * Remove lot with the given lot number
     */
    public Lot removeLot(int number){

        Lot temp=null;
        Iterator<Lot> iter = lots.iterator();
        boolean test = false; //temperary variable to see if we found it
        if(number >= 1 && number <nextLotNumber){ //valid check
            //while loop is better in this case not a for each loop
            while(iter.hasNext() && !test){ 
                temp = iter.next();
                if(temp.getNumber() == number){
                    iter.remove();
                    test =true; //found it
                }
            }
            if(test){
                return temp;
            }else{
                return null;
            }
        }
        return null;
    }
    
    /**
     * the new version does not depend on the index of the lot. it just searches for the lot and returns it if it finds it
     * else returns null
     */
    public Lot getLotNewVersion(int number){
        int index = 0;
        //boolean found = false;
        if(number >= 1 && number <nextLotNumber){ //valid check before we do anything
            while(index < lots.size()){
                if(lots.get(index).getNumber() == number){
                    //found = true;//we've found it stop searching
                    return lots.get(index);
                  
                }
                index++;
            }
        }
        return null;
    }
}
 