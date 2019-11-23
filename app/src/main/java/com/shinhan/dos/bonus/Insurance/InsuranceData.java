package com.shinhan.dos.bonus.Insurance;

public class InsuranceData {

    public String isDeduction; //보장형 보험인지 아닌지
    String lifename;
    String description;
    String amount;

    public InsuranceData(String is_insurance_valid, String insurance_name, String insurance_type_txt, String total_insurance_amount) {
        this.isDeduction = is_insurance_valid;
        this.lifename = insurance_name;
        this.description = insurance_type_txt;
        this.amount = total_insurance_amount;
    }
}
