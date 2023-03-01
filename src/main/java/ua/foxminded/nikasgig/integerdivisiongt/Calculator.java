package ua.foxminded.nikasgig.integerdivisiongt;

public class Calculator {

    public DivisionResult createLongDivision(int dividend, int divisor) {
        DivisionResult divisionResult = new DivisionResult(dividend, divisor);
        validate(divisionResult);
        String[] digits = splitDividend(divisionResult.getDividend());
        int reminderNumber = 0;
        int multiplyResult = 0;
        int mod = 0;
        int tab = 0;
        int beginReminder = 0;
        StringBuilder temporaryReminder = new StringBuilder();
        for (int i = 0; i < digits.length; i++) {
            temporaryReminder.append(digits[i]);
            reminderNumber = Integer.parseInt(temporaryReminder.toString());
            beginReminder = reminderNumber;
            if (reminderNumber >= divisionResult.getDivisor()) {
                mod = reminderNumber % divisionResult.getDivisor();
                multiplyResult = reminderNumber / divisionResult.getDivisor() * divisionResult.getDivisor();
                tab = Formatter.calculateTab(i, reminderNumber, multiplyResult);
                temporaryReminder.replace(0, temporaryReminder.length(), Integer.toString(mod));
                reminderNumber = Integer.parseInt(temporaryReminder.toString());
            }
            divisionResult.addSteps(new DivisionStep(reminderNumber, multiplyResult, mod, tab, beginReminder));
        }
        return divisionResult;
    }

    private void validate(DivisionResult divisionResult) {
        if (divisionResult.getDivisor() == 0) {
            throw new CalculatorException("Divisor can't be 0");
        }
        if (divisionResult.getDividend() < divisionResult.getDivisor()) {
            throw new CalculatorException("Dividend can't be less than divisor");
        }
    }

    private String[] splitDividend(int dividend) {
        return String.valueOf(dividend).split("");
    }
}
