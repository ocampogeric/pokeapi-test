package com.ocampogeric.soap.pokemon.exceptions;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM,
			customFaultCode = "{"+ PokemonNotFoundException.NAMESPACE_URI +"} 404",
			faultStringOrReason = "Pokemon Not found")
public class PokemonNotFoundException  extends Exception {
	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE_URI = "http://soap.ocampogeric.com/exception";
	
	public PokemonNotFoundException(String message) {
        super(message);
    }
}
