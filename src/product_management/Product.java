package product_management;

import java.io.Serializable;

public class Product implements Serializable {
    private String code;
    private String name;
    private Integer quantity;
    private Integer saled;
    private Double price;

    public Product() {

    }

    public Product(String code, String name, Integer quantity, Integer saled, Double price) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.saled = saled;
        this.price = price;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setSaled(Integer saled) {
        this.saled = saled;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getSaled() {
        return saled;
    }

    @Override
    public String toString() {
        return String.format("%6s | %20s | %6d | %6d | %f", code, name, quantity, saled, price);
    }
}
