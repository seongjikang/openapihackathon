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

    public ArrayList<InsuranceData> getInsuranceList() {
        return insuranceList;
    }
}
