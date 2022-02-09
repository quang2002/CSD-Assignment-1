package product_management;

import linked_list.Node;

import java.io.BufferedReader;
import java.io.FileWriter;

import linked_list.LinkedList;
import utilities.Input;

public class ProductList extends LinkedList<Product> {

    public boolean loadDataFromFile(String filename) {
        try {
            try (BufferedReader br = new BufferedReader(new java.io.FileReader(filename))) {
                clear();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split("\\|");

                    if (data.length == 5) {
                        addTail(new Product(
                                data[0].trim(),
                                data[1].trim(),
                                Integer.parseInt(data[2].trim()),
                                Integer.parseInt(data[3].trim()),
                                Double.parseDouble(data[4].trim())));
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
                Node<Product> it = getHead();
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

    public void display() {
        System.out.printf("%6s | %20s | %10s | %6s | %s\n", "Code", "Name", "Quantity", "Saled", "Price");
        System.out.println(this);
    }

    public Node<Product> findProductByCode(String code) {
        Node<Product> it = this.getHead();

        while (it != null) {
            if (it.getData().getCode().equals(code)) {
                return it;
            }
            it = it.getNext();
        }

        return null;
    }

    public boolean removeProductByCode(String code) {
        return remove(findProductByCode(code));
    }

    public boolean removeNodeAfterCode(String code) {
        return remove(findProductByCode(code).getNext());
    }

    public void sortByCode() {
        sort((a, b) -> a.getCode().compareTo(b.getCode()));
    }

    public void addProduct() {
        Product product = new Product();

        product.setCode(Input.getString("Enter product code: ", (code) -> {
            if (this.findProductByCode(code) != null) {
                System.out.println("Product code already exists.");
                return false;
            }

            System.out.println("Product code is valid.");
            return true;
        }));

        product.setName(Input.getString("Enter product name: "));

        product.setQuantity(Input.getInteger("Enter product quantity: ", (quantity) -> {
            if (quantity < 0) {
                System.out.println("Product quantity must be greater than 0.");
                return false;
            }

            System.out.println("Product quantity is valid.");
            return true;
        }));

        product.setSaled(Input.getInteger("Enter product saled: ", (saled) -> {
            if (saled < 0) {
                System.out.println("Product saled must be greater than 0.");
                return false;
            }

            if (saled > product.getQuantity()) {
                System.out.println("Product saled must be less than or equal to product quantity.");
                return false;
            }

            System.out.println("Product saled is valid.");
            return true;
        }));

        product.setPrice((double) Input.getFloat("Enter product price: ", (price) -> {
            if (price < 0) {
                System.out.println("Product price must be greater than 0.");
                return false;
            }

            System.out.println("Product price is valid.");
            return true;
        }));

        this.addTail(product);
    }

    @Override
    public String toString() {
        return reduce((result, value) -> result + value.toString() + "\n", "");
    }
}
