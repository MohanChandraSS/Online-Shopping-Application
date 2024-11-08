import java.util.Scanner;
import checkout.*;
import products.*;
import java.util.ArrayList;
import java.util.List;

//Abstraction
public interface OnlineShopping {
    void displayMenu();
}
//Inheritance
class Customer implements OnlineShopping {
    private ShoppingCart shoppingCart;
    private Checkout checkout;
    private Scanner scanner;

    public Customer(ShoppingCart shoppingCart, Checkout checkout, ArrayList<Product> products, Scanner scanner) {
        this.shoppingCart = shoppingCart;
        this.checkout = checkout;
        this.scanner = scanner;
    }

    @Override// method overriding
    public void displayMenu() {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Browse by Product Type");
            System.out.println("2. Add to Cart");
            System.out.println("3. Remove from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Return to Main Menu");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    browseProductsByType();
                    break;
                case 2:
                    System.out.print("Enter Product ID to add: ");
                    String productId = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    shoppingCart.addProduct(productId, quantity);
                    break;
                case 3:
                    removeFromCart();
                    break;
                case 4:
                    shoppingCart.displayCartItems();
                    break;
                case 5:
                    checkout.processCheckout(shoppingCart);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void removeFromCart() {
        System.out.print("Enter Product ID to remove: ");
        String productId = scanner.nextLine();
        System.out.print("Enter quantity to remove: ");
        int quantityToRemove = scanner.nextInt();
        shoppingCart.removeProduct(productId,quantityToRemove);
    }

    private void browseProductsByType() {
        while (true) {
            System.out.println("\nOnline Shopping Menu:");
            System.out.println("1. View Electronic Products");
            System.out.println("2. View Daily Use Products");
            System.out.println("3. View Cosmetic Products");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewElectronicProducts();
                    return;
                case 2:
                    viewDailyUseProducts();
                    return;
                case 3:
                    viewCosmeticProducts();
                    return;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewElectronicProducts() {
        System.out.println("Available Electronic Products:");
        for (ElectronicProduct product : Products.getElectronicProducts()) {
            System.out.println(product);
        }
    }

    private void viewDailyUseProducts() {
        System.out.println("Available Daily Use Products:");
        for (DailyUseProduct product : Products.getDailyUseProducts()) {
            System.out.println(product);
        }
    }

    private void viewCosmeticProducts() {
        System.out.println("Available Cosmetic Products:");
        for (CosmeticProduct product : Products.getCosmeticProducts()) {
            System.out.println(product);
        }
    }


}
//Inheritance
class Admin implements OnlineShopping {
    private final Scanner scanner;

    public Admin(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Update product quantity");
            System.out.println("2. Add new product");
            System.out.println("3. Delete product");
            System.out.println("4. View Inventory");
            System.out.println("5. Return to Main Menu");
            System.out.print("Choice: ");

            int adminChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (adminChoice) {
                case 1:
                    updateProductQuantity();
                    break;
                case 2:
                    addNewProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    viewInventory();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
    private void updateProductQuantity() {
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter new quantity: ");
        int newQuantity = scanner.nextInt();

        for (Product product : Products.availableProducts()) {
            if (product.getProductId().equals(productId)) {
                product.setQuantity(newQuantity);
                System.out.println("Updated quantity of " + product.getName() + " to " + newQuantity);
                return;
            }
        }
        System.out.println("Product with ID " + productId + " not found.");
    }
    private void addNewProduct() {
        System.out.println("Select product type to add:");
        System.out.println("1. Electronic Product");
        System.out.println("2. Daily Use Product");
        System.out.println("3. Cosmetic Product");
        System.out.print("Choice: ");
        int productTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new product ID: ");
        String newProductId = scanner.nextLine();
        System.out.print("Enter product name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter quantity: ");
        int newProductQuantity = scanner.nextInt();

        Product newProduct;
        switch (productTypeChoice) {
            case 1:
                System.out.print("Enter warranty period (years): ");
                double warranty = scanner.nextDouble();
                newProduct = new ElectronicProduct(newProductId, newName, price, newProductQuantity, warranty);
                break;
            case 2:
                newProduct = new DailyUseProduct(newProductId, newName, price, newProductQuantity);
                break;
            case 3:
                newProduct = new CosmeticProduct(newProductId, newName, price, newProductQuantity);
                break;
            default:
                System.out.println("Invalid product type. Product not added.");
                newProduct = null;
                break;
        }

        if (newProduct != null) {
            Products.addProduct(newProduct);
            System.out.println(newProduct.getName() + " has been added to the inventory.");
        }
    }

    private void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        String deleteProductId = scanner.nextLine();

        if (Products.removeProduct(deleteProductId)) {
            System.out.println("Product with ID " + deleteProductId + " has been removed from the inventory.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private void viewInventory() {
        System.out.println("Current inventory:");
        System.out.println("Available Electronic Products:");
        Products.getElectronicProducts().forEach(System.out::println);
        System.out.println("Available Daily Use Products:");
        Products.getDailyUseProducts().forEach(System.out::println);
        System.out.println("Available Cosmetic Products:");
        Products.getCosmeticProducts().forEach(System.out::println);
    }

}

//Main class to run the application
class UserInterface {
    private ShoppingCart shoppingCart;
    private Scanner scanner;

    public UserInterface() {
        shoppingCart = new ShoppingCart();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n----Welcome to Online Shopping Platform!----");
            System.out.println("Are you a customer or an admin?");
            System.out.println("1. Customer");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    Customer customer = new Customer(shoppingCart, new Checkout(), Products.availableProducts(), scanner);
                    customer.displayMenu(); // Show customer menu
                    break;
                case 2:
                    Admin admin = new Admin(scanner);
                    admin.displayMenu(); // Show admin menu
                    break;
                case 3:
                    System.out.println("Exiting the platform. Thank you for visiting!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Exiting system.");
                    break;
            }
        }

    }


    public static void main(String[] args) {
        UserInterface ui = new UserInterface(); //Creating a Object
        ui.start();//Calling the menu method
    }
}