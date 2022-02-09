import product_management.Management;
import utilities.Input;
import linked_list.Node;
import product_management.*;

public class Main {

    public static int getOrderMenuChoice() {
        System.out.println("\n\nORDER MENU");
        System.out.println("1. Add order");
        System.out.println("2. Display order with total value");
        System.out.println("3. Sort by product code, if same, sort by customer code");
        System.out.println("4. Back to main menu");

        return Input.getInteger("Enter your choice: ", (choice) -> {
            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice");
                return false;
            }
            return true;
        });
    }

    public static int getCustomerMenuChoice() {
        System.out.println("\n\nCUSTOMER MENU");
        System.out.println("1. Load data from file");
        System.out.println("2. Input & add to the end");
        System.out.println("3. Display data");
        System.err.println("4. Save customer list to file");
        System.out.println("5. Search by customer code");
        System.out.println("6. Delete by customer code");
        System.out.println("7. Back to main menu");

        return Input.getInteger("Enter your choice: ", (choice) -> {
            if (choice < 1 || choice > 7) {
                System.out.println("Invalid choice");
                return false;
            }
            return true;
        });
    }

    public static int getProductMenuChoice() {
        System.out.println("\n\nPRODUCT MENU");
        System.out.println("1. Load data from file");
        System.out.println("2. Input & add to the end");
        System.out.println("3. Display data");
        System.out.println("4. Save product list to file");
        System.out.println("5. Search by product code");
        System.out.println("6. Delete by product code");
        System.out.println("7. Sort by product code");
        System.out.println("8. Add after position k");
        System.out.println("9. Delete the node after the node having code = xCode");
        System.out.println("10. Back to main menu");

        return Input.getInteger("Enter your choice: ", (choice) -> {
            if (choice < 1 || choice > 10) {
                System.out.println("Invalid choice");
                return false;
            }
            return true;
        });
    }

    public static int getMoreChoice() {
        System.out.println("\n\nMORE MENU");
        System.out.println("1. Load data & traverse");
        System.out.println("2. Delete by code & traverse");
        System.out.println("3. Search by code & update & traverse");
        System.out.println("4. Add more products & traverse");
        System.out.println("5. Sort by saled & traverse");
        System.out.println("6. Back to main menu");

        return Input.getInteger("Enter your choice: ", (choice) -> {
            if (choice < 1 || choice > 6) {
                System.out.println("Invalid choice");
                return false;
            }
            return true;
        });
    }

    public static int getMainMenuChoice() {
        System.out.println("\n\nMAIN MENU");
        System.out.println("1. Product");
        System.out.println("2. Customer");
        System.out.println("3. Order");
        System.out.println("4. Exit");
        System.out.println("5. More");

        return Input.getInteger("Enter choice: ", (choice) -> {
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice");
                return false;
            }
            return true;
        });
    }

    public static void main(String[] args) {
        Management management = new Management();

        while (true) {
            int choice = getMainMenuChoice();

            switch (choice) {
                case 1:
                    while (true) {
                        int productChoice = getProductMenuChoice();

                        switch (productChoice) {
                            case 1:
                                management.loadProductFromFile();
                                break;
                            case 2:
                                management.addProduct();
                                break;
                            case 3:
                                management.displayProduct();
                                break;
                            case 4:
                                management.saveProductToFile();
                                break;
                            case 5:
                                management.searchProductByCode();
                                break;
                            case 6:
                                management.removeProduct();
                                break;
                            case 7:
                                management.sortProductByCode();
                                break;
                            case 8:
                                management.addProductAfterPositionK();
                                break;
                            case 9:
                                management.deleteNodeAfterCode();
                                break;
                            case 10:
                                break;
                        }

                        if (productChoice == 10) {
                            break;
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        int customerChoice = getCustomerMenuChoice();

                        switch (customerChoice) {
                            case 1:
                                management.loadCustomerFromFile();
                                break;
                            case 2:
                                management.addCustomer();
                                break;
                            case 3:
                                management.displayCustomer();
                                break;
                            case 4:
                                management.saveCustomerToFile();
                                break;
                            case 5:
                                management.searchCustomerByCode();
                                break;
                            case 6:
                                management.removeCustomer();
                                break;
                            case 7:
                                break;
                        }

                        if (customerChoice == 7) {
                            break;
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        int orderChoice = getOrderMenuChoice();

                        switch (orderChoice) {
                            case 1:
                                management.addOrder();
                                break;
                            case 2:
                                management.displayOrder();
                                break;
                            case 3:
                                management.sortOrder();
                                break;
                            case 4:
                                break;
                        }

                        if (orderChoice == 4) {
                            break;
                        }
                    }
                    break;
                case 5:
                    while (true) {
                        int moreChoice = getMoreChoice();

                        switch (moreChoice) {
                            case 1:
                                management.productList.loadDataFromFile("product1.txt");
                                management.displayProduct();
                                break;
                            case 2:
                                Node<Product> a1 = management.productList.findProductByCode("2");
                                if (a1 == null) {
                                    System.out.println("Product code is not exist");
                                    return;
                                } else {
                                    management.productList.remove(a1);
                                    management.displayProduct();
                                }
                                break;
                            case 3:
                                Node<Product> a2 = management.productList.findProductByCode("3");
                                if (a2 == null) {
                                    System.out.println("Product code is not exist");
                                    return;
                                } else {
                                    a2.getData().setQuantity(9);
                                    management.displayProduct();
                                }
                                break;
                            case 4:
                                management.productList.addTail(new Product("6", "E", 3, 2, 1.0));
                                management.displayProduct();
                                break;
                            case 5:
                                management.productList.sort((a, b) -> a.getSaled() - b.getSaled());
                                management.displayProduct();
                                break;
                        }

                        if (moreChoice == 6) {
                            break;
                        }
                    }
                    break;
                default:
                    return;
            }
        }
    }
}