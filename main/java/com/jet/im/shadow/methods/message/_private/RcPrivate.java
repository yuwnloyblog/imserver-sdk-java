package com.jet.im.shadow.methods.message._private;

import com.jet.im.JetIm;
import com.jet.im.models.ResponseResult;
import com.jet.im.models.message.PrivateMessage;

public class RcPrivate {
    private JetIm jetim;

    public RcPrivate(JetIm jetim){
        this.jetim = jetim;
    }

    public io.rong.models.response.ResponseResult send(io.rong.models.message.PrivateMessage message)throws Exception{
        PrivateMessage msg = new PrivateMessage();
        msg.setSenderId(message.getSenderId());
        msg.setTargetIds(message.getTargetId());
        msg.setMsgType(message.getContent().getType());
        msg.setMsgContent(message.getContent().toString());
        msg.setPushContent(message.getPushContent());
        msg.setPushExt(message.getPushExt());
        if(message.getIsPersisted()!=null){
            msg.setIsStorage(message.getIsPersisted()>0);
        }
        if(message.getIsCounted()!=null){
            msg.setIsCount(message.getIsCounted()>0);
        }
        if(message.getIsIncludeSender()!=null){
            msg.setIsNotifySender(message.getIsIncludeSender()>0);
        }
        ResponseResult result = this.jetim.msgSender.sendPrivateMsg(msg);
        io.rong.models.response.ResponseResult rcResult;
        if(result!=null){
            rcResult = new io.rong.models.response.ResponseResult(result.getCode(), result.getErrorMessage());
        }else{
            rcResult = new io.rong.models.response.ResponseResult(500, "can not get data.");
        }
        return rcResult;
    }
}