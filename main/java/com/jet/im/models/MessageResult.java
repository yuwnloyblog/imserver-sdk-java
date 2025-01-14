package com.jet.im.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MessageResult extends ResponseResult{
    public MessageResult(Integer code, String errorMessage){
        super(code,errorMessage);
    }
    
    @SerializedName("msg")
    private List<MsgIdEntry> msgIds;
    
    public List<MsgIdEntry> getMsgIds(){
        return this.msgIds;
    }

    public void setMsgIds(List<MsgIdEntry> msgIds){
        this.msgIds = msgIds;
    }
}
