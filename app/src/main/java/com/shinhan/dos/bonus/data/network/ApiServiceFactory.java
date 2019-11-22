package com.shinhan.dos.bonus.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceFactory {
	public ApiService makeApiService() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(ApiService.URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		ApiService apiService = retrofit.create(ApiService.class);
		return apiService;
	}
}
