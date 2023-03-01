package ua.foxminded.nikasgig.integerdivisiongt;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Integer Division!");
        boolean isInfinityLoop = false;
        Formatter formatter = new Formatter();
        Calculator calculator = new Calculator();
        do {
            System.out.println("----------");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input your Dividend: ");
            int inputDividend = scanner.nextInt();
            System.out.print("Input your Dividor: ");
            int inputDividor = scanner.nextInt();
            System.out.println("----------");
            System.out.println(formatter.format(calculator.createLongDivision(inputDividend, inputDividor)));
            System.out.println("----------");
            scanner.close();
        } while (isInfinityLoop);
    }
}
