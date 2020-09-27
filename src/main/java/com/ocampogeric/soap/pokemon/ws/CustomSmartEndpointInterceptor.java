package com.ocampogeric.soap.pokemon.ws;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.SmartEndpointInterceptor;
import org.springframework.ws.server.endpoint.MethodEndpoint;

import com.ocampogeric.soap.pokemon.models.entities.BinnacleRegister;
import com.ocampogeric.soap.pokemon.models.services.IBinnacleService;

public class CustomSmartEndpointInterceptor implements SmartEndpointInterceptor {
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private IBinnacleService binnacleService;

	@Override
	public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean shouldIntercept(MessageContext messageContext, Object endpoint) {
		// TODO Auto-generated method stub
		MethodEndpoint methodEndpoint = (MethodEndpoint)endpoint;
		String methodName = methodEndpoint.getMethod().getName();
		LocalDateTime now  = LocalDateTime.now();
		String ipAddress = request.getHeader("X-FORWARDED-FOR") == null ? request.getRemoteAddr(): request.getHeader("X-FORWARDED-FOR");
		System.out.println(ipAddress);
		
		BinnacleRegister register = new BinnacleRegister();
		register.setMethod(methodName);
		register.setDate(now);
		register.setIp(ipAddress);
		binnacleService.save(register);
		return false;
	}

	

}
