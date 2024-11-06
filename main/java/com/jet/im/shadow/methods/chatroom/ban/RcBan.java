package com.jet.im.shadow.methods.chatroom.ban;

import java.util.ArrayList;
import java.util.List;

import com.jet.im.JetIm;
import com.jet.im.models.ResponseResult;
import com.jet.im.models.chatroom.ChatroomMember;
import com.jet.im.models.chatroom.ChrmGlobalMuteMemberIds;
import com.jet.im.models.chatroom.ChrmGlobalMuteMembersResult;
import com.jet.im.shadow.util.DateUtil;

import io.rong.models.chatroom.ChatroomModel;

public class RcBan {
    private JetIm jetim;

    public RcBan(JetIm jetim){
        this.jetim = jetim;
    }

    public io.rong.models.response.ResponseResult add(ChatroomModel chatroom)throws Exception{
        List<String> ids = new ArrayList<>();
        for(io.rong.models.chatroom.ChatroomMember m : chatroom.getMembers()){
            ids.add(m.id);
        }
        long endOffset = 0;
        if(chatroom.getMinute()!=null){
            endOffset = ((long)chatroom.getMinute().intValue())*60*1000;
        }
        ChrmGlobalMuteMemberIds muteIds = new ChrmGlobalMuteMemberIds(ids,0,endOffset);
        ResponseResult result = this.jetim.chatroom.chrmGlobalMute.add(muteIds);
        io.rong.models.response.ResponseResult rcResult;
        if(result!=null){
            rcResult = new io.rong.models.response.ResponseResult(result.getCode(), result.getErrorMessage());
        }else{
            rcResult = new io.rong.models.response.ResponseResult(500, "can not get data.");
        }
        return rcResult;
    }

    public io.rong.models.response.ResponseResult remove(ChatroomModel chatroom)throws Exception{
        List<String> ids = new ArrayList<>();
        for(io.rong.models.chatroom.ChatroomMember m : chatroom.getMembers()){
            ids.add(m.id);
        }
        ChrmGlobalMuteMemberIds muteIds = new ChrmGlobalMuteMemberIds(ids,0,0);
        ResponseResult result = this.jetim.chatroom.chrmGlobalMute.remove(muteIds);
        io.rong.models.response.ResponseResult rcResult;
        if(result!=null){
            rcResult = new io.rong.models.response.ResponseResult(result.getCode(), result.getErrorMessage());
        }else{
            rcResult = new io.rong.models.response.ResponseResult(500, "can not get data.");
        }
        return rcResult;
    }

    public io.rong.models.response.ListGagChatroomUserResult getList()throws Exception{
        ChrmGlobalMuteMembersResult result = this.jetim.chatroom.chrmGlobalMute.getList(null, null);
        io.rong.models.response.ListGagChatroomUserResult rcResult;
        if(result!=null){
            if(result.getChatroomMuteMembers()!=null){
                List<io.rong.models.chatroom.ChatroomMember> members = new ArrayList<>();
                for(ChatroomMember m : result.getChatroomMuteMembers().getMembers()){
                    io.rong.models.chatroom.ChatroomMember member = new io.rong.models.chatroom.ChatroomMember();
                    member.setChatroomId("");
                    member.setId(m.getMemberId());
                    member.setTime(DateUtil.formatTime(m.getEndTime()));
                    members.add(member);
                }
                rcResult = new io.rong.models.response.ListGagChatroomUserResult(members);
            }else{
                rcResult = new io.rong.models.response.ListGagChatroomUserResult(result.getCode(), result.getErrorMessage(), null);
            }
        }else{
            rcResult = new io.rong.models.response.ListGagChatroomUserResult(500, "can not get data", null);
        }
        return rcResult;
    }
}
