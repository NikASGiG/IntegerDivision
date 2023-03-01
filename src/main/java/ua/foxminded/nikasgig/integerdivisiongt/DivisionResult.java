package ua.foxminded.nikasgig.integerdivisiongt;

import java.util.ArrayList;
import java.util.List;

public class DivisionResult {

    private int dividend;
    private int divisor;
    private List<DivisionStep> steps;

    public DivisionResult(int dividend, int divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
        this.steps = new ArrayList<>();
    }

    public int getDividend() {
        return dividend;
    }

    public void setDividend(int dividend) {
        this.dividend = dividend;
    }

    public int getDivisor() {
        return divisor;
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }

    public List<DivisionStep> getSteps() {
        return steps;
    }

    public void addSteps(DivisionStep step) {
        this.steps.add(step);
    }
}
