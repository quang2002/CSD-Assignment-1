package product_management;

import linked_list.Node;
import utilities.Input;

public class Management {
    public ProductList productList = new ProductList();
    public CustomerList customerList = new CustomerList();
    public OrderList orderList = new OrderList();

    public void addProduct() {
        productList.addProduct();
    }

    public void addCustomer() {
        customerList.addCustomer();
    }

    public void addOrder() {
        orderList.addOrder(productList, customerList);
    }

    public void removeProduct() {
        productList.display();

        String productCode = Input.getString("Enter product id to remove: ", (code) -> {
            if (productList.findProductByCode(code) == null) {
                System.out.println("Product not found");
                return false;
            }
            return true;
        });

        if (Input.getBoolean("Are you sure to remove product " + productCode + "? (y/n) ")) {
            productList.remove(productList.findProductByCode(productCode));
            System.out.println("Product removed");
        } else {
            System.out.println("Cancel remove product");
        }
    }

    public void removeCustomer() {
        // display all customer
        customerList.display();

        // get customer code
        String customerCode = Input.getString("Enter customer id to remove: ", (code) -> {
            if (customerList.findCustomerByCode(code) == null) {
                System.out.println("Customer not found");
                return false;
            }
            return true;
        });

        // confirm to remove Customer
        if (Input.getBoolean("Are you sure to remove customer " + customerCode + "? (y/n) ")) {
            customerList.remove(customerList.findCustomerByCode(customerCode));

            // remove all order of customer
            Node<Order> it = orderList.getHead();
            while (it != null) {
                if (it.getData().getCcode().equals(customerCode)) {
                    orderList.remove(it);

                    System.out.println("Order removed: " + it.getData().toString());
                }
                it = it.getNext();
            }

            System.out.println("Customer removed");
        } else {
            System.out.println("Cancel remove customer");
        }
    }

    public void displayOrder() {
        orderList.sort();
        orderList.display();
    }

    public void searchCustomerByCode() {
        String customerCode = Input.getString("Enter customer code: ");

        if (customerList.findCustomerByCode(customerCode) == null) {
            System.out.println("Customer code is not exist");
            return;
        } else {
            System.out.println("Customer found: " + customerList.findCustomerByCode(customerCode).toString());
        }
    }

    public void loadProductFromFile() {
        // input path of file to load
        String path = Input.getString("Enter path of file to load: ");

        // load data from file
        if (productList.loadDataFromFile(path)) {
            System.out.println("Load data from file successfully");
        } else {
            System.out.println("Load data from file failed");
        }
    }

    public void saveProductToFile() {
        // input path of file to save
        String path = Input.getString("Enter path of file to save: ");

        // save data to file
        if (productList.saveDataToFile(path)) {
            System.out.println("Save data to file successfully");
        } else {
            System.out.println("Save data to file failed");
        }
    }

    public void displayProduct() {
        // display all product
        productList.display();
    }

    public void searchProductByCode() {
        // enter product code
        String productCode = Input.getString("Enter product code: ");

        // check if product code is exist
        if (productList.findProductByCode(productCode) == null) {
            System.out.println("Product code is not exist");
            return;
        } else {
            System.out.println("Product found: " + productList.findProductByCode(productCode).toString());
        }
    }

    public void sortProductByCode() {
        productList.sortByCode();
    }

    public void addProductAfterPositionK() {
        // enter position k
        int k = Input.getInteger("Enter position k: ", (position) -> {
            // check if k is in list range
            int size = productList.getSize();
            if (position < 0 || position >= size) {
                System.out.println("Position k is not in list range");
                return false;
            }
            return true;
        });

        // enter product code
        String productCode = Input.getString("Enter product code: ", (code) -> {
            // code must be unique
            if (productList.findProductByCode(code) != null) {
                System.out.println("Product code is not unique");
                return false;
            }

            return true;
        });

        // enter product name
        String productName = Input.getString("Enter product name: ");

        // enter product quantity
        int productQuantity = Input.getInteger("Enter product quantity: ", (quantity) -> {
            // quantity must be greater than 0
            if (quantity <= 0) {
                System.out.println("Product quantity must be greater than 0");
                return false;
            }
            return true;
        });

        // enter product saled
        int productSaled = Input.getInteger("Enter product saled: ", (saled) -> {
            // saled must be greater than 0
            if (saled <= 0) {
                System.out.println("Product saled must be greater than 0");
                return false;
            }

            // saled must be less than quantity
            if (saled > productQuantity) {
                System.out.println("Product saled must be less than quantity");
                return false;
            }
            return true;
        });

        // enter product price
        double productPrice = Input.getFloat("Enter product price: ", (price) -> {
            // price must be positive
            if (price <= 0) {
                System.out.println("Product price must be positive");
                return false;
            }
            return true;
        });

        // add product
        productList.addAfter(productList.nodeAt(k),
                new Product(productCode, productName, productQuantity, productSaled, productPrice));
    }

    public void deleteNodeAfterCode() {
        // enter product code
        String productCode = Input.getString("Enter product code: ", (code) -> {
            // code must be exist
            if (productList.findProductByCode(code) == null) {
                System.out.println("Product code is not exist");
                return false;
            }
            return true;
        });

        // delete node
        productList.remove(productList.findProductByCode(productCode).getNext());
    }

    public void loadCustomerFromFile() {
        // enter path of file to load
        String path = Input.getString("Enter path of file to load: ");

        // load data from file
        if (customerList.loadDataFromFile(path)) {
            System.out.println("Load data from file successfully");
        } else {
            System.out.println("Load data from file failed");
        }
    }

    public void saveCustomerToFile() {
        // enter path of file to save
        String path = Input.getString("Enter path of file to save: ");

        // save data to file
        if (customerList.saveDataToFile(path)) {
            System.out.println("Save data to file successfully");
        } else {
            System.out.println("Save data to file failed");
        }
    }

    public void displayCustomer() {
        // display all customer
        customerList.display();
    }

    public void sortOrder() {
        orderList.sort();
    }
}
