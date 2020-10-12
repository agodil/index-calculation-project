package godil.InflationCalculator.model;

/**
 * class representing an item
 */
public class ItemValueChange {
    private int id;
    private String itemName;
    private double quantity;
    private double price1;
    private double price2;

    public ItemValueChange(String itemName, double quantity, double price1, double price2) {
        this(-1, itemName, quantity, price1, price2);
    }

    public ItemValueChange(int id, String itemName, double quantity, double price1, double price2) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price1 = price1;
        this.price2 = price2;
    }

    public String getItemName() {
        return itemName;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice1() {
        return price1;
    }

    public double getPrice2() {
        return price2;
    }

    private double calculatePercentChange() {
        return 100 * (price2 - price1) / price1;
    }

    public String getFormatPercentChange() {
        return String.format("%.2f", calculatePercentChange());
    }
}
