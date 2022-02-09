package product_management;

import java.io.BufferedReader;
import java.io.FileWriter;

import linked_list.LinkedList;
import linked_list.Node;
import utilities.Input;

public class OrderList extends LinkedList<Order> {
    public OrderList() {

    }

    public boolean loadDataFromFile(String filename) {
        try {
            try (BufferedReader br = new BufferedReader(new java.io.FileReader(filename))) {
                clear();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split("\\|");

                    if (data.length == 3) {
                        addTail(new Order(data[0].trim(), data[1].trim(), Integer.parseInt(data[2])));
                    }
                }
            }
        } catch (java.io.IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean saveDataToFile(String filename) {
        try {
            try (FileWriter fw = new FileWriter(filename)) {
                Node<Order> it = getHead();
                while (it != null) {
                    fw.write(it.getData().toString() + "\n");
                    it = it.getNext();
                }
            }
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void addOrder(ProductList productList, CustomerList customerList) {
        Order order = new Order();

        order.setCcode(Input.getString("Enter customer code: ", (value) -> {
            if (customerList.findCustomerByCode(value) == null) {
                System.out.println("Customer code is not exist");
                return false;
            }
            return true;
        }));

        order.setPcode(Input.getString("Enter product code: ", (value) -> {
            if (productList.findProductByCode(value) == null) {
                System.out.println("Product code is not exist");
                return false;
            }
            return true;
        }));

        order.setQuantity(Input.getInteger("Enter quantity: ", (value) -> {
            if (value <= 0) {
                System.out.println("Quantity must be greater than 0");
                return false;
            }

            if (productList.findProductByCode(order.getPcode()).getData().getQuantity() < value) {
                System.out.println("Quantity is greater than available quantity");
                return false;
            }
            return true;
        }));

        this.addTail(order);
    }

    public void display() {
        System.out.printf("%6s | %6s | %6s | %6s\n", "Customer", "Product", "Quantity", "Price");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return reduce((result, value) -> result + value.toString() + "\n", "");
    }

    public void sort() {
        sort((a, b) -> {
            if (a.getCcode().equals(b.getCcode())) {
                return a.getPcode().compareTo(b.getPcode());
            }
            return a.getCcode().compareTo(b.getCcode());
        });
    }
}
