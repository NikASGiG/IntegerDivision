package ua.foxminded.nikasgig.integerdivisiongt;

public class Formatter {

    private int bodyIndex = 0;
    
    public String format(DivisionResult divisionResult) {
        StringBuilder result = addHeadResult(divisionResult);
        result.append(addBodyResult(divisionResult));
        return result.toString();
    }

    private StringBuilder addHeadResult(DivisionResult divisionResult) {
        StringBuilder result = new StringBuilder();
        StringBuilder quorient = new StringBuilder(
                Integer.toString(divisionResult.getDividend() / divisionResult.getDivisor()));
        for (int i = 0; i < divisionResult.getSteps().size(); i++) {
            DivisionStep step = divisionResult.getSteps().get(i);
            if (step.getBeginReminder() >= divisionResult.getDivisor()) {
                result.append("_" + divisionResult.getDividend() + "|" + divisionResult.getDivisor()).append("\n");
                result.append(String.format("%" + (i + 2) + "d", step.getMultiplyResult()));
                int tab = calculateDigit(divisionResult.getDividend()) - calculateDigit(step.getMultiplyResult());
                result.append(assemblyString(tab, ' ') + "|" + assemblyString(quorient.toString().length(), '-'))
                        .append("\n");
                result.append(createDivisor(step.getMultiplyResult(), step.getTab()));
                result.append(assemblyString(tab, ' ') + "|" + quorient.toString()).append("\n");
                setBodyIndex(i+1);
                return result;
            }
            if (i == divisionResult.getSteps().size() - 1) {
                result.append(String.format("%" + (i + 2) + "s", step.getReminder())).append("\n");
                setBodyIndex(i+1);
            }
        }
        return result;
    }

    private StringBuilder addBodyResult(DivisionResult divisionResult) {
        StringBuilder result = new StringBuilder();
        for (int i = getBodyIndex(); i < divisionResult.getSteps().size(); i++) {
            DivisionStep step = divisionResult.getSteps().get(i);
            if (step.getBeginReminder() >= divisionResult.getDivisor()) {
                StringBuilder lastReminder = new StringBuilder(
                        String.format("%" + (i + 2) + "s", "_" + step.getBeginReminder()));
                result.append(lastReminder).append("\n");
                StringBuilder multiply = new StringBuilder(
                        String.format("%" + (i + 2) + "d", step.getMultiplyResult()));
                result.append(multiply).append("\n");
                result.append(createDivisor(step.getMultiplyResult(), step.getTab())).append("\n");
            }
            if (i == divisionResult.getSteps().size() - 1) {
                result.append(String.format("%" + (i + 2) + "s", step.getReminder())).append("\n");
            }
        }
        return result;
    }

    public int getBodyIndex() {
        return bodyIndex;
    }

    public void setBodyIndex(int bodyIndex) {
        this.bodyIndex = bodyIndex;
    }

    private String createDivisor(Integer multiplyResult, Integer tab) {
        return assemblyString(tab, ' ') + assemblyString(calculateDigit(multiplyResult), '-');
    }

    private static int calculateDigit(int i) {
        return Integer.toString(i).length();
    }

    private String assemblyString(int numberOfSymbols, char symbol) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < numberOfSymbols; i++) {
            line.append(symbol);
        }
        return line.toString();
    }

    public static int calculateTab(int i, int reminderNumber, int multiplyResult) {
        return (String.format("%" + (i + 2) + "s", "_" + reminderNumber)).length() - calculateDigit(multiplyResult);
    }
}
