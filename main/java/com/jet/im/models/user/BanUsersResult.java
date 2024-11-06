package com.jet.im.models.user;

import com.google.gson.annotations.SerializedName;
import com.jet.im.models.Result;
import com.jet.im.util.GsonUtil;

public class BanUsersResult extends Result {
    @SerializedName("data")
    private BanUsers banUsers;

    public BanUsersResult(Integer code, String errMsg, BanUsers banUsers){
        super(code, errMsg);
        this.banUsers = banUsers;
    }

    public BanUsers getBanUsers(){
        return this.banUsers;
    }

    @Override
	public String toString() {
		return GsonUtil.toJson(this, BanUsersResult.class);
	}
}