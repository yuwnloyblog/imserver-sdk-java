package com.juggle.im.models;

import com.juggle.im.util.GsonUtil;

public class ResponseResult extends Result{
    private String reqBody;
    
	public ResponseResult(Integer code, String msg) {
		super(code, msg);
		this.code = code;
		this.errorMessage = msg;
	}

	
	public String getReqBody() {
        return reqBody;
    }


    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }

    @Override
	public String toString() {
		return GsonUtil.toJson(this, ResponseResult.class);
	}
}
