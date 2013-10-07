package com.example.npr;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ITemUtil {
	static public class ItemsJSONParser{		
		static ArrayList<Item> parseItems(String jsonString) throws JSONException{
			ArrayList<Item> items = new ArrayList<Item>();	
			
			JSONObject aJSONObject = new JSONObject(jsonString);
			//JSONArray item1=aJSONObject.getJSONArray("item");
			JSONArray itemsJSONArray = aJSONObject.getJSONArray("item");
			for(int i=0; i<itemsJSONArray.length(); i++){
				JSONObject itemJSONObject = itemsJSONArray.getJSONObject(i);
				Item item = new Item(itemJSONObject);	
				items.add(item);
			}
			return items;
		}
	}

}
