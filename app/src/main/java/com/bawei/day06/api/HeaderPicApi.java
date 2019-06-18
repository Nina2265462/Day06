package com.bawei.day06.api;

import com.bawei.day06.entity.HeaderEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/*
 *@Auther:cln
 *@Date: 2019/6/18
 *@Description:功能
 * */
public interface HeaderPicApi {
    @POST
    @Multipart
    Observable<HeaderEntity> uploadHeadPic(@HeaderMap HashMap<String, String> headers, @Part MultipartBody.Part file);
}
