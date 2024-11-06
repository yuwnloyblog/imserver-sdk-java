package com.jet.im;

<<<<<<< HEAD:main/java/com/juggle/im/App.java
import com.juggle.im.shadow.RcJuggleIm;

import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
=======
import com.jet.im.models.user.UserInfo;
import com.jet.im.models.user.UserInfoResult;
import com.jet.im.models.user.UserTokenResult;
>>>>>>> dev:main/java/com/jet/im/App.java

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
<<<<<<< HEAD:main/java/com/juggle/im/App.java
        RcJuggleIm sdk = new RcJuggleIm("appkey", "appsecret", "http://127.0.0.1:8082");
=======
        JetIm sdk = new JetIm("appkey", "appsecret", "http://127.0.0.1:8082");
>>>>>>> dev:main/java/com/jet/im/App.java
        try {
            TokenResult result = sdk.user.register(new UserModel("userid1", "user1", ""));

           System.out.println(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}