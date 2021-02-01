package com.example.lead_insert;

public class QuotationListModel {

    String customer_name;
    String quotation_date;
    String quotation_no;
    String payment;
    String tags;
    String divisions;

    public QuotationListModel(String customer_name, String quotation_date, String quotation_no, String payment, String tags, String divisions) {

        this.customer_name = customer_name;
        this.quotation_date = quotation_date;
        this.quotation_no = quotation_no;
        this.payment = payment;
        this.tags = tags;
        this.divisions = divisions;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setQuotation_date(String quotation_date) {
        this.quotation_date = quotation_date;
    }

    public void setQuotation_no(String quotation_no) {
        this.quotation_no = quotation_no;
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

    public String getQuotation_date() {
        return quotation_date;
    }

    public String getQuotation_no() {
        return quotation_no;
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
