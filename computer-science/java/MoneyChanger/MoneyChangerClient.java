package MoneyChanger;

import java.util.Scanner;

public class MoneyChangerClient {

    private final static char UPDATE = 'A';
    private final static char CONVERT = 'C';
    private final static char PRINT = 'P';

    public static void main(String[] args) {
        MoneyChanger moneyChanger = new MoneyChanger();

        try (Scanner sc = new Scanner(System.in)){
            System.out.println("Insert values of these currencies inside the cashbox.\nThen an empty line to proceed.");
            // show possible currencies:
            for (Currency c : Currency.values())
                System.out.println(c);
            String line;
            while((line = sc.nextLine()) != null && !line.isEmpty()){               
                moneyChanger.cashBox().deposit(Amount.valueOf(line.trim()));
            }
        System.out.println("Now the Money Changer can operate normally.\n"+UPDATE+" <amount> <amount>: Input of an exchange rate\n"+CONVERT+" <amount> <currency>: Convert amount to another currency\n"+PRINT+" : Prints registered exchange rates and cashbox content ");

            while((line = sc.nextLine()) != null && !line.isEmpty()){
                switch (line.charAt(0)){
                    case UPDATE -> {
                        String[] parts = line.substring(2).split(" = ");
                        Amount from = Amount.valueOf(parts[0]);
                        Amount to = Amount.valueOf(parts[1]);
                        if (from.currency() == to.currency())
                            System.out.println("ERROR: Can't define an exchange rate between the same currency.");
                        else{
                            ExchangeRate newRate = new ExchangeRate(from, to);
                            
                            moneyChanger.insertExchangeRate(newRate);
                        }
                    }
                    case CONVERT ->{
                        String[] parts = line.substring(2).split(" = ");
                        Amount from = Amount.valueOf(parts[0]);
                        Currency currency = Currency.map.get(parts[1].charAt(0));

                        moneyChanger.convert(from, currency);
                    }
                    case PRINT -> {
                        System.out.println(moneyChanger);
                    }
                    default -> {
                        System.out.println("Unknown Command.");
                    }
                }
            }
            
        }
    }
}
