package MoneyChanger;

import java.util.HashMap;
import java.util.Map;

/**
 * Saves all the possible currencies accepted by the {@link MoneyChangerMachine}
 */
public enum Currency {
    Dollar("Dollar", '$'),
    Euro("Euro", '€'),
    Franc("Franc", '₣'),
    Lira("Lira", '₺'),
    Rupee("Rupee", '₹'),
    Pound("Pound", '£'),
    Yen("Yen", '¥');

    /** Map of all possible currencies, indicized by char symbol */
    public static Map<Character, Currency> map = new HashMap<>();

    /** name of the currency */
    public final String name;
    /** symbol of the currency */
    public final char symbol;

    /**
     * Creates new currency with a name and a symbol.
     * Name must be non empty
     * 
     * @param name   of the currency
     * @param symbol of the currency
     * @throws IllegalArgumentException if name is empty
     */
    private Currency(String name, char symbol) {
        if (name.isBlank())
            throw new IllegalArgumentException("Currency name must be non empty");
        this.name = name;
        this.symbol = symbol;
    }

    public static Amount ZERO(Currency currency) {
        return new Amount(0, currency);
    }

    static {
        for (Currency c : values()) {
            map.put(c.symbol, c);
        }
    }

    /**
     * Returns the currency corresponding to the given symbol
     * 
     * @param symbol char of the currency
     * @return the corresponding currency
     */
    public static Currency valueOf(char symbol) {
        return Currency.map.get(symbol);
    }

    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }
}
