package com.jet.im.user;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

import com.jet.im.JetIm;
import com.jet.im.models.user.UserInfo;
import com.jet.im.models.user.UserInfoResult;
import com.jet.im.models.user.UserTokenResult;
import com.jet.im.util.GsonUtil;
import com.jet.im.util.HttpUtil;

public class User {
    private JetIm jetim;

    public UserBan userBan;
    public TagUser tagUser;

    public User(JetIm jetim){
        this.jetim = jetim;
        this.userBan = new UserBan(this.jetim);
        this.tagUser = new TagUser(this.jetim);
    }

    public UserTokenResult register(UserInfo user)throws Exception{
        String urlPath = this.jetim.getApiUrl()+"/apigateway/users/register";
        String body = GsonUtil.toJson(user);
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(this.jetim.getAppkey(), this.jetim.getSecret(), urlPath);
        HttpUtil.setBodyParameter(body, conn);
        String response = "";
        UserTokenResult result  = null;
        try{
            response = HttpUtil.returnResult(conn);
            result = (UserTokenResult)GsonUtil.fromJson(response, UserTokenResult.class);
        }catch(Exception e){
            result = new UserTokenResult(500,"request:"+conn.getURL()+",response:"+response+",Exception:"+e.getMessage(),null);
        }
        return result;
    }

    public UserInfoResult get(String userId)throws Exception{
        String urlPath = this.jetim.getApiUrl()+"/apigateway/users/info?user_id="+URLEncoder.encode(userId, "UTF-8");
        HttpURLConnection conn = HttpUtil.CreateGetHttpConnection(this.jetim.getAppkey(), this.jetim.getSecret(),urlPath);
        String response = "";
        UserInfoResult result = null;
        try{
            response = HttpUtil.returnResult(conn);
            result = (UserInfoResult)GsonUtil.fromJson(response, UserInfoResult.class);
        }catch(Exception e){
            result = new UserInfoResult(500,"request:"+conn.getURL()+",response:"+response+",Exception:"+e.getMessage(),null);
        }
        return result;
    }
}
