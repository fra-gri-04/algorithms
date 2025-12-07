package MoneyChanger;

import java.util.Objects;

/**
 * Describes an exchange rate by providing two {@link Amount}s: {@code from }
 * and {@code to }
 * <p>
 * The base currency is {@code from} and the quote currency is {@code to}
 * <bold>Note:</bold>The amounts values follow the same rules as any other
 * amount, so the value represent the cents
 * 
 * <p>
 * For example:
 * $1.23 = €1 is represented as:
 * <p>
 * from = Amount(Dollar, 123)
 * to = Amount(Euro, 100)
 * 
 */
public record ExchangeRate(Amount from, Amount to) {

    /**
     * Creates an ExchangeRate, checking if the currencies match and if they are
     * negative.
     * If the base currency value is not equal to 1, converts the two amounts, in
     * order to have a clearer exchange rate.
     * 
     * @param from base currency
     * @param to   quote currency
     * @throws IllegalArgumentException if currencies match or values are not
     *                                  positive.
     * @throws NullPointerException     if amounts are null
     */
    public ExchangeRate(Amount from, Amount to) {
        Objects.requireNonNull(from, "Amount must be non null");
        Objects.requireNonNull(to, "Amount must be non null");

        if (from.cents() <= 0 || to.cents() <= 0)
            throw new IllegalArgumentException("Exchange Rates' amount values must be positive.");

        if (from.currency() == to.currency())
            throw new IllegalArgumentException("Currencies must not match");

        // converts the exchange rate in order to have the base currency equal to 1

        if (from.cents() == 100) {
            this.from = from;
            this.to = to;
            return;
        } else if (to.cents() == 100) {
            this.from = to;
            this.to = from;
        } else {
            // from : to = 1 : x
            int normalizedCents = (int) (((float) (to.cents()) / (float) (from.cents())) * 100);

            this.from = new Amount(100, from.currency());
            this.to = new Amount(normalizedCents, to.currency());
        }
    }

    /**
     * Reverse the exchange rate, providing the corresponding quote currency if you
     * have the base as the quote.
     * <p>
     * For example:
     * <p>
     * with rate = {€100, $123}
     * <p>
     * rate.reverse = {$100, €81}
     * 
     * @return the reversed exchangeRate
     */
    public ExchangeRate reverse() {
        // new rate has from = 100 and to equal to
        int reverseCents = (int) (((float) (from.cents()) / (float) (to.cents())) * 100);
        return new ExchangeRate(new Amount(100, from.currency()), new Amount(reverseCents, to.currency()));
    }

    @Override
    public final boolean equals(Object obj) {
        Objects.requireNonNull(obj);
        if (obj == this)
            return true;
        if (obj instanceof ExchangeRate other) {
            return from.equals(other.from()) && to.equals(other.to());
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(from.hashCode(), to.hashCode());
    }

    @Override
    public final String toString() {
        return from.toString() + " = " + to.toString();
    }
}
