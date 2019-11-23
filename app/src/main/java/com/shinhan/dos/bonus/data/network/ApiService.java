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

	/*************************
	 * 카드
	 *************************/
	/*
	 * 월별 카드 사용 내역 - 3338
	 */
	@POST("/card/cardUsage")
	public Call<JsonObject> getCardUsage(@Body Map<String, String> params);

	/*
	 * 카드상품조회 - 3338
	 */
	@POST("/card/cardList")
	public Call<JsonObject> getCardList(@Body Map<String, String> params);

	/*
	 * 현금영수증 - 3339
	 */
	@POST("/life/getcash")
	public Call<JsonObject> getCash(@Body Map<String, String> params);

	/*************************
	 * 주식
	 *************************/
	/*
	 * 내 주식 - 3339
	 */
	@POST("/invest/investUsage")
	public Call<JsonObject> getInvestUsage(@Body Map<String, String> params);

	/*
	 * 공제가능주식 - 3339
	 */
	@POST("/invest/searchInvest")
	public Call<JsonObject> searchInvest(@Body Map<String, String> params);

	/*************************
	 * 은행
	 *************************/
	/*
	 * 내 주택청약, 연금저축, 펀드 조회 - 3338
	 */
	@POST("/bank/myAccount")
	public Call<JsonObject> getMyAccount(@Body Map<String, String> params);

	/*************************
	 * 보험
	 *************************/
	/*
	 * 내 보험 - 3339
	 */
	@POST("/life/lifeUsage")
	public Call<JsonObject> getLifeUsage(@Body Map<String, String> params);

	/*************************
	 * 대중교통
	 *************************/
	/*
	 * 대중교통 - 3339
	 */
	@POST("/life/getPublicTransfer")
	public Call<JsonObject> getPublicTransfer(@Body Map<String, String> params);

	/*************************
	 * 전통시장
	 *************************/
	/*
	 * 전통시장 - 3339
	 */
	@POST("/life/getTraditionalMarket")
	public Call<JsonObject> getTraditionalMarket(@Body Map<String, String> params);




	/*
	// 샘플코드 ..
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
	*/
}
