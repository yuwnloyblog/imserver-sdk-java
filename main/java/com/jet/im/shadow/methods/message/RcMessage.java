package com.jet.im.shadow.methods.message;

import com.jet.im.JetIm;
import com.jet.im.shadow.methods.message.chatroom.RcChatroom;
import com.jet.im.shadow.methods.message._private.RcPrivate;

public class RcMessage {
    private JetIm jetim;
    public RcPrivate msgPrivate;
    public RcChatroom msgChatroom;
    public RcMessage(JetIm jetim){
        this.jetim = jetim;

        this.msgPrivate = new RcPrivate(this.jetim);
        this.msgChatroom = new RcChatroom(this.jetim);
    }

    
}
