package com.shinhan.dos.bonus.data;

import com.google.gson.JsonObject;
import com.shinhan.dos.bonus.data.network.ApiServiceFactory;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class DataResultImpl implements DataResult {
	private ApiServiceFactory apiServiceFactory = new ApiServiceFactory();

	@Override
	public void getCardUsage(Callback<JsonObject> callback, Map<String, String> params) {
		Call<JsonObject> call = apiServiceFactory.makeApiService().getCardUsage(params);
		call.enqueue(callback);
	}

	@Override
	public void getInvestUsage(Callback<JsonObject> callback, Map<String, String> params) {
		Call<JsonObject> call = apiServiceFactory.makeApiService().getInvestUsage(params);
		call.enqueue(callback);
	}

	@Override
	public void getMyAccount(Callback<JsonObject> callback, Map<String, String> params) {
		Call<JsonObject> call = apiServiceFactory.makeApiService().getMyAccount(params);
		call.enqueue(callback);
	}

	@Override
	public void getLifeUsage(Callback<JsonObject> callback, Map<String, String> params) {
		Call<JsonObject> call = apiServiceFactory.makeApiService().getLifeUsage(params);
		call.enqueue(callback);
	}

/*
	// 샘플코드 ..
	@Override
	public void getUserList(Callback<JsonObject> callback) {
		Call<JsonObject> call = apiServiceFactory.makeApiService().getUserList();
		call.enqueue(callback);
		return;
	}

	@Override
	public void joinUser(Callback<JsonObject> callback, String name, String uuid, String idNumber, String password) {

		JsonObject param = new JsonObject();
		param.addProperty("name", name);
		param.addProperty("uuid", uuid);
		param.addProperty("id_number", idNumber);
		param.addProperty("password", password);

		Call<JsonObject> call = apiServiceFactory.makeApiService().joinUser(param);
		call.enqueue(callback);
		return;
	}

	@Override
	public void token(Callback<Map> callback, Map<String, String> params) {
		Call<Map> call = apiServiceFactory.makeApiService().token(params);
		call.enqueue(callback);
	}

	@Override
	public void inquiryRealName(Callback<Map> callback, String token, Map<String, String> params) {
		Call<Map> call = apiServiceFactory.makeApiService().inquiryRealName(token, params);
		call.enqueue(callback);
	}
*/

}
