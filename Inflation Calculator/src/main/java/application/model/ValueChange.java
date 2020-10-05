package application.model;

/**
 * class representing an item
 */
public class ValueChange {
    private int id;
    private String itemName;
    private double quantity;
    private double price1;
    private double price2;

    public ValueChange(String itemName, double quantity, double price1, double price2) {
        this(-1, itemName, quantity, price1, price2);
    }

    public ValueChange(int id, String itemName, double quantity, double price1, double price2) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price1 = price1;
        this.price2 = price2;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice1() {
        return price1;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }

    public double getPrice2() {
        return price2;
    }

    public void setPrice2(double price2) {
        this.price2 = price2;
    }

    public double calculatePercentChange() {
        return 100 * (price2 - price1) / price1;
    }

    public String getFormattedPercentChange() {
        return String.format("%.2f", calculatePercentChange());
    }
}
