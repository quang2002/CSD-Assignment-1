package product_management;

import linked_list.Node;
import utilities.Input;

public class Management {
    ProductList productList = new ProductList();
    CustomerList customerList = new CustomerList();
    OrderList orderList = new OrderList();

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

    public void searchByCCode() {
        String customerCode = Input.getString("Enter customer code: ");

        if (customerList.findCustomerByCode(customerCode) == null) {
            System.out.println("Customer code is not exist");
            return;
        } else {
            System.out.println("Customer found: " + customerList.findCustomerByCode(customerCode).toString());
        }
    }
}
