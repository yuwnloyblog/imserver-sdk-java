package com.jet.im.chatroom;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import com.jet.im.JetIm;
import com.jet.im.models.ResponseResult;
import com.jet.im.models.chatroom.ChatroomBanMemberIds;
import com.jet.im.models.chatroom.ChatroomBanMembersResult;
import com.jet.im.util.GsonUtil;
import com.jet.im.util.HttpUtil;

public class ChatroomMemberBan {
    private JetIm jetim;
    public ChatroomMemberBan(JetIm jetim){
        this.jetim = jetim;
    }

    public ResponseResult add(ChatroomBanMemberIds banIds)throws Exception{
        String urlPath = this.jetim.getApiUrl()+ "/apigateway/chatrooms/banmembers/add";
        String body = GsonUtil.toJson(banIds);
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

    public ResponseResult remove(ChatroomBanMemberIds banIds)throws Exception{
        String urlPath = this.jetim.getApiUrl()+ "/apigateway/chatrooms/banmembers/del";
        String body = GsonUtil.toJson(banIds);
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

    public ChatroomBanMembersResult getList(String chatId, Integer limit, String offset)throws Exception{
        String urlPath = this.jetim.getApiUrl()+"/apigateway/chatrooms/banmembers/query?chat_id="+URLEncoder.encode(chatId, "UTF-8");
        if(limit!=null){
            urlPath = urlPath + "&limit="+limit;
        }
        if(offset!=null){
            urlPath = urlPath + "&offset="+offset;
        }
        HttpURLConnection conn = HttpUtil.CreateGetHttpConnection(this.jetim.getAppkey(), this.jetim.getSecret(),urlPath);
        String response = "";
        ChatroomBanMembersResult result = null;
        try{
            response = HttpUtil.returnResult(conn);
            result = (ChatroomBanMembersResult)GsonUtil.fromJson(response, ChatroomBanMembersResult.class);
        }catch(Exception e){
            result = new ChatroomBanMembersResult(500,"request:"+conn.getURL()+",response:"+response+",Exception:"+e.getMessage(),null);
        }
        return result;
    }
}
