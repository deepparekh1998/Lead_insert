package com.example.lead_insert;

import androidx.appcompat.app.AppCompatActivity;

public class LeadListModel {
    String lead_description;
    String company_name;
    String contact_name;
    String mobile;


    public LeadListModel(String lead_description, String company_name, String contact_name, String mobile) {
        this.lead_description = lead_description;
        this.company_name = company_name;
        this.contact_name = contact_name;
        this.mobile = mobile;

    }

    public void setLead_description(String lead_description) {
        this.lead_description = lead_description;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLead_description() {
        return lead_description;
    }


    public String getCompany_name() {
        return company_name;
    }


    public String getContact_name() {
        return contact_name;
    }


    public String getMobile() {
        return mobile;
    }
}
