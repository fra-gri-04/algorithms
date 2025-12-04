package MoneyChanger;

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

    public final String name;
    public final char symbol;

    /**
     * Creates new currency with a name and a symbol.
     * Name must be non empty
     * @param name of the currency
     * @param symbol of the currency
     * @throws IllegalArgumentException if name is empty
     */
    private Currency(String name, char symbol){
        if (name.isBlank()) throw new IllegalArgumentException("Currency name must be non empty");
        this.name = name;
        this.symbol = symbol;
    }

    public static Amount ZERO(Currency currency){
        return new Amount(0, currency);
    }

    @Override
    public String toString() {
        return name+" ("+symbol+")";
    }
}
