package ua.foxminded.nikasgig.integerdivisiongt;

public class DivisionStep {
    private int reminder;
    private int multiplyResult;
    private int mod;
    private int tab;
    private int beginReminder;

    public DivisionStep() {
    }

    public DivisionStep(int reminder, int multiplyResult, int mod, int tab, int beginReminder) {
        this.reminder = reminder;
        this.multiplyResult = multiplyResult;
        this.mod = mod;
        this.tab = tab;
        this.beginReminder = beginReminder;
    }
    
    public int getBeginReminder() {
        return beginReminder;
    }
    
    public int getReminder() {
        return reminder;
    }

    public int getMultiplyResult() {
        return multiplyResult;
    }

    public int getMod() {
        return mod;
    }

    public int getTab() {
        return tab;
    }
}
