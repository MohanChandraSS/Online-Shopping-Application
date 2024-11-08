package checkout;

import products.ShoppingCart;

import java.util.Scanner;


public class Checkout {
    public void processCheckout(ShoppingCart shoppingCart) {
        Scanner scanner = new Scanner(System.in);

        Cart cart = new Cart(shoppingCart.calculateTotalCartValue());
        double totalPrice = cart.calculateTotalPrice();
        System.out.println("Total Price of cart including GST: ₹" + totalPrice);
        System.out.println("Please Enter the following details: ");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.print("Enter your contact number: ");
        String contactNumber = scanner.nextLine();

        DeliveryDetails delivery = new DeliveryDetails(name, address, contactNumber);
        delivery.displayDeliveryInfo();

        System.out.println("\nSelect Payment Method:");
        System.out.println("1. Cash on Delivery");
        System.out.println("2. UPI");
        System.out.println("3. Debit Card");
        int choice = scanner.nextInt();

        Payment payment;
        switch (choice) {
            case 1:
                payment = new CashOnDelivery(totalPrice);
                payment.processPayment();
                break;
            case 2:
                scanner.nextLine();
                System.out.print("Enter your UPI ID: ");
                String upiId = scanner.nextLine();
                payment = new UpiPayment(totalPrice, upiId);
                payment.processPayment();
                break;
            case 3:
                scanner.nextLine();
                System.out.print("Enter your Debit Card Number: ");
                String cardNumber = scanner.nextLine();
                System.out.print("Enter your Card PIN: ");
                String pin = scanner.nextLine();
                System.out.print("Enter your CVV: ");
                String cvv = scanner.nextLine();
                payment = new DebitCardPayment(totalPrice, cardNumber, pin, cvv);
                payment.processPayment();
                break;
            default:
                System.out.println("Invalid choice! Please select a valid payment method.");
                scanner.close();
                return;
        }


        shoppingCart.clearCart();

        System.out.println("\nThank you for shopping with us! Your product will be delivered soon.");
    }

    class DeliveryDetails {
        private String name;
        private String address;
        private String contactNumber;


        public DeliveryDetails(String name, String address, String contactNumber) {
            this.name = name;
            this.address = address;
            this.contactNumber = contactNumber;
        }


        public void displayDeliveryInfo() {
            System.out.println("Delivery Details:");
            System.out.println("Name: " + name);
            System.out.println("Address: " + address);
            System.out.println("Contact Number: " + contactNumber);
        }
    }
}
