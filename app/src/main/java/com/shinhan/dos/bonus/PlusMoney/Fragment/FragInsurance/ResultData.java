package com.shinhan.dos.bonus.PlusMoney.Fragment.FragInsurance;

import java.util.ArrayList;

public class ResultData {


    public String customerName;
    public String phoneNumber;
    public ArrayList<InsuranceRecommandData> insuranceRecommandList;

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<InsuranceRecommandData> getInsuranceRecommandList() {
        return insuranceRecommandList;
    }
}
