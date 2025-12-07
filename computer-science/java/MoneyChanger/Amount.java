package MoneyChanger;

import java.util.Objects;

/**
 * Describes an amount by its value in cents and its currency.
 */
public record Amount(int cents, Currency currency) implements Comparable<Amount> {
    /*
     * AF:
     * The value of the amount is represented in cents, so 1 unit is 100 cents.
     * The currency is an element of the enum Currency and has a symbol and a name
     * Operations between amounts are possible only with the same currency.
     * 
     * RI:
     * Currency is an istance of the currency enum
     * 
     */

    /**
     * Creates a new Amount using <b>cents</b>, in order to avoid using floats or
     * doubles to represent values. This means that 1 unit is 100 cents.
     * 
     * @param cents    value of the amount expressed in cents.
     * @param currency one of the registered currencies in {@link Currency}.
     * @throws IllegalArgumentException if currency is null.
     */
    public Amount(int cents, Currency currency) {
        if (currency == null)
            throw new IllegalArgumentException("Currency must be non null");
        this.cents = cents;
        this.currency = currency;
    }

    /**
     * Creates an amount from a string of the format:
     * <p>
     * <Currency Symbol><Value> like: €123.45 -> Amount(12345, Currency.Euro)
     * @param string
     * @return
     */
    public static Amount valueOf(String string){
        char currencySymbol = string.charAt(0);

        Currency currency = Currency.valueOf(currencySymbol);

        string = string.substring(1);
        
        String[] values = string.split("\\.");
        
        int cents = 0;
        if (values.length == 1)
            cents = Integer.parseInt(string) * 100;
        // string has decimal values
        if (values.length > 1)
            cents += Integer.parseInt(values[0]) * 100 + Integer.parseInt(values[1]);

        return new Amount(cents, currency);
    }

    // METHODS:

    /**
     * Adds the two amounts as long as they are of the same currency
     * 
     * @param amountToAdd the amount to add to this
     * @throws IllegalArgumentException if currencies don't match
     * @returns the sum of the values of the two amounts as a new Amount
     */
    public Amount add(Amount amountToAdd) {
        if (this.currency != amountToAdd.currency)
            throw new IllegalArgumentException("The currencies of the amounts don't match.");
        return new Amount(this.cents + amountToAdd.cents, this.currency);
    }

    /**
     * Calls the add method with the opposite number of cents, in order to
     * substract the amountToSub from this amount.
     * this.cents + (-amountToSub.cents)
     * 
     * Note that this method doesn't update this.cents, as this is a record
     * It just returns the result.
     * 
     * @param amountToSub amount to subtract from this.
     * @return the difference between this.cents and the other amount's cents.
     */
    public Amount sub(Amount amountToSub) {
        return add(new Amount(-amountToSub.cents, amountToSub.currency));
    }

    @Override
    public int compareTo(Amount other) {
        /*
         * 1 if this > other
         * 0 if this == other
         * -1 if this < other
         */
        if (this.currency != other.currency)
            throw new IllegalArgumentException("The currencies of the amounts don't match.");

        return Integer.compare(this.cents, other.cents);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof Amount other) {
            // System.out.println(this.cents + "," + other.cents + "-" + this.currency + ","
            // + other.currency + "->"
            // + (this.cents == other.cents && this.currency == other.currency));
            return this.cents == other.cents && this.currency == other.currency;
        }

        return false;
    }


    @Override
    public final int hashCode() {
        return Objects.hash(cents, currency);
    }

    @Override
    public final String toString() {
        String res = String.valueOf(currency.symbol) + cents / 100 + ".";
        int decimal = cents % 100;

        if (decimal == 0)
            res += "00";
        else if (decimal < 10)
            res += decimal + "0";
        else
            res += decimal;

        return res;
    }
}
