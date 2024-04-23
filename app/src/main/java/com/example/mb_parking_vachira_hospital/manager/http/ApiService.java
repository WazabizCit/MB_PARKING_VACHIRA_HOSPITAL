package com.example.mb_parking_vachira_hospital.manager.http;

import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_checkcardin;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_get_membercartype;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_get_visitorcartype;
import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_login;
import com.example.mb_parking_vachira_hospital.model.Result_action_save_in;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.MultipartBody;
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
    @Headers("token:33629f7a-03b5-11eb-adc1-0242ac120002")
    @POST("api/SaveActionIn/Save")
    Call<Result_action_save_in> action_save_in(
            @Query("card_id") String card_id,
            @Query("license_plate") String license_plate,
            @Query("cartype_id") String cartype_id,
            @Query("user_id") String user_id,
            @Query("user_no_record") String user_no_record,
            @Query("guardhouse") String guardhouse,
            @Query("address") String address,
            @Part MultipartBody.Part file1,
            @Part MultipartBody.Part file2

    );



}
