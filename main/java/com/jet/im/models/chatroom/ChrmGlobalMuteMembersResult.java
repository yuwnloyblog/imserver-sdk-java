package com.jet.im.models.chatroom;

import com.google.gson.annotations.SerializedName;
import com.jet.im.models.Result;
import com.jet.im.util.GsonUtil;

public class ChrmGlobalMuteMembersResult extends Result{
    @SerializedName("data")
    private ChrmGlobalMuteMembers data;
    public ChrmGlobalMuteMembersResult(Integer code, String errMsg, ChrmGlobalMuteMembers muteMembers){
        super(code, errMsg);
        this.data = muteMembers;
    }
    public ChrmGlobalMuteMembers getChatroomMuteMembers(){
        return this.data;
    }
    @Override
	public String toString() {
		return GsonUtil.toJson(this, ChrmGlobalMuteMembersResult.class);
	}
}
