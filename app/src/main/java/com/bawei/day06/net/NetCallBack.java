package com.bawei.day06.net;

import com.bawei.day06.entity.HeaderEntity;

/*
 *@Auther:cln
 *@Date: 2019/6/17
 *@Description:功能
 * */
public interface NetCallBack {
    void onSuccess(HeaderEntity headerEntity);

    void onFailure(String msg);
}
