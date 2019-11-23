package com.shinhan.dos.bonus.PlusMoney.Fragment.FragCard;

import java.util.ArrayList;

public class ProductData {
    //int product_img;
    String cardName;
    ArrayList<Bonus> cardBonus;

    public ProductData(String product_name, ArrayList<Bonus> product_subscribe) {
        //this.product_img = product_img;
        this.cardName = product_name;
        this.cardBonus = product_subscribe;
    }
}
