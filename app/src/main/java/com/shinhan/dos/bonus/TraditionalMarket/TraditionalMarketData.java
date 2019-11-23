package com.shinhan.dos.bonus.TraditionalMarket;

public class TraditionalMarketData {
    private double x;
    private double y;
    private String tmGbn;
    private String name;
    private double distance;

    public TraditionalMarketData(double x, double y, String tmGbn, String name, double distance) {
        this.x = x;
        this.y = y;
        this.tmGbn = tmGbn;
        this.name = name;
        this.distance = distance;
    }

    public TraditionalMarketData() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getTmGbn() {
        return tmGbn;
    }

    public void setTmGbn(String tmGbn) {
        this.tmGbn = tmGbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
