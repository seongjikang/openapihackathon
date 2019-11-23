package com.shinhan.dos.bonus.PlusMoney.Fragment.FragStock;

import com.shinhan.dos.bonus.PlusMoney.Fragment.FragInsurance.InsuranceRecommandData;

import java.util.ArrayList;

public class ResultData {

    public String customerName;
    public String phoneNumber;
    public ArrayList<StockRecommandData> holdingStockList;

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<StockRecommandData> getholdingStockList() {
        return holdingStockList;
    }
}
