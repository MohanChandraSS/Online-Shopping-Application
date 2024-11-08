package checkout;

abstract class Payment {
    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract void processPayment();
}


class CashOnDelivery extends Payment {
    public CashOnDelivery(double amount) {
        super(amount);
    }

    @Override
    public void processPayment() {
        System.out.println("Payment of ₹" + amount + " will be done via Cash on Delivery.");
    }
}


class UpiPayment extends Payment {
    private String upiId;

    public UpiPayment(double amount, String upiId) {
        super(amount);
        this.upiId = upiId;
    }

    @Override
    public void processPayment() {
        System.out.println("Payment of ₹" + amount + " has been processed via UPI ID: " + upiId);
    }
}


class DebitCardPayment extends Payment {
    private String cardNumber;
    private String pin;
    private String cvv;

    public DebitCardPayment(double amount, String cardNumber, String pin, String cvv) {
        super(amount);
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.cvv = cvv;
    }

    @Override
    public void processPayment() {
        System.out.println("Payment of ₹" + amount + " has been processed via Debit Card.");
        System.out.println("Card Number: " + cardNumber);

    }
}
