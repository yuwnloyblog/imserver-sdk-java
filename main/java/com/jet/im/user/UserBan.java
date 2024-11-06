package com.jet.im.user;

import java.net.HttpURLConnection;
import com.jet.im.JetIm;
import com.jet.im.models.ResponseResult;
import com.jet.im.models.user.BanUsers;
import com.jet.im.models.user.BanUsersResult;
import com.jet.im.util.GsonUtil;
import com.jet.im.util.HttpUtil;

public class UserBan {
    private JetIm jetim;
    public UserBan(JetIm jetim){
        this.jetim = jetim;
    }

    public ResponseResult add(BanUsers banUsers)throws Exception{
        String urlPath = this.jetim.getApiUrl()+ "/apigateway/users/banusers/ban";
        String body = GsonUtil.toJson(banUsers);
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

    public ResponseResult remove(BanUsers banUsers)throws Exception{
        String urlPath = this.jetim.getApiUrl()+ "/apigateway/users/banusers/unban";
        String body = GsonUtil.toJson(banUsers);
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

    public BanUsersResult getList(int limit, String offset)throws Exception{
        String urlPath = this.jetim.getApiUrl()+"/apigateway/users/banusers/query?";
        urlPath = urlPath + "limit="+limit;
        urlPath = urlPath + "&offset="+offset;
        HttpURLConnection conn = HttpUtil.CreateGetHttpConnection(this.jetim.getAppkey(), this.jetim.getSecret(),urlPath);
        String response = "";
        BanUsersResult result = null;
        try{
            response = HttpUtil.returnResult(conn);
            result = (BanUsersResult)GsonUtil.fromJson(response, BanUsersResult.class);
        }catch(Exception e){
            result = new BanUsersResult(500,"request:"+conn.getURL()+",response:"+response+",Exception:"+e.getMessage(),null);
        }
        return result;
    }

    public BanUsersResult getList(Integer size, Integer page)throws Exception{
        String urlPath = this.jetim.getApiUrl()+"/apigateway/users/banusers/query";
        HttpURLConnection conn = HttpUtil.CreateGetHttpConnection(this.jetim.getAppkey(), this.jetim.getSecret(),urlPath);
        String response = "";
        BanUsersResult result = null;
        try{
            response = HttpUtil.returnResult(conn);
            result = (BanUsersResult)GsonUtil.fromJson(response, BanUsersResult.class);
        }catch(Exception e){
            result = new BanUsersResult(500,"request:"+conn.getURL()+",response:"+response+",Exception:"+e.getMessage(),null);
        }
        return result;
    }

    public BanUsersResult getList()throws Exception{
        return getList(null, null);
    }
}
