package products;

public class CosmeticProduct extends Product {
    public CosmeticProduct(String productId, String name, double price, int quantity) {
        super(productId, name, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        return getPrice() * getQuantity();
    }
}

