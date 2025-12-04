package MoneyChanger;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A chest of {@link Amount}s of the currencies registered in {@link Currency}
 * Provides methods to withdraw and to fill up the chest.
 */
public class CashBox implements Iterable<Amount> {

    /*
     * AF:
     * Has a map of all the different currencies available and their values,
     * accessible by the currency.
     * Implements an iterator which skips the currencies whom value is zero.
     * 
     * RI:
     * 
     */

    private Map<Currency, Amount> amountsMap;

    /**
     * Initialize amountsMap in order to have all starting amounts set to zero. 
     * It gets the currencies from the Currency enum.
     * 
     */
    public CashBox() {
        // initialize map
        amountsMap = new HashMap<Currency, Amount>();
        
        // controllare:
        if(Currency.values().length == 0) throw new IllegalStateException("Currency enum must be filled.");
        
        // fills with zeros of all the possible currencies 
        for (Currency currency : Currency.values())
            amountsMap.put(currency, Currency.ZERO(currency));
    }

    /**
     * Deposits the amount to the current value of the same currency, updating amountsMap.
     * @param amount to add to this cashbox
     */
    public void deposit(Amount amount){
        Objects.requireNonNull(amount,"Amount must not be null.");
        Amount addedAmounts = amountsMap.get(amount.currency()).add(amount);
        amountsMap.put(amount.currency(), addedAmounts);
    }
    /**
     * Getter for the map of <Currency, Amount>
     * @return
     */
    public Map<Currency, Amount> amountsMap(){
        return Map.copyOf(amountsMap);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof CashBox other)
            return amountsMap.equals(other.amountsMap());
        return false;
    }

    @Override
    public int hashCode() {
        return amountsMap.hashCode();
    }

    @Override
    public String toString() {
        String res;
        res = "Cashbox:\n";

        for (Amount amount : this)
            res += amount+"\n";
        return res.trim();
    }

    // has to be tested
    @Override
    public Iterator<Amount> iterator() {
        return new Iterator<Amount>() {
            private final Map<Currency, Amount> amounts = Map.copyOf(amountsMap);
            private int index = 0;
            private int stop = Currency.values().length;
            private Currency[] currencies = Currency.values();

            @Override
            public boolean hasNext() {
                while(index < stop && amounts.get(currencies[index]).cents() == 0)
                    index++;
                return index < stop;
            }
            @Override
            public Amount next() {
                if (hasNext()){
                    Amount next = amounts.get(currencies[index]);
                    index++;
                    return next;
                }
                return null;
            }
        };
    }
}
