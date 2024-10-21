package com.juggle.im.models.chatroom;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ChatroomMuteMembers {
    @SerializedName("chat_id")
    private String chatId;
    @SerializedName("members")
    private List<ChatroomMember> members;
    @SerializedName("offset")
    private String offset;

    public ChatroomMuteMembers(){}

    public String getChatId(){
        return this.chatId;
    }

    public List<ChatroomMember> getMembers(){
        return this.members;
    }

    public String getOffset(){
        return this.offset;
    }
}
