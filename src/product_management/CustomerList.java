package product_management;

import java.io.BufferedReader;
import java.io.FileWriter;
import linked_list.LinkedList;
import linked_list.Node;
import utilities.Input;

public class CustomerList extends LinkedList<Customer> {
    public CustomerList() {

    }

    public boolean loadDataFromeFile(String filename) {
        try {
            try (BufferedReader br = new BufferedReader(new java.io.FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split("\\|");

                    if (data.length == 3) {
                        addTail(new Customer(data[0].trim(), data[1].trim(), data[2].trim()));
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
                Node<Customer> it = getHead();
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

    public Node<Customer> findCustomerByCode(String code) {
        Node<Customer> it = this.getHead();
        while (it != null) {
            if (it.getData().getCode().equals(code)) {
                return it;
            }
            it = it.getNext();
        }
        return null;
    }

    public boolean removeByCode(String code) {
        return remove(findCustomerByCode(code));
    }

    public void addCustomer() {
        Customer customer = new Customer();

        customer.setCode(Input.getString("Enter customer code: ", (value) -> {
            if (this.findCustomerByCode(value) != null) {
                System.out.println("Customer code is not unique");
                return false;
            }
            return true;
        }));

        customer.setName(Input.getString("Enter customer name: ", (value) -> {
            if (value.length() < 1) {
                System.out.println("Customer name must be at least 1 character");
                return false;
            }
            return true;
        }));

        customer.setPhone(Input.getString("Enter customer phone: ", "^[0-9]{10}$"));

        this.addTail(customer);
    }

    public void display() {
        System.out.printf("%6s | %20s | %10s", "Code", "Name", "Phone");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return reduce((result, value) -> result + value + "\n", "");
    }
}
