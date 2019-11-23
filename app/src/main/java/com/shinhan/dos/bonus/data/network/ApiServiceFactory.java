package com.shinhan.dos.bonus.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceFactory {
	public static final String URL = "http://10.3.17.188:3339"; // 임시 -> 통합예정

	public ApiService makeApiService() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		ApiService apiService = retrofit.create(ApiService.class);
		return apiService;
	}
}
