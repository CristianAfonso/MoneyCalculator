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
    private String currencyFrom;
    private String currencyTo;
    
    public MoneyCalculator(){}
    
    private void control() throws Exception{
        input();
        process();
        output();
    }
    
    private void input() throws IOException{
        System.out.println("Introduzca cantidad: ");
        Scanner scanner = new Scanner(System.in);
        amount          =   Double.parseDouble(scanner.next());
        System.out.println("Introduzca una divisa origen: ");
        currencyFrom    =  scanner.next();
        System.out.println("Introduzca una divisa destino: ");
        currencyTo      =  scanner.next();
    }
    
    private void process() throws IOException{
        exchangeRate = getExchangeRate(currencyFrom,currencyTo);
    }
    
    private void output(){
        System.out.println(amount + " " + currencyFrom  + " equivalen a " + amount*exchangeRate + " " + currencyTo );
    }
    private static double getExchangeRate(String from, String to) throws IOException{
        URL url = new URL("http://free.currencyconverterapi.com/api/v5/convert?q=" + from + "_" + to + "&compact=ultra&apiKey=54e783e7998e50874768"); 
        //He tenido que añadir mi key personal porque si no no me hubiera dejado acceder a la api la sustituyo a la hora de subirla con XXX
        URLConnection connection = url.openConnection();
        try (BufferedReader reader =
                    new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            String line     = reader.readLine();
            String line1    = line.substring(line.indexOf(to)+5, line.indexOf("}"));
            return Double.parseDouble(line1);
        }
    }
}