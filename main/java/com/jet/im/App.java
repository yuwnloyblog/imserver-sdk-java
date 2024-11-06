package com.jet.im;

import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import com.jet.im.shadow.RcJetIm;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        RcJetIm sdk = new RcJetIm("appkey", "appsecret", "http://127.0.0.1:8082");
        try {
            TokenResult result = sdk.user.register(new UserModel("userid1", "user1", ""));

           System.out.println(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}