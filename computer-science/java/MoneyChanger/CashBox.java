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

    /** Map that assigns each currency to the amount contained in the cashbox */
    private Map<Currency, Amount> amountsMap;

    /**
     * Initialize amountsMap in order to have all starting amounts set to zero.
     * <p> It gets the currencies from the {@link Currency} enum.
     * 
     */
    public CashBox() {
        // initialize map
        amountsMap = new HashMap<Currency, Amount>();

        // controllare
        if (Currency.values().length == 0)
            throw new IllegalStateException("Currency enum must be filled.");

        // fills with zeros of all the possible currencies
        for (Currency currency : Currency.values())
            amountsMap.put(currency, Currency.ZERO(currency));
    }

    /**
     * Deposits the amount to the current value of the same currency, updating
     * amountsMap.
     * 
     * @param amount to add to this cashbox
     */
    public void deposit(Amount amount) {
        Objects.requireNonNull(amount, "Amount must not be null.");
        Amount addedAmounts = amountsMap.get(amount.currency()).add(amount);
        amountsMap.put(amount.currency(), addedAmounts);
    }

    /**
     * Withdraws an amount if the cashbox contains a greater value of the same
     * currency.
     * Otherwise, throws IllegalStateException
     * 
     * @param amount to withdraw
     * @throws IllegalStateException if the amount is not contained in the box
     */
    public void withdraw(Amount amount) {
        // compare the amounts of the same currency:
        Amount inside = amountsMap.get(amount.currency());

        // if what is contained is less than the amount to withdraw 
        if (inside.compareTo(amount) < 0)
            throw new IllegalStateException("The amount requested to withdraw is not contained in the cashbox.");

        amountsMap.put(amount.currency(), inside.sub(amount));
    }

    /**
     * Getter for the map of <Currency, Amount>
     * 
     * @return the map of amounts contained in the cashbox
     */
    public Map<Currency, Amount> amountsMap() {
        return Map.copyOf(amountsMap);
    }

    /**
     * Gives how much of a currency is present in the cashbox
     * @param currency to check
     * @return the amount present in the cashBox
     */
    public Amount howMuch(Currency currency){
        return amountsMap.get(currency);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
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
        res = "Cashbox: [\n";

        for (Amount amount : this)
            res += "\t\t"+ amount + ",\n";
        res += "\t]";
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
                while (index < stop && amounts.get(currencies[index]).cents() == 0)
                    index++;
                return index < stop;
            }

            @Override
            public Amount next() {
                if (hasNext()) {
                    Amount next = amounts.get(currencies[index]);
                    index++;
                    return next;
                }
                return null;
            }
        };
    }
}
