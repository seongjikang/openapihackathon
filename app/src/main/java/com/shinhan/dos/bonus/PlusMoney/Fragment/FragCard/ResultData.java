package com.shinhan.dos.bonus.PlusMoney.Fragment.FragCard;

import com.shinhan.dos.bonus.PlusMoney.Fragment.FragInsurance.InsuranceRecommandData;

import java.util.ArrayList;

public class ResultData {


    public String customerName;
    public String phoneNumber;
    public ArrayList<ProductData> creditCardList;

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<ProductData> getcreditCardList() {
        return creditCardList;
    }
}
