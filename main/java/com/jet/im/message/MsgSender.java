package com.jet.im.message;

import java.net.HttpURLConnection;

import com.jet.im.JetIm;
import com.jet.im.models.ResponseResult;
import com.jet.im.models.message.ChatroomMessage;
import com.jet.im.models.message.PrivateMessage;
import com.jet.im.models.message.RecallMessage;
import com.jet.im.util.GsonUtil;
import com.jet.im.util.HttpUtil;

public class MsgSender {
    private JetIm jetim;
    public MsgSender(JetIm jetim){
        this.jetim = jetim;
    }

    public ResponseResult sendPrivateMsg(PrivateMessage message)throws Exception{
        String urlPath = this.jetim.getApiUrl()+ "/apigateway/messages/private/send";
        String body = GsonUtil.toJson(message);
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(this.jetim.getAppkey(), this.jetim.getSecret(), urlPath);
        HttpUtil.setBodyParameter(body, conn);
        String response = "";
        ResponseResult result  = null;
        try{
            response = HttpUtil.returnResult(conn);
            result = (ResponseResult)GsonUtil.fromJson(response, ResponseResult.class);
        }catch(Exception e){
            result = new ResponseResult(500,"request:"+conn.getURL()+",response:"+response+",Exception:"+e.getMessage());
        }
        return result;
    }

    public ResponseResult sendChatroomMsg(ChatroomMessage message)throws Exception{
        String urlPath = this.jetim.getApiUrl()+ "/apigateway/messages/chatroom/send";
        String body = GsonUtil.toJson(message);
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(this.jetim.getAppkey(), this.jetim.getSecret(), urlPath);
        HttpUtil.setBodyParameter(body, conn);
        String response = "";
        ResponseResult result  = null;
        try{
            response = HttpUtil.returnResult(conn);
            result = (ResponseResult)GsonUtil.fromJson(response, ResponseResult.class);
        }catch(Exception e){
            result = new ResponseResult(500,"request:"+conn.getURL()+",response:"+response+",Exception:"+e.getMessage());
        }
        return result;
    }

    public ResponseResult broadcastChatroomMsg(ChatroomMessage message)throws Exception{
        String urlPath = this.jetim.getApiUrl()+ "/apigateway/messages/chatroom/broadcast";
        String body = GsonUtil.toJson(message);
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(this.jetim.getAppkey(), this.jetim.getSecret(), urlPath);
        HttpUtil.setBodyParameter(body, conn);
        String response = "";
        ResponseResult result  = null;
        try{
            response = HttpUtil.returnResult(conn);
            result = (ResponseResult)GsonUtil.fromJson(response, ResponseResult.class);
        }catch(Exception e){
            result = new ResponseResult(500,"request:"+conn.getURL()+",response:"+response+",Exception:"+e.getMessage());
        }
        return result;
    }

    public ResponseResult recallMsg(RecallMessage msg)throws Exception{
        String urlPath = this.jetim.getApiUrl()+"/apigateway/hismsgs/recall";
        String body = GsonUtil.toJson(msg);
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(this.jetim.getAppkey(), this.jetim.getSecret(), urlPath);
        HttpUtil.setBodyParameter(body, conn);
        String response = "";
        ResponseResult result  = null;
        try{
            response = HttpUtil.returnResult(conn);
            result = (ResponseResult)GsonUtil.fromJson(response, ResponseResult.class);
        }catch(Exception e){
            result = new ResponseResult(500,"request:"+conn.getURL()+",response:"+response+",Exception:"+e.getMessage());
        }
        return result;
    }
}
