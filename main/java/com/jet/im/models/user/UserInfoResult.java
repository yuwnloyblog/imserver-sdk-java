package com.jet.im.models.user;

import com.google.gson.annotations.SerializedName;
import com.jet.im.models.Result;
import com.jet.im.util.GsonUtil;

public class UserInfoResult extends Result{
    @SerializedName("data")
    private UserInfo data;

    public UserInfoResult(Integer code, String errMsg, UserInfo userinfo){
        super(code, errMsg);
        this.data = userinfo;
    }

    public UserInfo getUserInfo(){
        return this.data;
    }

    public UserInfoResult setUserInfo(UserInfo userinfo){
        this.data = userinfo;
        return this;
    }

    @Override
	public String toString() {
		return GsonUtil.toJson(this, UserInfoResult.class);
	}
}