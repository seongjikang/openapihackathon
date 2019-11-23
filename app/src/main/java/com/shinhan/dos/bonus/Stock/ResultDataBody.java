package com.shinhan.dos.bonus.Stock;

import java.util.ArrayList;
import java.util.List;

public class ResultDataBody {
    public String customerName;
    public String phoneNumber;
    public ArrayList<ArrayList<StockData>> holdingStockList;

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

    public ArrayList<ArrayList<StockData>> getHoldingStockList() {
        return holdingStockList;
    }
}
