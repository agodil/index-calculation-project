package sample;

import java.util.List;

public class IndexChange {
    private List<ValueChange> valueChanges;
    private double yearsBetween;

    public IndexChange(List<ValueChange> valueChanges, double yearsBetween) {
        this.valueChanges = valueChanges;
        this.yearsBetween = yearsBetween;
    }

    public void setYearsBetween(double yearsBetween) {
        this.yearsBetween = yearsBetween;
    }

    public List<ValueChange> getValueChanges() {
        return valueChanges;
    }

    public double getYearsBetween() {
        return yearsBetween;
    }

    public double indexValue1() {
        double sum = 0;
        for (ValueChange vc: valueChanges) {
            sum += vc.getPrice1() * vc.getQuantity();
        }
        return sum;
    }

    public double indexValue2() {
        double sum = 0;
        for (ValueChange vc: valueChanges) {
            sum += vc.getPrice2() * vc.getQuantity();
        }
        return sum;
    }

    public double getPercentChange() {
        return 100 * (indexValue2() - indexValue1()) / indexValue1();
    }

    public double getPercentAverageChange() {
        return 100 * Math.pow((indexValue2() / indexValue1()), 1.0 / yearsBetween) - 1;
    }
}
