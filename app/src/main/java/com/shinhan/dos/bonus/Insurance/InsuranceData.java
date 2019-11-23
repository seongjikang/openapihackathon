package com.shinhan.dos.bonus.Insurance;

public class InsuranceData {

    public int is_insurance_valid; //보장형 보험인지 아닌지
    String insurance_name;
    String insurance_type_txt;
    int total_insurance_amount;
    int minus_tax_amount;

    public InsuranceData(int is_insurance_valid, String insurance_name, String insurance_type_txt, int total_insurance_amount, int minus_tax_amount) {
        this.is_insurance_valid = is_insurance_valid;
        this.insurance_name = insurance_name;
        this.insurance_type_txt = insurance_type_txt;
        this.total_insurance_amount = total_insurance_amount;
        this.minus_tax_amount = minus_tax_amount;
    }
}
