package product_management;

import java.io.Serializable;
import java.util.Locale;

public class Customer implements Serializable {
    private String code;
    private String name;
    private String phone;

    public Customer() {

    }

    public Customer(String code, String name, String phone) {
        this.code = code;
        this.name = name;
        this.phone = phone;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%6s | %20s | %10s", code, name, phone);
    }
}
