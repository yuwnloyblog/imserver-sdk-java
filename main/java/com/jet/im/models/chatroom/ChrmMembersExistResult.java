package com.jet.im.models.chatroom;

import com.google.gson.annotations.SerializedName;
import com.jet.im.models.Result;
import com.jet.im.util.GsonUtil;

public class ChrmMembersExistResult extends Result{
    @SerializedName("data")
    private ChrmMembersExist data;
    public ChrmMembersExistResult(Integer code, String errMsg, ChrmMembersExist membersExist){
        super(code, errMsg);
        this.data = membersExist;
    }
    public ChrmMembersExist getChrmMembersExist(){
        return this.data;
    }
    @Override
	public String toString() {
		return GsonUtil.toJson(this, ChrmMembersExistResult.class);
	}
}