import java.util.*;

public class SupermarketBillingSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static ShoppingCart cart = new ShoppingCart();

    public static void main(String[] args) {
        collectCustomerDetails();
        showMenu();
    }

    private static void showMenu() {
        int choice;
        do {
            System.out.println("------ Supermarket Billing System ------");
            System.out.println("1. Add item to cart");
            System.out.println("2. Remove item from cart");
            System.out.println("3. View cart");
            System.out.println("4. Apply discount");
            System.out.println("5. Generate invoice");
            System.out.println("6. Download invoice");
            System.out.println("7. Exit");
            System.out.println("----------------------------------------");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addItemToCart();
                    break;
                case 2:
                    removeItemFromCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    applyDiscount();
                    break;
                case 5:
                    generateInvoice();
                    break;
                case 6:
                    downloadInvoice();
                    break;
                case 7:
                    System.out.println("Thank you for using the Supermarket Billing System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    showMenu();
                    break;
            }
        } while (choice != 7);
    }

    private static void collectCustomerDetails() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer phone number: ");
        String phone = scanner.nextLine();
        cart.setCustomerDetails(name, phone);
        System.out.println("Customer details added!");
    }


    private static void addItemToCart() {
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        Product product = new Product(name, price, quantity);
        cart.addItem(product);

        System.out.println("Item added to cart!");
    }

    private static void removeItemFromCart() {
        System.out.print("Enter item number to remove: ");
        int index = scanner.nextInt();

        if (index >= 1 && index <= cart.getCartSize()) {
            cart.removeItem(index - 1);
            System.out.println("Item removed from cart!");
        } else {
            System.out.println("Invalid item number.");
        }
    }

    private static void viewCart() {
        if (cart.getCartSize() > 0) {
            System.out.println("------ Cart Items ------");
            for (int i = 0; i < cart.getCartSize(); i++) {
                Product item = cart.getItem(i);
                System.out.println((i + 1) + ". " + item.getName() + "\t$" + item.getPrice() + "\tQty: " + item.getQuantity());
            }
            System.out.println("------------------------");
            System.out.println("Total: $" + cart.calculateTotal());
            System.out.println("------------------------");
        } else {
            System.out.println("Cart is empty.");
        }
    }

    private static void applyDiscount() {
        System.out.print("Enter discount amount: ");
        double discount = scanner.nextDouble();
        cart.setDiscount(discount);
        System.out.println("Discount applied!");
    }

    private static void generateInvoice() {
        if (cart.getCartSize() > 0) {
            cart.printInvoice();
            System.out.println("Invoice generated. You can download it now.");
        } else {
            System.out.println("Cart is empty. Cannot generate invoice.");
        }
    }

    private static void downloadInvoice() {
        if (cart.getCartSize() > 0) {
            if (cart.saveInvoiceToFile()) {
                System.out.println("Invoice downloaded successfully.");
                cart = new ShoppingCart();
            } else {
                System.out.println("Error: Invoice download failed.");
            }
        } else {
            System.out.println("Cart is empty. Cannot download invoice.");
        }
    }


}