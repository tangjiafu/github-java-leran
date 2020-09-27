package com.debug.kill.api.response;

import com.debug.kill.api.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

	private Integer code;
	private String msg;
	private T data;

	public BaseResponse(Integer code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public BaseResponse(StatusCode statusCode){
		this.code = statusCode.getCode();
		this.msg = statusCode.getMsg();
	}

	public BaseResponse(StatusCode statusCode, T o){
		this.code = statusCode.getCode();
		this.msg = statusCode.getMsg();
		this.data = o;
	}


	public static BaseResponse succ(Object o){
		return new BaseResponse(StatusCode.Success, o);
	}

	public static BaseResponse fail(){
		return new BaseResponse(StatusCode.Fail);
	}
}
