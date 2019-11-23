package com.shinhan.dos.bonus.Insurance;

import java.util.ArrayList;

public class ResultInsuranceData {

    public String customerName;
    public String phoneNumber;
    public ArrayList<InsuranceData> insuranceList;

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<InsuranceData> getInsuranceList() {
        return insuranceList;
    }
}
