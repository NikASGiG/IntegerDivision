package ua.foxminded.nikasgig.integerdivisiongt;

public class CalculationResult {
    public StringBuilder result = new StringBuilder();
    public StringBuilder quotient = new StringBuilder();
    public StringBuilder reminder = new StringBuilder();

    public void cleanData() {
        result = new StringBuilder();
        quotient = new StringBuilder();
        reminder = new StringBuilder();
    }
}
