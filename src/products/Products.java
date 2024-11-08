package products;

import java.util.ArrayList;

public class Products {
    private static ArrayList<ElectronicProduct> electronicProducts = new ArrayList<>();
    private static ArrayList<DailyUseProduct> dailyUseProducts = new ArrayList<>();
    private static ArrayList<CosmeticProduct> cosmeticProducts = new ArrayList<>();

    static {
        // Initializing products
        electronicProducts.add(new ElectronicProduct("P001", "Smartphone", 49999.99, 10, 2));
        electronicProducts.add(new ElectronicProduct("P002", "Laptop", 119999.99, 5, 3));
        dailyUseProducts.add(new DailyUseProduct("P005", "Toothpaste", 59.99, 50));
        cosmeticProducts.add(new CosmeticProduct("P007", "Lipstick", 39.99, 25));
    }

    // Getter methods for accessing copies of each product list
    public static ArrayList<ElectronicProduct> getElectronicProducts() {
        return new ArrayList<>(electronicProducts);
    }

    public static ArrayList<DailyUseProduct> getDailyUseProducts() {
        return new ArrayList<>(dailyUseProducts);
    }

    public static ArrayList<CosmeticProduct> getCosmeticProducts() {
        return new ArrayList<>(cosmeticProducts);
    }

    public static void addProduct(Product product) {
        if (product instanceof ElectronicProduct) {
            electronicProducts.add((ElectronicProduct) product);
        } else if (product instanceof DailyUseProduct) {
            dailyUseProducts.add((DailyUseProduct) product);
        } else if (product instanceof CosmeticProduct) {
            cosmeticProducts.add((CosmeticProduct) product);
        }
    }

    public static boolean removeProduct(String productId) {
        for (Product product : availableProducts()) {
            if (product.getProductId().equals(productId)) {
                if (product instanceof ElectronicProduct) {
                    return electronicProducts.remove(product);
                } else if (product instanceof DailyUseProduct) {
                    return dailyUseProducts.remove(product);
                } else if (product instanceof CosmeticProduct) {
                    return cosmeticProducts.remove(product);
                }
            }
        }
        return false;
    }

    // Consolidates all products for ease of use
    public static ArrayList<Product> availableProducts() {
        ArrayList<Product> allProducts = new ArrayList<>();
        allProducts.addAll(getElectronicProducts());
        allProducts.addAll(getDailyUseProducts());
        allProducts.addAll(getCosmeticProducts());
        return allProducts;
    }
}
