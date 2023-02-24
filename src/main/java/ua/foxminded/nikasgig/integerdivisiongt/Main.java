package ua.foxminded.nikasgig.integerdivisiongt;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Integer Division!");
        boolean isInfinityLoop = false;
        System.out.println("---Test---");
        int exampleDividend = 78945;
        int exampleDivisor = 4;
        Calculator calculator = new Calculator();
        System.out.println(calculator.createLongDivision(exampleDividend, exampleDivisor));
        do {
            System.out.println("----------");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input your Dividend: ");
            int inputDividend = scanner.nextInt();
            System.out.print("Input your Dividor: ");
            int inputDividor = scanner.nextInt();
            System.out.println("----------");
            System.out.println(calculator.createLongDivision(inputDividend, inputDividor));
            System.out.println("----------");
            scanner.close();
        } while (isInfinityLoop);
    }
}
