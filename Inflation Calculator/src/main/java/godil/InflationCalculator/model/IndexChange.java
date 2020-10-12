package godil.InflationCalculator.model;

import java.util.List;

public class IndexChange {
    private List<ItemValueChange> itemValueChanges;
    private double yearsBetween;

    /**
     * @param itemValueChanges
     * @param yearsBetween
     */
    public IndexChange(List<ItemValueChange> itemValueChanges, double yearsBetween) {
        this.itemValueChanges = itemValueChanges;
        this.yearsBetween = yearsBetween;
    }

    public List<ItemValueChange> getValueChanges() {
        return itemValueChanges;
    }

    public double getYearsBetween() {
        return yearsBetween;
    }

    public void setYearsBetween(double yearsBetween) {
        this.yearsBetween = yearsBetween;
    }

    /**
     * sums first values
     *
     * @return
     */
    public double indexValue1() {
        double sum = 0;
        for (ItemValueChange vc : itemValueChanges) {
            sum += vc.getPrice1() * vc.getQuantity();
        }
        return sum;
    }

    /**
     * sums second values
     *
     * @return
     */
    public double indexValue2() {
        double sum = 0;
        for (ItemValueChange vc : itemValueChanges) {
            sum += vc.getPrice2() * vc.getQuantity();
        }
        return sum;
    }

    /**
     * calculate change in index
     *
     * @return
     */
    public double getPercentChange() {
        return 100 * (indexValue2() - indexValue1()) / indexValue1();
    }

    /**
     * calculate annual change in index
     *
     * @return
     */
    public double getPercentAverageChange() {
        return 100 * (Math.pow((indexValue2() / indexValue1()), 1.0 / yearsBetween) - 1);
    }
}
