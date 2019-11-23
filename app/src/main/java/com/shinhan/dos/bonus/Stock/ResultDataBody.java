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

    public ArrayList<ArrayList<StockData>> getHoldingStockList() {
        return holdingStockList;
    }
}
