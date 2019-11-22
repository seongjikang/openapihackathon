package com.shinhan.dos.bonus.data.network;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ApiService {
	public static final String URL = "http://로컬";

	// 유저 전체 조회
	@POST("/v1/user/join")
	public Call<JsonObject> getUserList();

	// 유저 가입
	@Headers({ "Content-Type: application/json;charset=UTF-8"})
	@POST("/v1/user/join")
	public Call<JsonObject> joinUser(@Body JsonObject body);

	@GET("/v1.0/account/balance")
	public Call<Map> accountBalance(@Header("Authorization") String token, @QueryMap Map<String, String> params);

	@POST("/v1.0/inquiry/real_name")
	public Call<Map> inquiryRealName(@Header("Authorization") String token, @Body Map<String, String> params);

	@FormUrlEncoded // POST에만 붙는다고 한다.
	@POST("/oauth/2.0/token")
	public Call<Map> token(@FieldMap Map<String, String> params);
}
