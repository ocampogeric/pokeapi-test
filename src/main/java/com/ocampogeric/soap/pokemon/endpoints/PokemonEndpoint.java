package com.ocampogeric.soap.pokemon.endpoints;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ocampogeric.soap.pokemon.GetPokemonAbilitiesRequest;
import com.ocampogeric.soap.pokemon.GetPokemonAbilitiesResponse;
import com.ocampogeric.soap.pokemon.GetPokemonBaseExperienceRequest;
import com.ocampogeric.soap.pokemon.GetPokemonBaseExperienceResponse;
import com.ocampogeric.soap.pokemon.GetPokemonHeldItemsRequest;
import com.ocampogeric.soap.pokemon.GetPokemonHeldItemsResponse;
import com.ocampogeric.soap.pokemon.GetPokemonIdRequest;
import com.ocampogeric.soap.pokemon.GetPokemonIdResponse;
import com.ocampogeric.soap.pokemon.GetPokemonLocationAreaEncountersRequest;
import com.ocampogeric.soap.pokemon.GetPokemonLocationAreaEncountersResponse;
import com.ocampogeric.soap.pokemon.GetPokemonNameRequest;
import com.ocampogeric.soap.pokemon.GetPokemonNameResponse;
import com.ocampogeric.soap.pokemon.HeldItem;
import com.ocampogeric.soap.pokemon.LocationArea;
import com.ocampogeric.soap.pokemon.PokemonAbility;
import com.ocampogeric.soap.pokemon.exceptions.PokemonNotFoundException;
import com.ocampogeric.soap.pokemon.repositories.PokemonRepository;

@Endpoint
public class PokemonEndpoint {
	private static final String NAMESPACE_URI = "http://soap.ocampogeric.com/pokemon";

	private PokemonRepository pokemonRepository;
	
	private HttpServletRequest httpServletRequest;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.httpServletRequest = request;
    }

	@Autowired
	public PokemonEndpoint(PokemonRepository pokemonRepository) {
		this.pokemonRepository = pokemonRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonAbilitiesRequest")
	@ResponsePayload
	public GetPokemonAbilitiesResponse getAbilities(@RequestPayload GetPokemonAbilitiesRequest request)
			throws PokemonNotFoundException {
		GetPokemonAbilitiesResponse response = new GetPokemonAbilitiesResponse();
		List<PokemonAbility> abilities = pokemonRepository.getAbilities(request.getPokemonName());

		if (abilities == null) {
			throw new PokemonNotFoundException("Pokemon not found/Pokemon no encontrado");
		}

		response.getPokemonAbility().addAll(abilities);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonBaseExperienceRequest")
	@ResponsePayload
	public GetPokemonBaseExperienceResponse getBaseExperience(@RequestPayload GetPokemonBaseExperienceRequest request)
			throws PokemonNotFoundException {
		GetPokemonBaseExperienceResponse response = new GetPokemonBaseExperienceResponse();
		Integer baseExperience = pokemonRepository.getBaseExperience(request.getPokemonName());

		if (baseExperience == null) {
			throw new PokemonNotFoundException("Pokemon not found/Pokemon no encontrado");
		}

		response.setPokemonBaseExperience(baseExperience);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonHeldItemsRequest")
	@ResponsePayload
	public GetPokemonHeldItemsResponse getHeldItems(@RequestPayload GetPokemonHeldItemsRequest request)
			throws PokemonNotFoundException {
		GetPokemonHeldItemsResponse response = new GetPokemonHeldItemsResponse();
		List<HeldItem> heltItems = pokemonRepository.getHeldItems(request.getPokemonName());

		if (heltItems == null) {
			throw new PokemonNotFoundException("Pokemon not found/Pokemon no encontrado");
		}

		response.getPokemonHeldItem().addAll(heltItems);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonIdRequest")
	@ResponsePayload
	public GetPokemonIdResponse getPokemonId(@RequestPayload GetPokemonIdRequest request)
			throws PokemonNotFoundException {
		GetPokemonIdResponse response = new GetPokemonIdResponse();
		Integer pokemonId = pokemonRepository.getPokemonId(request.getPokemonName());

		if (pokemonId == null) {
			throw new PokemonNotFoundException("Pokemon not found/Pokemon no encontrado");
		}

		response.setPokemonId(pokemonId);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonNameRequest")
	@ResponsePayload
	public GetPokemonNameResponse getPokemonName(@RequestPayload GetPokemonNameRequest request)
			throws PokemonNotFoundException {
		GetPokemonNameResponse response = new GetPokemonNameResponse();
		String name = pokemonRepository.getPokemonName(request.getPokemonName());

		if (name == null) {
			throw new PokemonNotFoundException("Pokemon not found/Pokemon no encontrado");
		}

		response.setPokemonName(name);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonLocationAreaEncountersRequest")
	@ResponsePayload
	public GetPokemonLocationAreaEncountersResponse getLocationAreaEncounters(@RequestPayload GetPokemonLocationAreaEncountersRequest request) throws PokemonNotFoundException {
		GetPokemonLocationAreaEncountersResponse response = new GetPokemonLocationAreaEncountersResponse();
		List<LocationArea> locationsAreas = pokemonRepository.getLocationAreas(request.getPokemonName());
		
		if (locationsAreas == null) {
			throw new PokemonNotFoundException("Pokemon not found/Pokemon no encontrado");
		}
		
		response.getLocationArea().addAll(locationsAreas);
		return response;
	}
}
