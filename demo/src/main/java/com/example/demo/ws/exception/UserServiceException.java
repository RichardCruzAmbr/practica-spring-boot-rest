package com.example.demo.ws.exception;

public class UserServiceException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4232151145160748016L;

	public UserServiceException(String msg){
		super(msg);
	}
}
