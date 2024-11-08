package products;

public class ElectronicProduct extends Product {
    private double warrantyPeriod;

    public ElectronicProduct(String productId, String name, double price, int quantity, double warrantyPeriod) {
        super(productId, name, price, quantity);
        this.warrantyPeriod = warrantyPeriod;
    }

    public double getWarrantyPeriod() {
        return warrantyPeriod;
    }

    @Override
    public double calculateTotalPrice() {
        return getPrice() * getQuantity();
    }

    @Override
    public String toString() {
        return super.toString() + ", Warranty Period: " + warrantyPeriod + " years";
    }
}
