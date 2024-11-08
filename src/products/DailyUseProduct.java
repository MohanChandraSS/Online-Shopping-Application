package products;

public class DailyUseProduct extends Product {
    public DailyUseProduct(String productId, String name, double price, int quantity) {
        super(productId, name, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        return getPrice() * getQuantity();
    }
}