package com.jet.im.models.chatroom;

import com.google.gson.annotations.SerializedName;
import com.jet.im.models.Result;
import com.jet.im.util.GsonUtil;

public class ChatroomMuteMembersResult extends Result{
    @SerializedName("data")
    private ChatroomMuteMembers data;
    public ChatroomMuteMembersResult(Integer code, String errMsg, ChatroomMuteMembers chatroom){
        super(code, errMsg);
        this.data = chatroom;
    }
    public ChatroomMuteMembers getChatroomMuteMembers(){
        return this.data;
    }
    @Override
	public String toString() {
		return GsonUtil.toJson(this, ChatroomMuteMembersResult.class);
	}
}
