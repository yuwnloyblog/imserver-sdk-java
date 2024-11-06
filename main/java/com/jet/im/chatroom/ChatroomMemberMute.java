package com.jet.im.chatroom;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import com.jet.im.JetIm;
import com.jet.im.models.ResponseResult;
import com.jet.im.models.chatroom.ChatroomMuteMemberIds;
import com.jet.im.models.chatroom.ChatroomMuteMembersResult;
import com.jet.im.util.GsonUtil;
import com.jet.im.util.HttpUtil;

public class ChatroomMemberMute {
    private JetIm jetim;
    public ChatroomMemberMute(JetIm jetim){
        this.jetim = jetim;
    }

    public ResponseResult add(ChatroomMuteMemberIds muteIds)throws Exception{
        String urlPath = this.jetim.getApiUrl()+ "/apigateway/chatrooms/mutemembers/add";
        String body = GsonUtil.toJson(muteIds);
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

    public ResponseResult remove(ChatroomMuteMemberIds muteIds)throws Exception{
        String urlPath = this.jetim.getApiUrl()+ "/apigateway/chatrooms/mutemembers/del";
        String body = GsonUtil.toJson(muteIds);
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

    public ChatroomMuteMembersResult getList(String chatId, Integer limit, String offset)throws Exception{
        String urlPath = this.jetim.getApiUrl()+"/apigateway/chatrooms/mutemembers/query?chat_id="+URLEncoder.encode(chatId, "UTF-8");
        if(limit!=null){
            urlPath = urlPath + "&limit="+limit;
        }
        if(offset!=null){
            urlPath = urlPath + "&offset="+offset;
        }
        HttpURLConnection conn = HttpUtil.CreateGetHttpConnection(this.jetim.getAppkey(), this.jetim.getSecret(),urlPath);
        String response = "";
        ChatroomMuteMembersResult result = null;
        try{
            response = HttpUtil.returnResult(conn);
            result = (ChatroomMuteMembersResult)GsonUtil.fromJson(response, ChatroomMuteMembersResult.class);
        }catch(Exception e){
            result = new ChatroomMuteMembersResult(500,"request:"+conn.getURL()+",response:"+response+",Exception:"+e.getMessage(),null);
        }
        return result;
    }
}
