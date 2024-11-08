package checkout;


public class Cart {
    private double productPrice;
    private final double GST_RATE = 0.18;


    public Cart(double productPrice) {
        this.productPrice = productPrice;
    }


    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }


    public double calculateTotalPrice() {
        double gstAmount = productPrice * GST_RATE;
        return productPrice + gstAmount;
    }
}
