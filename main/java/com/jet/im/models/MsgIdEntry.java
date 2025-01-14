package com.jet.im.models;

import com.google.gson.annotations.SerializedName;

public class MsgIdEntry {
    @SerializedName("target_id")
    private String targetId;
    @SerializedName("msg_id")
    private String msgId;

    public String getTargetId(){
        return this.targetId;
    }

    public void setTargetId(String targetId){
        this.targetId = targetId;
    }

    public String getMsgId(){
        return this.msgId;
    }

    public void setMsgId(String msgId){
        this.msgId = msgId;
    }
}
