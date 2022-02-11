import product_management.CustomerList;
import product_management.Order;
import product_management.OrderList;
import product_management.Product;
import product_management.ProductList;

public class Ordering {
    OrderList orderList = new OrderList();
    ProductList productList = new ProductList();
    CustomerList customerList = new CustomerList();

    public void loadFiles(String productFile, String customerFile) {
        productList.loadDataFromFile(productFile);
        customerList.loadDataFromFile(customerFile);
    }

    // sell
    public void sell(String pcode, String ccode, int quantity) {
        if (productList.findProductByCode(pcode) == null) {
            System.out.println("Product code is not exist");
            return;
        }

        if (customerList.findCustomerByCode(ccode) == null) {
            System.out.println("Customer code is not exist");
            return;
        }

        Product product = productList.findProductByCode(pcode).getData();

        if (product.getSaled() >= product.getQuantity()) {
            return;
        }

        if (product.getQuantity() < quantity) {
            System.out.println("Not enough quantity");
            return;
        }

        orderList.addTail(new Order(pcode, ccode, quantity));
        product.setSaled(product.getSaled() + quantity);
    }

    // display
    public void displayProduct() {
        productList.display();
    }

    public void displayOrdering() {
        orderList.display();
    }
}