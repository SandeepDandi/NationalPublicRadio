package com.example.npr;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StoryUtil {
	static public class StoriesJSONParser{		
		static ArrayList<Story> parseStories(String jsonString) throws JSONException{
			ArrayList<Story> stories = new ArrayList<Story>();	
			
			JSONObject aJSONObject = new JSONObject(jsonString);
			//JSONArray item1=aJSONObject.getJSONArray("item");
			JSONObject list= aJSONObject.getJSONObject("list");
			JSONArray itemsJSONArray = list.getJSONArray("story");
			for(int i=0; i<itemsJSONArray.length(); i++){
				JSONObject itemJSONObject = itemsJSONArray.getJSONObject(i);
				Story story = new Story(itemJSONObject);	
				stories.add(story);
			}
			return stories;
		}
	}
}
