package com.example.lead_insert;

import androidx.appcompat.app.AppCompatActivity;

public class OpportunityListModel {
    String Date;
    String opp_description;
    String customer_name;
    String expected_revenue;
    String expected_closingdate;
    String employee_name;



    public OpportunityListModel(String date, String opp_description, String customer_name, String expected_revenue, String expected_closingdate, String employee_name) {
        this.Date = date;
        this.opp_description = opp_description;
        this.customer_name = customer_name;
        this.expected_revenue = expected_revenue;
        this.expected_closingdate = expected_closingdate;
        this.employee_name = employee_name;

    }

    public void setDate(String date) {
        Date = date;
    }

    public void setOpp_description(String opp_description) {
        this.opp_description = opp_description;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setExpected_revenue(String expected_revenue) {
        this.expected_revenue = expected_revenue;
    }

    public void setExpected_closingdate(String expected_closingdate) {
        this.expected_closingdate = expected_closingdate;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getDate() {
        return Date;
    }
    public String getOpp_description() {
        return opp_description;
    }


    public String getCustomer_name() {
        return customer_name;
    }


    public String getExpected_revenue() {
        return expected_revenue;
    }


    public String getExpected_closingdate() {
        return expected_closingdate;
    }

    public String getEmployee_name() {
        return employee_name;
    }
}

