package com.jet.im.chatroom;

import java.net.HttpURLConnection;

import com.jet.im.JetIm;
import com.jet.im.models.ResponseResult;
import com.jet.im.models.chatroom.ChrmGlobalMuteMemberIds;
import com.jet.im.models.chatroom.ChrmGlobalMuteMembersResult;
import com.jet.im.util.GsonUtil;
import com.jet.im.util.HttpUtil;

public class ChrmGlobalMemberMute {
    private JetIm jetim;
    public ChrmGlobalMemberMute(JetIm jetim){
        this.jetim = jetim;
    }

    public ResponseResult add(ChrmGlobalMuteMemberIds muteIds)throws Exception{
        String urlPath = this.jetim.getApiUrl()+ "/apigateway/chatrooms/globalmutemembers/add";
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

    public ResponseResult remove(ChrmGlobalMuteMemberIds muteIds)throws Exception{
        String urlPath = this.jetim.getApiUrl()+ "/apigateway/chatrooms/globalmutemembers/del";
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

    public ChrmGlobalMuteMembersResult getList(Integer limit, String offset)throws Exception{
        String urlPath = this.jetim.getApiUrl()+"/apigateway/chatrooms/globalmutemembers/query?";
        if(limit!=null){
            urlPath = urlPath + "&limit="+limit;
        }
        if(offset!=null){
            urlPath = urlPath + "&offset="+offset;
        }
        HttpURLConnection conn = HttpUtil.CreateGetHttpConnection(this.jetim.getAppkey(), this.jetim.getSecret(),urlPath);
        String response = "";
        ChrmGlobalMuteMembersResult result = null;
        try{
            response = HttpUtil.returnResult(conn);
            result = (ChrmGlobalMuteMembersResult)GsonUtil.fromJson(response, ChrmGlobalMuteMembersResult.class);
        }catch(Exception e){
            result = new ChrmGlobalMuteMembersResult(500,"request:"+conn.getURL()+",response:"+response+",Exception:"+e.getMessage(),null);
        }
        return result;
    }
}
