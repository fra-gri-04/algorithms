package MoneyChanger;

public class MoneyChangerClient {
    public static void main(String[] args) {
        Amount zero = Currency.ZERO(Currency.Dollar);
        Amount three = new Amount(300, Currency.Rupee);

        CashBox box = new CashBox();

        box.deposit(zero);
        box.deposit(three);
        box.deposit(three);
        box.deposit(three);

        System.out.println(box);
    }
}
