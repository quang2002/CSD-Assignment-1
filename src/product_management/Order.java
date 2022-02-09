package product_management;

import java.io.Serializable;
import java.util.Locale;

public class Order implements Serializable {
    private String pcode;
    private String ccode;
    private Integer quantity;

    public Order() {

    }

    public Order(String pcode, String ccode, Integer quantity) {
        this.pcode = pcode;
        this.ccode = ccode;
        this.quantity = quantity;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCcode() {
        return ccode;
    }

    public String getPcode() {
        return pcode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%6s | %6s | %d", pcode, ccode, quantity);
    }
}
