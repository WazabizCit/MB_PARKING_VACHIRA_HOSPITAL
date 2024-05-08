package com.example.mb_parking_vachira_hospital.manager.http;

import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_calculationparking;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_checkcardin;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_checkestamp;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_get_membercartype;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_get_promotion;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_get_visitorcartype;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_login;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_save_promotion;
import com.example.mb_parking_vachira_hospital.model.Result_action_save_in;
import com.example.mb_parking_vachira_hospital.model.Result_action_save_out;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {


    @Headers({"token:33629f7a-03b5-11eb-adc1-0242ac120002", "Content-Type: application/json"})
    @POST("api/Login/UserLogin")
    Call<Result_action_mobile_login> action_mobile_login(@Body HashMap<String, String> fields);



    @Headers({"token:33629f7a-03b5-11eb-adc1-0242ac120002", "Content-Type: application/json"})
    @POST("api/ActionIn/CheckCardIn/checkcard")
    Call<Result_action_mobile_checkcardin> action_mobile_checkcardin(@Body HashMap<String, String> fields);



    @Headers({"token:33629f7a-03b5-11eb-adc1-0242ac120002", "Content-Type: application/json"})
    @POST("api/Getcartype/GetCartype")
    Call<Result_action_mobile_get_visitorcartype> action_mobile_get_visitorcartype(@Body HashMap<String, String> fields);


    @Headers({"token:33629f7a-03b5-11eb-adc1-0242ac120002", "Content-Type: application/json"})
    @POST("api/Getcartype/GetMemberType")
    Call<Result_action_mobile_get_membercartype> action_mobile_get_membercartype(@Body HashMap<String, String> fields);



    @Multipart
    @POST("api/SaveActionIn/Save")
    Call<Result_action_save_in> action_save_in(
            @Part("card_id") RequestBody  card_id,
            @Part("license_plate") RequestBody  license_plate,
            @Part("cartype_id") RequestBody  cartype_id,
            @Part("user_id") RequestBody  user_id,
            @Part("user_no_record") RequestBody  user_no_record,
            @Part("guardhouse") RequestBody guardhouse,
            @Part("address") RequestBody  address,
            @Part MultipartBody.Part file1,
            @Part MultipartBody.Part file2

    );




    @Headers({"token:33629f7a-03b5-11eb-adc1-0242ac120002", "Content-Type: application/json"})
    @POST("api/ActionOut/CalculationParking/calculation")
    Call<Result_action_mobile_calculationparking> action_mobile_calculationparking(@Body HashMap<String, String> fields);




    @Multipart
    @Headers("token:33629f7a-03b5-11eb-adc1-0242ac120002")
    @POST("api/SaveActionOut/SaveOut")
    Call<Result_action_save_out> action_save_out(

            @Query("card_id") String card_id,
            @Query("recordin_no") String recordin_no,
            @Query("license") String license,
            @Query("date_in") String date_in,
            @Query("date_out") String date_out,
            @Query("promotion_id") String promotion_id,
            @Query("price") String price,
            @Query("discount") String discount,
            @Query("losscard") String losscard,
            @Query("hours") String hours,
            @Query("minute") String minute,
            @Query("userout") String userout,
            @Query("userno") String userno,
            @Query("user_name") String user_name,
            @Query("guardhouse") String guardhouse,
            @Query("posid") String posid,
            @Query("receipt") String receipt,
            @Query("isMember") String isMember,
            @Query("member_cash_balance") String member_cash_balance,
            @Query("member_decreaseCashOrHour") String member_decreaseCashOrHour,
            @Query("member_pro_minute_balance_use") String member_pro_minute_balance_use,
            @Query("overdate") String overdate,
            @Part MultipartBody.Part file1,
            @Part MultipartBody.Part file2

    );


      //////////////////////// ESTAMP ////////////////////////////////


    @Headers({"token:33629f7a-03b5-11eb-adc1-0242ac120002", "Content-Type: application/json"})
    @POST("api/CheckEstamp/promotion")
    Call<Result_action_mobile_checkestamp> action_mobile_checkestamp(@Body HashMap<String, String> fields);


    @Headers({"token:33629f7a-03b5-11eb-adc1-0242ac120002", "Content-Type: application/json"})
    @POST("api/GetEstamp/promotion")
    Call<Result_action_mobile_get_promotion> action_mobile_get_promotion(@Body HashMap<String, String> fields);


    @Headers({"token:33629f7a-03b5-11eb-adc1-0242ac120002", "Content-Type: application/json"})
    @POST("api/SaveEstamp/promotion")
    Call<Result_action_mobile_save_promotion> action_mobile_save_promotion(@Body HashMap<String, String> fields);


}
