package ua.foxminded.nikasgig.integerdivisiongt;

public class Calculator {
    CalculationResult calculationResult = new CalculationResult();

    public String createLongDivision(int dividend, int divisor) {

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        try {
            if (divisor == 0) {
                throw new CalculatorException("Divisor can't be 0");
            }
            if (dividend < divisor) {
                throw new CalculatorException("Dividend can't be less than divisor");
            }
        } catch (CalculatorException e) {
            System.out.println(e.getMessage());
        }
        
        String[] digits = splitDividend(dividend);
        int reminderNumber;
        int multiplyResult;
        int mod;
        for (int i = 0; i < digits.length; i++) {
            calculationResult.reminder.append(digits[i]);
            reminderNumber = Integer.parseInt(calculationResult.reminder.toString());
            if (reminderNumber >= divisor) {
                mod = reminderNumber % divisor;
                multiplyResult = reminderNumber / divisor * divisor;
                String lastReminder = String.format("%" + (i + 2) + "s", "_" + reminderNumber);
                calculationResult.result.append(lastReminder).append("\n");
                String multiply = String.format("%" + (i + 2) + "d", multiplyResult);
                calculationResult.result.append(multiply).append("\n");
                Integer tab = lastReminder.length() - calculateDigit(multiplyResult);
                calculationResult.result.append(createDivisor(multiplyResult, tab)).append("\n");
                calculationResult.quotient.append(reminderNumber / divisor);
                calculationResult.reminder.replace(0, calculationResult.reminder.length(), Integer.toString(mod));
                reminderNumber = Integer.parseInt(calculationResult.reminder.toString());
            }
            if (i == digits.length - 1) {
                calculationResult.result.append(String.format("%" + (i + 2) + "s", reminderNumber)).append("\n");
            }
        }
        modifyResultView(dividend, divisor);
        String cloneResult = calculationResult.result.toString();
        calculationResult.cleanData();
        return cloneResult;
    }

    private String[] splitDividend(int dividend) {
        return String.valueOf(dividend).split("");
    }

    private String createDivisor(Integer multiplyResult, Integer tab) {
        return assemblyString(tab, ' ') + assemblyString(calculateDigit(multiplyResult), '-');
    }

    private int calculateDigit(int i) {
        return (int) Math.log10(i) + 1;
    }

    private String assemblyString(int numberOfSymbols, char symbol) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < numberOfSymbols; i++) {
            string.append(symbol);
        }
        return string.toString();
    }

    private void modifyResultView(Integer dividend, Integer divisor) {
        int[] index = new int[3];
        for (int i = 0, j = 0; i < calculationResult.result.length(); i++) {
            if (calculationResult.result.charAt(i) == '\n') {
                index[j] = i;
                j++;
            }
            if (j == 3) {
                break;
            }
        }
        int tab = calculateDigit(dividend) + 1 - index[0];
        calculationResult.result.insert(index[2],
                assemblyString(tab, ' ') + "|" + calculationResult.quotient.toString());
        calculationResult.result.insert(index[1],
                assemblyString(tab, ' ') + "|" + assemblyString(calculationResult.quotient.length(), '-'));
        calculationResult.result.insert(index[0], "|" + divisor);
        calculationResult.result.replace(1, index[0], dividend.toString());
    }
}
