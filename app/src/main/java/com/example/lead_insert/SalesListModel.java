package com.example.lead_insert;

public class SalesListModel{
    String customer_name;
    String sales_orderdate;
    String sales_orderno;
    String payment;
    String tags;
    String divisions;



    public SalesListModel(String customer_name, String sales_orderdate, String sales_orderno, String payment, String tags, String divisions) {
        this.customer_name = customer_name;
        this.sales_orderdate = sales_orderdate;
        this.sales_orderno = sales_orderno;
        this.payment = payment;
        this.tags = tags;
        this.divisions = divisions;

    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setSales_orderdate(String sales_orderdate) {
        this.sales_orderdate = sales_orderdate;
    }

    public void setSales_orderno(String sales_orderno) {
        this.sales_orderno = sales_orderno;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setDivisions(String divisions) {
        this.divisions = divisions;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getSales_orderdate() {
        return sales_orderdate;
    }

    public String getSales_orderno() {
        return sales_orderno;
    }

    public String getPayment() {
        return payment;
    }

    public String getTags() {
        return tags;
    }

    public String getDivisions() {
        return divisions;
    }
}
