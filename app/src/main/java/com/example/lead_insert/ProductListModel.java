package com.example.lead_insert;

public class ProductListModel {
    String product_name;
    String quantity;
    String unit_price;
    String tax;
    String total;


    public ProductListModel(String product_name, String quantity, String unit_price, String tax, String total) {
        this.product_name = product_name;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.tax = tax;
        this.total = total;

    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getProduct_name() {
        return product_name;
    }


    public String getQuantity() {
        return quantity;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public String getTax() {
        return tax;
    }

    public String getTotal() {
        return total;
    }
}
