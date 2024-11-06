package com.jet.im.shadow;

import com.jet.im.JetIm;
import com.jet.im.shadow.methods.chatroom.RcChatroom;
import com.jet.im.shadow.methods.message.RcMessage;
import com.jet.im.shadow.methods.user.RcUser;

public class RcJetIm {
    public JetIm jteim;

    public RcUser user;
    public RcChatroom chatroom;
    public RcMessage message;
    public RcJetIm(String appkey, String appSecret, String apiUrl){
        this.jetim = new JetIm(appkey, appSecret, apiUrl);
        this.user = new RcUser(this.jetim);
        this.chatroom = new RcChatroom(this.jetim);
        this.message = new RcMessage(this.jetim);
    }

    public JetIm getJetIm(){
        return this.jetim;
    }
}
