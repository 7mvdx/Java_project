package Project;

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double calculateSubtotal() {
        return price * quantity;
    }

    public String generateReceipt() {
        return "Product: " + name + "\nPrice per unit: $" + price + "\nQuantity sold: " + quantity + "\nSubtotal: $" + calculateSubtotal() + "\n";
    }

    @Override
    public String toString() {
        return name + " | $" + price + " | " + quantity + " | $" + calculateSubtotal();
    }
}