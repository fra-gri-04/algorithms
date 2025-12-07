package MoneyChanger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * 
 */
public class MoneyChanger implements Iterable<ExchangeRate> {
    private final CashBox cashBox;

    private List<ExchangeRate> exchangeRates;

    /**
     * Initialize a MoneyChanger by creating an empty {@link CashBox} and an
     * empty table of {@link ExchangeRate}, initializing them all to null.
     */
    public MoneyChanger() {
        this.cashBox = new CashBox();
        this.exchangeRates = new ArrayList<ExchangeRate>();
    }

    /**
     * Receives an amount to be converted into {@code currency} and withdraws it
     * from the {@code cashBox} if possible, readding the amount.
     * If the currencies match, throws error.
     * If the exchangeRates table doesn't contain the right rate, throws error.
     * If either of the parameters are null, throws NullPointerException
     * 
     * @param fromAmount amount inserted to convert.
     * @param currency   target currency to withdraw.
     * @return the amount in the wanted currency.
     */
    public Amount convert(Amount fromAmount, Currency currency) {
        Objects.requireNonNull(fromAmount, "Amount Must be non null");
        Objects.requireNonNull(currency, "Currency Must be non null");

        if (fromAmount.currency() == currency) {
            System.out.println("ERROR: Unable to convert to same currency");
            return null;
        }

        ExchangeRate rate = getRate(fromAmount.currency(), currency);

        if (rate == null) {
            System.out.println("ERROR: Unavailable Exchange Rate");
            return null;
        }

        int new_cents = (fromAmount.cents() * rate.to().cents()) / 100;
        Amount converted = new Amount(new_cents, currency);

        // checks if wanted quantity is contained in cashBox
        if (cashBox.howMuch(currency).compareTo(converted) < 0) {
            System.out.println("ERROR: Insufficient funds");
            return null;
        }

        cashBox.deposit(fromAmount);
        cashBox.withdraw(converted);
        return converted;
    }

    /**
     * Puts the rate in the correct position of the exchangeRates table, at the from
     * row and the to column, overwriting the previous value.
     * 
     * @param rate to add.
     * @throws NullPointerException if rate is null.
     */
    public void insertExchangeRate(ExchangeRate rate) {
        Objects.requireNonNull(rate, "ExchangeRate must be non null");

        // inserts at the right position the current exchange rate
        int i = 0;
        boolean toAdd = true;
        for (ExchangeRate checkedRate : exchangeRates) {
            if (checkedRate.from().currency() == rate.from().currency() &&
                    checkedRate.to().currency() == rate.to().currency()) {
                exchangeRates.remove(i);
                exchangeRates.add(rate);
                toAdd = false;
                break;
            }
            i++;
        }

        if (toAdd)
            exchangeRates.add(rate);
    }

    /**
     * Searches for a rate with corresponding currencies
     * Returns null if not found
     * 
     * @param from base currency
     * @param to   quote currency
     * @return the rate or null if not found
     */
    private ExchangeRate getRate(Currency from, Currency to) {
        for (ExchangeRate rate : exchangeRates){
            if (rate.from().currency() == from && rate.to().currency() == to)
                return rate;
            // if it hasn't found the exchangeRate in the correct order, search for the opposite too:
            if (rate.to().currency() == from && rate.from().currency() == to)
                return rate.reverse();
        }
        return null;
    }

    /**
     * Gives the cashBox
     * @return the cashBox;
     */
    public CashBox cashBox(){
        return cashBox;
    }


    @Override
    public String toString() {
        String res = "MoneyChanger: {\n\tExchange Rates: [\n";
        if (exchangeRates.size() == 0)
            res += ",\n";
        
        for (ExchangeRate rate : this)
            res += "\t\t"+rate.toString()+"\n";

        res += "\t],\n\t"+ cashBox.toString()+"\n}";
        return res;
    }

    @Override
    public Iterator<ExchangeRate> iterator() {
        return exchangeRates.iterator();
    }
}

/**
 * Exchange Rates
 * <p>
 * Stored in a table, with indices of the currencies
 * <p>
 * For example, for Currencies = [USD, EUR]
 * <table>
 * <tr>
 * <td></td>
 * <td>USD</td>
 * <td>EUR</td>
 * </tr>
 * <tr>
 * <td>USD</td>
 * <td>0</td>
 * <td>0</td>
 * </tr>
 * <tr>
 * <td>EUR</td>
 * <td>0.8</td>
 * <td>0</td>
 * </tr>
 * </table>
 * <p>
 * This means that 1 eur corresponds to 0.8 usd
 */
// previous implementation
// private Map<Currency, HashMap<Currency, ExchangeRate>> exchangeRates;