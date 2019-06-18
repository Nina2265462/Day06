package com.bawei.day06;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bawei.day06.api.HeaderPicApi;
import com.bawei.day06.entity.HeaderEntity;
import com.bawei.day06.net.HttpUtil;

import java.io.File;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @SuppressLint("CheckResult")
    public void uploads(View view) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //创建文件
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/h.jpg");

            //创建呢文件请求体对象
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            // 多表单上传的工具类
            MultipartBody.Part imgPart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
            HashMap<String, String> headers = new HashMap<>();
            headers.put("userId", "5442");
            headers.put("sessionId", "15607624091785442");
            HttpUtil.getInstance().createService(HeaderPicApi.class)
                    .uploadHeadPic(headers, imgPart).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HeaderEntity>() {
                @Override
                public void accept(HeaderEntity headerEntity) throws Exception {
                    Log.e("MainActivity====", "accept: " + headerEntity.message);

                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {

                }
            });
        }
    }

    public void upload(View view) {
    }
}
