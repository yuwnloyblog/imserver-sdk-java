package com.jet.im;

import com.jet.im.models.user.UserInfo;
import com.jet.im.models.user.UserInfoResult;
import com.jet.im.models.user.UserTokenResult;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JetIm sdk = new JetIm("appkey", "appsecret", "http://127.0.0.1:8082");
        try {
           UserTokenResult result = sdk.user.register(new UserInfo("userid1", "user1", ""));

           System.out.println(result.toString());
           System.out.println(result.getUserToken().getToken());

           UserInfoResult uResult = sdk.user.get("userId1");
           System.out.println(uResult.toString());
           System.out.println(uResult.getUserInfo().getNickname());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
