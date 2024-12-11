import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class ShoppingCart {
    private List<Product> items;
    private double discount;
    private String customerName;
    private String customerPhone;

    public ShoppingCart() {
        items = new ArrayList<>();
        discount = 0.0;
        customerName = "";
        customerPhone = "";
    }

    public void addItem(Product product) {
        items.add(product);
    }

    public void removeItem(int index) {
        items.remove(index);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        total -= discount;
        return total;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setCustomerDetails(String name, String phone) {
        this.customerName = name;
        this.customerPhone = phone;
    }

    public int getCartSize() {
        return items.size();
    }

    public Product getItem(int i) {
        return items.get(i);
    }

    public void printInvoice() {
        System.out.println("----------- INVOICE -----------");
        System.out.println("Customer: " + customerName);
        System.out.println("Phone: " + customerPhone);
        System.out.println("-------------------------------");
        for (int i = 0; i < items.size(); i++) {
            Product item = items.get(i);
            System.out.println((i + 1) + ". " + item.getName() + "\t$" + item.getPrice() + "\tQty: " + item.getQuantity());
        }
        System.out.println("-------------------------------");
        System.out.println("Total: $" + calculateTotal());
        System.out.println("Discount: $" + discount);
        System.out.println("-------------------------------");
    }

    public boolean saveInvoiceToFile() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = LocalDateTime.now().format(formatter);
            String filename = "invoice_" + timestamp + ".txt";

            try (FileWriter writer = new FileWriter(filename)) {
                writer.write("-------------- INVOICE ------------\n");
                writer.write("Customer Name: " + customerName + "\n");
                writer.write("Phone: " + customerPhone + "\n");
                writer.write("-----------------------------------\n");
                for (int i = 0; i < items.size(); i++) {
                    Product item = items.get(i);
                    writer.write((i + 1) + ". " + item.getName() + "\t$" + item.getPrice() + "\tQty: " + item.getQuantity() + "\n");
                }
                writer.write("-------------------------------\n");
                writer.write("Total: $" + calculateTotal() + "\n");
                writer.write("Discount: $" + discount + "\n");
                writer.write("-------------------------------\n");
            }

            System.out.println("Invoice saved to file: " + filename);
            return true;
        } catch (IOException e) {
            System.out.println("Failed to save invoice to file: " + e.getMessage());
            return false;
        }
    }
}
