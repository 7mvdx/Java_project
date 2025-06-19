package Project;

import javax.swing.JOptionPane;

public class AppleStoreManagementSystem {
    private static Product[] boughtProducts = new Product[0];
    private static Product[] soldProducts = new Product[0];
    private static int numBoughtProducts = 0;
    private static int numSoldProducts = 0;
    private static double totalRevenue = 0.0;
    private static double totalCosts = 0.0;
    private static boolean isRunning = true;
    private static Reservation[] reservations = new Reservation[0];
    private static int numReservations = 0;

    public static void main(String[] args) {
        displayWelcomeMessage();
        while (isRunning) {
            switch (displayMenu()) {
                case 1:
                    sellProduct();
                    break;
                case 2:
                    viewTotalSales();
                    break;
                case 3:
                    makeReservation();
                    break;
                case 4:
                    buyProduct();
                    break;
                case 5:
                    viewTotalCosts();
                    break;
                case 6:
                    isRunning = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "❌ Invalid choice. Please enter a number between 1 and 6.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        displayOverallReceipt();
    }

    private static void displayWelcomeMessage() {
        JOptionPane.showMessageDialog(null, "🍎 Welcome to the Apple Store Management System 🍎\n\nCome and experience the seamless integration of our products and services!", "Welcome!", JOptionPane.INFORMATION_MESSAGE);
    }

    private static int displayMenu() {
        String choice = JOptionPane.showInputDialog(null, "1. 💻 Sell a product\n2. 💰 View total sales\n3. 🕰️ Make a Genius Bar reservation\n4. 📦 Buy a product\n5. 📉 View total costs\n6. 🚪 Exit & show the overall receipt\nEnter your choice (1-6): ", "Apple Store Management System", JOptionPane.QUESTION_MESSAGE);
        if (choice == null || choice.isEmpty()) return 6; // Return exit option if dialog is closed or input is empty
        try {
            return Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            return 0; // Return 0 for invalid input
        }
    }

    private static void buyProduct() {
        String productName = JOptionPane.showInputDialog(null, "🔍 Enter product name: ", "Buy Product", JOptionPane.QUESTION_MESSAGE);
        if (productName == null || productName.isEmpty()) return; // Return if product name is not provided

        int quantity;
        try {
            quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "📦 Enter quantity to buy: ", "Buy Product", JOptionPane.QUESTION_MESSAGE));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ Invalid quantity format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double pricePerUnit;
        try {
            pricePerUnit = Double.parseDouble(JOptionPane.showInputDialog(null, "💰 Enter price per unit: $", "Buy Product", JOptionPane.QUESTION_MESSAGE));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ Invalid price format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Product product = new Product(productName, pricePerUnit, quantity);
        expandBoughtProductsArray();
        boughtProducts[numBoughtProducts++] = product;

        double totalCost = product.calculateSubtotal();
        totalCosts += totalCost;

        JOptionPane.showMessageDialog(null, "🛒 You bought " + quantity + " units of " + productName + " for a total cost of $" + totalCost, "Purchase Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void expandBoughtProductsArray() {
        if (numBoughtProducts == boughtProducts.length) {
            boughtProducts = java.util.Arrays.copyOf(boughtProducts, boughtProducts.length + 1);
        }
    }

    private static void sellProduct() {
        String productName = JOptionPane.showInputDialog(null, "🔍 Enter product name: ", "Sell Product", JOptionPane.QUESTION_MESSAGE);
        if (productName == null || productName.isEmpty()) return; // Return if product name is not provided

        int quantity;
        try {
            quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "📦 Enter quantity sold: ", "Sell Product", JOptionPane.QUESTION_MESSAGE));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ Invalid quantity format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double pricePerUnit;
        try {
            pricePerUnit = Double.parseDouble(JOptionPane.showInputDialog(null, "💰 Enter price per unit: $", "Sell Product", JOptionPane.QUESTION_MESSAGE));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "❌ Invalid price format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Product product = new Product(productName, pricePerUnit, quantity);
        expandSoldProductsArray();
        soldProducts[numSoldProducts++] = product;

        double totalRevenueAmount = product.calculateSubtotal();
        totalRevenue += totalRevenueAmount;

        JOptionPane.showMessageDialog(null, "💳 You sold " + quantity + " units of " + productName + " for a total revenue of $" + totalRevenueAmount, "Sale Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void expandSoldProductsArray() {
        if (numSoldProducts == soldProducts.length) {
            soldProducts = java.util.Arrays.copyOf(soldProducts, soldProducts.length + 1);
        }
    }

    private static void viewTotalSales() {
        JOptionPane.showMessageDialog(null, "💰 Total Sales: $" + totalRevenue, "Total Sales", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void viewTotalCosts() {
        JOptionPane.showMessageDialog(null, "📉 Total Costs: $" + totalCosts, "Total Costs", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void makeReservation() {
        String customerName = JOptionPane.showInputDialog(null, "🧑‍💻 Enter customer name: ", "Make Reservation", JOptionPane.QUESTION_MESSAGE);
        if (customerName == null || customerName.isEmpty()) return; // Return if customer name is not provided

        String timeSlot = JOptionPane.showInputDialog(null, "⌚ Enter reservation time (HH:mm): ", "Make Reservation", JOptionPane.QUESTION_MESSAGE);
        if (timeSlot == null || timeSlot.isEmpty() || !isValidTimeFormat(timeSlot)) {
            JOptionPane.showMessageDialog(null, "⚠️ Invalid time format. Please enter time in HH:mm format.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Return if the time slot is invalid
        }

        Reservation newReservation = new Reservation(customerName, timeSlot);
        expandReservationsArray();
        reservations[numReservations++] = newReservation;

        JOptionPane.showMessageDialog(null, "📅 Reservation confirmed for " + customerName + " at " + timeSlot, "Reservation Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void expandReservationsArray() {
        if (numReservations == reservations.length) {
            reservations = java.util.Arrays.copyOf(reservations, reservations.length + 1);
        }
    }

    private static boolean isValidTimeFormat(String time) {
        if (time.matches("\\d{2}:\\d{2}")) {
            String[] parts = time.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            return hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60;
        }
        return false;
    }

    private static void displayOverallReceipt() {
        StringBuilder overallReceipt = new StringBuilder("📜 Overall Receipt:\n\n");

        overallReceipt.append("📦 Bought Products:\nName | Price | Quantity | Subtotal\n");
        for (int i = 0; i < numBoughtProducts; i++) {
            overallReceipt.append(boughtProducts[i].toString()).append("\n");
        }

        overallReceipt.append("\n💻 Sold Products:\nName | Price | Quantity | Subtotal\n");
        for (int i = 0; i < numSoldProducts; i++) {
            overallReceipt.append(soldProducts[i].toString()).append("\n");
        }

        double netProfit = totalRevenue - totalCosts;
        overallReceipt.append("\n💰 Total Revenue (Sales): $").append(totalRevenue).append("\n");
        overallReceipt.append("📉 Total Costs (Purchases): $").append(totalCosts).append("\n");
        overallReceipt.append("💵 Net Profit: $").append(netProfit).append("\n\n🗓️ Reservations:\n");
        for (int i = 0; i < numReservations; i++) {
            overallReceipt.append(reservations[i].toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, overallReceipt.toString(), "🧾 Overall Receipt", JOptionPane.INFORMATION_MESSAGE);
    }
}