package com.shinhan.dos.bonus.data;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Callback;

public interface DataResult {
	public void getCardUsage(Callback<JsonObject> callback, Map<String, String> params);
	public void getCardList(Callback<JsonObject> callback, Map<String, String> params);
	public void getCash(Callback<JsonObject> callback, Map<String, String> params);
	public void getInvestUsage(Callback<JsonObject> callback, Map<String, String> params);
	public void searchInvest(Callback<JsonObject> callback, Map<String, String> params);
	public void getMyAccount(Callback<JsonObject> callback, Map<String, String> params);
	public void getLifeUsage(Callback<JsonObject> callback, Map<String, String> params);
	public void searchInsurance(Callback<JsonObject> callback, Map<String, String> params);
	public void getPublicTransfer(Callback<JsonObject> callback, Map<String, String> params);
	public void getCulturePayment(Callback<JsonObject> callback, Map<String, String> params);
	public void getRetailAtm(Callback<JsonObject> callback, Map<String, String> params);
	public void getCultureList(Callback<JsonObject> callback, Map<String, String> params);
	public void getSalaryAmt(Callback<JsonObject> callback, Map<String, String> params);
	public void getName(Callback<JsonObject> callback, Map<String, String> params);
/*
	// 샘플코드 ..
	public void getUserList(Callback<JsonObject> callback);
	public void joinUser(Callback<JsonObject> callback, String name, String uuid, String idNumber, String password);
	public void token(Callback<Map> callback, Map<String, String> params);
	public void inquiryRealName(Callback<Map> callback, String token, Map<String, String> params);
*/

}
