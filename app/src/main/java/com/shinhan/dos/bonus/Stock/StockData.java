package com.shinhan.dos.bonus.Stock;

public class StockData {

    public int is_stock_valid; //공제되는주식인지 아닌지
    public String stock_name;
    public int stock_currentvalue;
    public String stock_change_rate;
    public String stock_subscribe1;
    public String stock_subscribe2;

    public StockData(int is_stock_valid, String stock_name, int stock_currentvalue, String stock_change_rate, String stock_subscribe1, String stock_subscribe2) {
        this.is_stock_valid = is_stock_valid;
        this.stock_name = stock_name;
        this.stock_currentvalue = stock_currentvalue;
        this.stock_change_rate = stock_change_rate;
        this.stock_subscribe1 = stock_subscribe1;
        this.stock_subscribe2 = stock_subscribe2;
    }
}
