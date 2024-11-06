package com.jet.im.models.chatroom;

import java.util.List;

public class ChatroomBanMembers {
    @SerializedName("chat_id")
    private String chatId;
    @SerializedName("members")
    private List<ChatroomMember> members;
    @SerializedName("offset")
    private String offset;

    public ChatroomBanMembers(){}

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
