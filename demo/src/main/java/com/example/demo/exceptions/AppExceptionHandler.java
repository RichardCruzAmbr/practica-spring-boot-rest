package com.example.demo.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.model.ErrorMessage;
import com.example.demo.ws.exception.UserServiceException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{
	/**
	 * Método de exception general
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
		String errorMsg = ex.getLocalizedMessage(); // String que obtiene el msg de error
		if(errorMsg == null) errorMsg = ex.toString();
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMsg);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Método de exception para NullPointerException
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = {NullPointerException.class})
	public ResponseEntity<Object> handleAnyNullPointerException(Exception ex, WebRequest request){
		String errorMsg = ex.getLocalizedMessage(); // String que obtiene el msg de error
		if(errorMsg == null) errorMsg = ex.toString();
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMsg);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Método de exception para excepción propia-> UserServiceException
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> handleAnyUserServiceException(Exception ex, WebRequest request){
		String errorMsg = ex.getLocalizedMessage(); // String que obtiene el msg de error
		if(errorMsg == null) errorMsg = ex.toString();
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMsg);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
