package com.ocampogeric.soap.pokemon;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class Utils {
	public static final String POKEAPI_URL = "https://pokeapi.co/api/v2";
	
	public static JsonObject getPokemonByName(String name) {
		Gson gson = new GsonBuilder().create();
		
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(Utils.POKEAPI_URL + "/pokemon/" + name)
				.build();
		
		Response response;
		String jsonStr;
		try {
			response = client.newCall(request).execute();
			jsonStr = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		if (jsonStr.equals("Not Found")) {
			return null;
		}
		JsonElement element = gson.fromJson (jsonStr, JsonElement.class);
		JsonObject pokemonJsonObject = element.getAsJsonObject();
		
		return pokemonJsonObject;
	}
	
	public static JsonObject getJsonObjectByUrl(String apiUrl) {
		Gson gson = new GsonBuilder().create();
		
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(Utils.POKEAPI_URL + apiUrl)
				.build();
		
		Response response;
		String jsonStr;
		try {
			response = client.newCall(request).execute();
			jsonStr = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		if (jsonStr.equals("Not Found")) {
			return null;
		}
		JsonElement element = gson.fromJson (jsonStr, JsonElement.class);
		JsonObject jsonObject = new JsonObject();
		
		if (element.isJsonArray()) {
			jsonObject.add("items", element.getAsJsonArray());
		} else {
			jsonObject = element.getAsJsonObject();
		}
		
		return jsonObject;
	}
}
