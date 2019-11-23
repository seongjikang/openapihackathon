package com.shinhan.dos.bonus.Stock;

public class StockData {

    public int deduction; //공제되는주식인지 아닌지
    public String name;
    public String evlt_amt;
    public String uv_diff_ratio;
    public String news_titl;
   // public String stock_subscribe2;

    public StockData(int is_stock_valid, String stock_name, String stock_currentvalue, String stock_change_rate, String stock_subscribe1, String stock_subscribe2) {
        this.deduction = is_stock_valid;
        this.name = stock_name;
        this.evlt_amt = stock_currentvalue;
        this.uv_diff_ratio = stock_change_rate;
        this.news_titl = stock_subscribe1;
        //this.stock_subscribe2 = stock_subscribe2;
    }
}
