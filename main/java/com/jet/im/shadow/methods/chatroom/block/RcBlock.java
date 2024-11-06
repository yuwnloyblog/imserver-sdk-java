package com.jet.im.shadow.methods.chatroom.block;

import java.util.ArrayList;
import java.util.List;

import com.jet.im.JetIm;
import com.jet.im.models.ResponseResult;
import com.jet.im.models.chatroom.ChatroomMember;
import com.jet.im.shadow.util.DateUtil;
import com.jet.im.models.chatroom.ChatroomBanMemberIds;
import com.jet.im.models.chatroom.ChatroomBanMembersResult;

import io.rong.models.chatroom.ChatroomModel;

public class RcBlock {
    private JetIm jetim;

    public RcBlock(JetIm jetim){
        this.jetim = jetim;
    }

    public io.rong.models.response.ResponseResult add(ChatroomModel chatroom)throws Exception{
        List<String> memberIds = new ArrayList<>();
        for(io.rong.models.chatroom.ChatroomMember m : chatroom.getMembers()){
            memberIds.add(m.getId());
        }
        long endOffset = 0;
        if(chatroom.getMinute()!=null){
            endOffset = ((long)chatroom.getMinute().intValue())*60*1000;
        }
        ChatroomBanMemberIds banIds = new ChatroomBanMemberIds(chatroom.getId(), memberIds,0,endOffset);
        ResponseResult result = this.jetim.chatroom.chrmMemberBan.add(banIds);
        io.rong.models.response.ResponseResult rcResult;
        if(result!=null){
            rcResult = new io.rong.models.response.ResponseResult(result.getCode(), result.getErrorMessage());
        }else{
            rcResult = new io.rong.models.response.ResponseResult(500, "can not get data.");
        }
        return rcResult;
    }

    public io.rong.models.response.ResponseResult remove(ChatroomModel chatroom)throws Exception{
        List<String> memberIds = new ArrayList<>();
        for(io.rong.models.chatroom.ChatroomMember m : chatroom.getMembers()){
            memberIds.add(m.getId());
        }
        ChatroomBanMemberIds banIds = new ChatroomBanMemberIds(chatroom.getId(), memberIds,0,0);
        ResponseResult result = this.jetim.chatroom.chrmMemberBan.remove(banIds);
        io.rong.models.response.ResponseResult rcResult;
        if(result!=null){
            rcResult = new io.rong.models.response.ResponseResult(result.getCode(), result.getErrorMessage());
        }else{
            rcResult = new io.rong.models.response.ResponseResult(500, "can not get data.");
        }
        return rcResult;
    }

    public io.rong.models.response.ListBlockChatroomUserResult getList(String chatroomId)throws Exception{
        ChatroomBanMembersResult result = this.jetim.chatroom.chrmMemberBan.getList(chatroomId, null, null);
        io.rong.models.response.ListBlockChatroomUserResult rcResult;
        if(result!=null){
            if(result.getChatroomMuteMembers()!=null){
                List<io.rong.models.chatroom.ChatroomMember> members = new ArrayList<>();
                for(ChatroomMember m : result.getChatroomMuteMembers().getMembers()){
                    io.rong.models.chatroom.ChatroomMember member = new io.rong.models.chatroom.ChatroomMember();
                    member.setChatroomId(chatroomId);
                    member.setId(m.getMemberId());
                    member.setTime(DateUtil.formatTime(m.getEndTime()));
                    members.add(member);
                }
                rcResult = new io.rong.models.response.ListBlockChatroomUserResult(members);
            }else{
                rcResult = new io.rong.models.response.ListBlockChatroomUserResult(result.getCode(), result.getErrorMessage(), null);
            }
        }else{
            rcResult = new io.rong.models.response.ListBlockChatroomUserResult(500, "can not get data", null);
        }
        return rcResult;
    }
}
