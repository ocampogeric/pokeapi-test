package com.ocampogeric.soap.pokemon.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ocampogeric.soap.pokemon.HeldItem;
import com.ocampogeric.soap.pokemon.LocationArea;
import com.ocampogeric.soap.pokemon.PokemonAbility;
import com.ocampogeric.soap.pokemon.Utils;

@Component
public class PokemonRepository {
	
	
	public List<PokemonAbility> getAbilities(String pokemonName) {
		List<PokemonAbility> abilities = new ArrayList<PokemonAbility>();
		
		JsonObject pokemon = Utils.getPokemonByName(pokemonName.toLowerCase());
		if (pokemon == null) {
			return null;
		}
		
		if (pokemon.has("abilities") && pokemon.get("abilities").getAsJsonArray().size() > 0) {
			PokemonAbility pokemonAbility = null;
			
			for (JsonElement e : pokemon.get("abilities").getAsJsonArray()) {
				JsonObject obj = e.getAsJsonObject();
				JsonObject abilityObj = obj.get("ability").getAsJsonObject();
				
				pokemonAbility = new PokemonAbility();
				pokemonAbility.setName(abilityObj.get("name").getAsString());
				pokemonAbility.setUrl(abilityObj.get("url").getAsString());
				pokemonAbility.setIsHidden(obj.get("is_hidden").getAsBoolean());
				
				abilities.add(pokemonAbility);
			}
		}
		return abilities;
	}
	
	public Integer getBaseExperience(String pokemonName) {
		Integer baseExperience = null;
		
		JsonObject pokemon = Utils.getPokemonByName(pokemonName.toLowerCase());
		if (pokemon == null) {
			return null;
		}
		
		if (pokemon.has("base_experience")) {
			baseExperience = pokemon.get("base_experience").getAsInt();
		}
		
		return baseExperience;
	}
	
	public List<HeldItem> getHeldItems(String pokemonName) {
		List<HeldItem> heldItems = new ArrayList<HeldItem>();
		
		JsonObject pokemon = Utils.getPokemonByName(pokemonName.toLowerCase());
		if (pokemon == null) {
			return null;
		}
		
		if (pokemon.has("held_items") && pokemon.get("held_items").getAsJsonArray().size() > 0) {
			HeldItem item = null;
			for (JsonElement e : pokemon.get("held_items").getAsJsonArray()) {
				JsonObject obj = e.getAsJsonObject();
				JsonObject itemObj = obj.get("item").getAsJsonObject();
				
				item = new HeldItem();
				item.setName(itemObj.get("name").getAsString());
				item.setUrl(itemObj.get("url").getAsString());
				
				heldItems.add(item);
			}
		}
		return heldItems;
	}
	
	public Integer getPokemonId(String pokemonName) {
		Integer pokemonId = null;
		
		JsonObject pokemon = Utils.getPokemonByName(pokemonName.toLowerCase());
		if (pokemon == null) {
			return null;
		}
		
		if (pokemon.has("id")) {
			pokemonId = pokemon.get("id").getAsInt();
		}
		
		return pokemonId;
	}
	
	public String getPokemonName(String pokemonName) {
		String name = null;
		
		JsonObject pokemon = Utils.getPokemonByName(pokemonName.toLowerCase());
		if (pokemon == null) {
			return null;
		}
		
		if (pokemon.has("name")) {
			name = pokemon.get("name").getAsString();
		}
		return name;
	}
	
	public List<LocationArea> getLocationAreas(String pokemonName) {
		List<LocationArea> locationAreas = new ArrayList<LocationArea>();
		
		JsonObject pokemon = Utils.getPokemonByName(pokemonName.toLowerCase());
		if (pokemon == null) {
			return null;
		}
		
		if (pokemon.has("location_area_encounters")) {
			JsonObject locationAreaObject = Utils.getJsonObjectByUrl("/pokemon/" + pokemon.get("id").getAsString() + "/encounters");
			
			if (locationAreaObject.has("items") && locationAreaObject.get("items").getAsJsonArray().size() > 0) {
				LocationArea item = null;
				for (JsonElement e : locationAreaObject.get("items").getAsJsonArray()) {
					JsonObject obj = e.getAsJsonObject();
					JsonObject itemObj = obj.get("location_area").getAsJsonObject();
					
					item = new LocationArea();
					item.setName(itemObj.get("name").getAsString());
					item.setUrl(itemObj.get("url").getAsString());
					
					locationAreas.add(item);
				}
			}
		}
		return locationAreas;
	}
	
	
	
}
