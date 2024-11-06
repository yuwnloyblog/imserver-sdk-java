package com.jet.im.shadow.methods.chatroom;

import java.util.ArrayList;
import java.util.List;

import com.jet.im.JetIm;
import com.jet.im.models.ResponseResult;
import com.jet.im.models.chatroom.ChatroomInfo;
import com.jet.im.models.chatroom.ChatroomInfoResult;
import com.jet.im.models.chatroom.ChatroomMember;
import com.jet.im.shadow.methods.chatroom.ban.RcBan;
import com.jet.im.shadow.methods.chatroom.block.RcBlock;
import com.jet.im.shadow.methods.chatroom.mute.RcMuteMembers;
import com.jet.im.shadow.util.DateUtil;

import io.rong.models.chatroom.ChatroomDataModel;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ChatroomUserQueryResult;

public class RcChatroom {
    private JetIm jetim;
    public RcMuteMembers muteMembers;
    public RcBlock block;
    public RcBan ban;
    public RcChatroom(JetIm jetim){
        this.jetim = jetim;
        this.ban = new RcBan(this.jetim);
        this.block = new RcBlock(this.jetim);
        this.muteMembers = new RcMuteMembers(this.jetim);
    }

    public io.rong.models.response.ResponseResult create(ChatroomModel[] chatrooms)throws Exception{
        for(ChatroomModel chatroom : chatrooms){
            ChatroomInfo chrm = new ChatroomInfo(chatroom.getId(), chatroom.getName());
            this.jetim.chatroom.create(chrm);
        }
        return new io.rong.models.response.ResponseResult(0, "success");
    }

    public io.rong.models.response.ResponseResult createV2(ChatroomDataModel chatroom)throws Exception{
        ChatroomInfo chrm = new ChatroomInfo(chatroom.getId(), "");
        if(chatroom.getIsBan()){
            chrm.setIsMute(1);
        }
        ResponseResult result = this.jetim.chatroom.create(chrm);
        io.rong.models.response.ResponseResult rcResult;
        if(result!=null){
            rcResult = new io.rong.models.response.ResponseResult(result.getCode(), result.getErrorMessage());
        }else{
            rcResult = new io.rong.models.response.ResponseResult(500, "can not get data.");
        }
        return rcResult;
    }

    public io.rong.models.response.ResponseResult destroy(ChatroomModel chatroom)throws Exception{
        ChatroomInfo chrm = new ChatroomInfo(chatroom.getId(),chatroom.getName());
        ResponseResult result = this.jetim.chatroom.destroy(chrm);
        io.rong.models.response.ResponseResult rcResult;
        if(result!=null){
            rcResult = new io.rong.models.response.ResponseResult(result.getCode(), result.getErrorMessage());
        }else{
            rcResult = new io.rong.models.response.ResponseResult(500, "can not get data.");
        }
        return rcResult;
    }

    public ChatroomUserQueryResult get(ChatroomModel chatroom)throws Exception{
        int order = 0;
        if(chatroom.getOrder()==1){
            order = 1;
        }
        ChatroomInfoResult result = this.jetim.chatroom.get(chatroom.getId(), chatroom.getCount(), order);
        ChatroomUserQueryResult rcResult;
        if(result!=null){
            if(result.getChatroomInfo()!=null){
                List<io.rong.models.chatroom.ChatroomMember> members = new ArrayList<>();
                for(ChatroomMember member : result.getChatroomInfo().getMembers()){
                    io.rong.models.chatroom.ChatroomMember m = new io.rong.models.chatroom.ChatroomMember();
                    m.setChatroomId(chatroom.getId());
                    m.setId(member.getMemberId());
                    m.setTime(DateUtil.formatTime(member.getAddedTime()));
                    members.add(m);
                }
                rcResult = new ChatroomUserQueryResult(result.getCode(), result.getErrorMessage(),result.getChatroomInfo().getMemberCount(),members);
            }else{
                rcResult = new ChatroomUserQueryResult(result.getCode(), result.getErrorMessage(),0,null);
            }
        }else{
            rcResult = new ChatroomUserQueryResult(500, "can not get data",0,null);
        }
        return rcResult;
    }
}
