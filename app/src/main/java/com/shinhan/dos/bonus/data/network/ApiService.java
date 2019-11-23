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

	/*
	 * 메인화면 과목별 총 금액 조회
	 */
	@POST("/bank/main")
	public Call<JsonObject> getMainInfo(@Body Map<String, String> params);

	/*************************
	 * 카드
	 *************************/
	/*
	 * 월별 카드 사용 내역
	 */
	@POST("/card/cardUsage")
	public Call<JsonObject> getCardUsage(@Body Map<String, String> params);

	/*
	 * 카드상품조회
	 */
	@POST("/card/cardList")
	public Call<JsonObject> getCardList(@Body Map<String, String> params);

	/*
	 * 현금영수증
	 */
	@POST("/life/cash")
	public Call<JsonObject> getCash(@Body Map<String, String> params);

	/*************************
	 * 주식
	 *************************/
	/*
	 * 내 주식
	 */
	@POST("/invest/investUsage")
	public Call<JsonObject> getInvestUsage(@Body Map<String, String> params);

	/*
	 * 공제가능주식
	 */
	@POST("/invest/searchInvest")
	public Call<JsonObject> searchInvest(@Body Map<String, String> params);

	/*************************
	 * 은행
	 *************************/
	/*
	 * 내 주택청약, 연금저축, 펀드 조회
	 */
	@POST("/bank/myAccount")
	public Call<JsonObject> getMyAccount(@Body Map<String, String> params);

	/*************************
	 * 보험
	 *************************/
	/*
	 * 내 보험
	 */
	@POST("/life/lifeUsage")
	public Call<JsonObject> getLifeUsage(@Body Map<String, String> params);

	/*
	 * 보장성 보험 상품 조회
	 */
	@POST("/life/searchInsurance")
	public Call<JsonObject> searchInsurance(@Body Map<String, String> params);

	/*************************
	 * 대중교통
	 *************************/
	/*
	 * 대중교통
	 */
	@POST("/life/PublicTransfer")
	public Call<JsonObject> getPublicTransfer(@Body Map<String, String> params);

	/*************************
	 * 전통시장
	 *************************/
	/*
	 * 전통시장 쓴 금액 조회
	 */
	@POST("/life/culturePayment")
	public Call<JsonObject> getCulturePayment(@Body Map<String, String> params);

	/*
	 * 신한은행 ATM 조회
	 */
	@POST("/bank/retailAtm")
	public Call<JsonObject> getRetailAtm(@Body Map<String, String> params);

	/*
	 * 문화 조회 (전통시장과 좌표)
	 */
	@POST("/bank/culture")
	public Call<JsonObject> getCultureList(@Body Map<String, String> params);


	@POST("/bank/salary")
	public Call<JsonObject> getTotalSalaryAmt(@Body Map<String, String> params);

	@POST("/bank/name")
	public Call<JsonObject> getCusName(@Body Map<String, String> params);

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
