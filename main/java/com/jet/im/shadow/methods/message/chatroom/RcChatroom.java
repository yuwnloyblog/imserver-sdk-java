package com.jet.im.shadow.methods.message.chatroom;

import java.util.ArrayList;
import java.util.List;

import com.jet.im.JetIm;
import com.jet.im.models.MessageResult;
import com.jet.im.models.MsgIdEntry;
import com.jet.im.models.ResponseResult;
import com.jet.im.models.message.ChatroomMessage;
import com.jet.im.models.message.RecallMessage;

public class RcChatroom {
    private JetIm jetim;

    public RcChatroom(JetIm jetim){
        this.jetim = jetim;
    }

    public io.rong.models.response.MessageResult send(io.rong.models.message.ChatroomMessage message)throws Exception{
        ChatroomMessage msg = new ChatroomMessage();
        msg.setSenderId(message.getSenderId());
        msg.setTargetIds(message.getTargetId());
        msg.setMsgType(message.getContent().getType());
        msg.setMsgContent(message.getContent().toString());
        msg.setPushContent(message.getPushContent());
        msg.setPushExt(message.getPushExt());
        if(message.getIsPersisted()!=null){
            msg.setIsStorage(message.getIsPersisted()>0);
        }
        if(message.getIsIncludeSender()!=null){
            msg.setIsNotifySender(message.getIsIncludeSender()>0);
        }
        MessageResult result = this.jetim.msgSender.sendChatroomMsg(msg);
        io.rong.models.response.MessageResult rcResult;
        if(result!=null){
            rcResult = new io.rong.models.response.MessageResult(result.getCode(), result.getErrorMessage());
            List<io.rong.models.response.MessageUIDEntry> list = new ArrayList<io.rong.models.response.MessageUIDEntry>();
            if(result.getMsgIds()!=null&&result.getMsgIds().size()>0){
                for (MsgIdEntry entry : result.getMsgIds()) {
                    io.rong.models.response.MessageUIDEntry msgUidEntity = new io.rong.models.response.MessageUIDEntry();
                    msgUidEntity.setChatroomId(entry.getTargetId());
                    msgUidEntity.setMessageUID(entry.getMsgId());
                    list.add(msgUidEntity);
                }
                rcResult.setMessageUIDs(list);
            }
        }else{
            rcResult = new io.rong.models.response.MessageResult(500, "can not get data.");
        }
        return rcResult;
    }

    public io.rong.models.response.ResponseResult broadcast(io.rong.models.message.ChatroomMessage message)throws Exception{
        ChatroomMessage msg = new ChatroomMessage();
        msg.setSenderId(message.getSenderId());
        msg.setTargetIds(message.getTargetId());
        msg.setMsgType(message.getContent().getType());
        msg.setMsgContent(message.getContent().toString());
        msg.setPushContent(message.getPushContent());
        msg.setPushExt(message.getPushExt());
        msg.setIsStorage(message.getIsPersisted()>0);
        msg.setIsNotifySender(message.getIsIncludeSender()>0);
        ResponseResult result = this.jetim.msgSender.sendChatroomMsg(msg);
        io.rong.models.response.ResponseResult rcResult;
        if(result!=null){
            rcResult = new io.rong.models.response.ResponseResult(result.getCode(), result.getErrorMessage());
        }else{
            rcResult = new io.rong.models.response.ResponseResult(500, "can not get data.");
        }
        return rcResult;
    }

    public io.rong.models.response.ResponseResult recall(io.rong.models.message.RecallMessage message)throws Exception{
        long msgTime = Long.parseLong(message.getSentTime());
        RecallMessage recall  = new RecallMessage(3, message.getSenderId(), message.getTargetId(), message.getUId(), msgTime, message.getExtra());
        ResponseResult result = this.jetim.msgSender.recallMsg(recall);
        io.rong.models.response.ResponseResult rcResult;
        if(result!=null){
            rcResult = new io.rong.models.response.ResponseResult(result.getCode(), result.getErrorMessage());
        }else{
            rcResult = new io.rong.models.response.ResponseResult(500, "can not get data.");
        }
        return rcResult;
    }
}
