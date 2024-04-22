package com.example.mb_parking_vachira_hospital.manager.http;

import com.example.mb_parking_vachira_hospital.model.Result_action_mobile_login;

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




}
