package moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MoneyCalculator {

    public static void main(String[] args) throws Exception {
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.control();
    }   
    
    private double amount;
    private double exchangeRate;
    private String currency;
    
    private void control() throws Exception{
        input();
        process();
        output();
    }
    
    private void input(){
        System.out.println("Introduce una divisa: ");
        Scanner scanner     = new Scanner(System.in);
        currency = scanner.next();
        System.out.println("Introduzca una cantidad en " + currency + ": ");
        amount       = Double.parseDouble(scanner.next());
    }
    
    private void process() throws IOException{
        exchangeRate = getExchangeRate(currency,"EUR");
    }
    
    private void output(){
        System.out.println(amount + " " + currency + " equivalen a " + amount*exchangeRate + " EUR");
    }
    private static double getExchangeRate(String from, String to) throws IOException{
        URL url = new URL("http://free.currencyconverterapi.com/api/v5/convert?q=" + from + "_" + to + "&compact=ultra&apiKey=54e783e7998e50874768"); 
        //He tenido que añadir mi key personal porque si no no me hubiera dejado acceder a la api la sustituyo a la hora de subirla con XXX
        URLConnection connection = url.openConnection();
        try (BufferedReader reader =
                    new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            String line     = reader.readLine();
            String line1    = line.substring(line.indexOf(to)+6, line.indexOf("}"));
            return Double.parseDouble(line1);
        }
    }
    
    
    
    
}
