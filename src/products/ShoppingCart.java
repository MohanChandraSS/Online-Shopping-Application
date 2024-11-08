package products;

import checkout.Cart;
import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Product> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    //method to add product to the cart
    public void addProduct(String productId, int quantity) {
        for (Product product : Products.availableProducts()) {
            if (product.getProductId().equals(productId)) {
                int totalQuantityInCart = 0;

                for (Product cartItem : cartItems) {
                    if (cartItem.getProductId().equals(productId)) {
                        totalQuantityInCart += cartItem.getQuantity();
                    }
                }
                if (totalQuantityInCart + quantity > product.getQuantity()) {
                    System.out.println("Not enough stock for " + product.getName() + ". Available stock: " + product.getQuantity());
                    return;
                }

                // Add the product to the cart
                Product productToAdd = null;
                if (product instanceof ElectronicProduct) {
                    productToAdd = new ElectronicProduct(productId, product.getName(), product.getPrice(), quantity, ((ElectronicProduct) product).getWarrantyPeriod());
                } else if (product instanceof DailyUseProduct) {
                    productToAdd = new DailyUseProduct(productId, product.getName(), product.getPrice(), quantity);
                } else if (product instanceof CosmeticProduct) {
                    productToAdd = new CosmeticProduct(productId, product.getName(), product.getPrice(), quantity);
                }

                cartItems.add(productToAdd);

                // Decrease the available stock
                product.setQuantity(product.getQuantity() - quantity);
                System.out.println(quantity + " " + product.getName() + " added to cart.");
                return;
            }
        }
        System.out.println("Product with ID " + productId + " not found.");
    }

    //method to remove product to the cart
    public void removeProduct(String productId, int quantityToRemove) {
        Product productToRemove = null;

        for (Product product : cartItems) {
            if (product.getProductId().equals(productId)) {
                productToRemove = product;
                break;
            }
        }

        if (productToRemove != null) {
            if (quantityToRemove >= productToRemove.getQuantity()) {
                // Update the product's stock when removing from cart
                for (Product product : Products.availableProducts()) {
                    if (product.getProductId().equals(productId)) {
                        product.setQuantity(product.getQuantity() + productToRemove.getQuantity());
                        break;
                    }
                }

                cartItems.remove(productToRemove);
                System.out.println("Removed " + productToRemove.getName() + " from the cart.");
            } else {
                int remainingQuantity = productToRemove.getQuantity() - quantityToRemove;

                // Update the product's stock accordingly
                for (Product product : Products.availableProducts()) {
                    if (product.getProductId().equals(productId)) {
                        product.setQuantity(product.getQuantity() + quantityToRemove);
                        break;
                    }
                }

                productToRemove.setQuantity(remainingQuantity);
                System.out.println("Removed " + quantityToRemove + " of " + productToRemove.getName() + " from the cart.");
            }
        } else {
            System.out.println("Product not found in the cart.");
        }
    }

    public double calculateTotalCartValue() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.calculateTotalPrice();
        }
        return total;
    }

    //method to display products in cart
    public void displayCartItems() {
        Cart cart = new Cart(calculateTotalCartValue());
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        }
        else {
            for (Product product : cartItems) {
                System.out.println(product);
            }
            System.out.println("Cart Total: "+calculateTotalCartValue());
            double totalPrice = cart.calculateTotalPrice();
            System.out.printf("Total Price of cart including GST: ₹%.2f%n", totalPrice);
        }
    }

    public void clearCart() {
        cartItems.clear();
    }
}
