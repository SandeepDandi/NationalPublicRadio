package com.example.npr;

import org.json.JSONException;
import org.json.JSONObject;

public class Item {
	@Override
	public String toString() {
		return "Item [type=" + type + ", title=" + title + ", additionalinfo="+ additionalinfo + ", id=" + id + ", num=" + num + "]";
	}

	String type;
	JSONObject title,additionalinfo;
	int id, num;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JSONObject getTitle() {
		return title;
	}

	public void setTitle(JSONObject title) {
		this.title = title;
	}

	public JSONObject getAdditionalinfo() {
		return additionalinfo;
	}

	public void setAdditionalinfo(JSONObject additionalinfo) {
		this.additionalinfo = additionalinfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	
	public Item(JSONObject itemJSONObject) throws JSONException{
		this.id = itemJSONObject.getInt("id");
		this.num = itemJSONObject.getInt("num");
		this.type = itemJSONObject.getString("type");
		this.title = itemJSONObject.getJSONObject("title");
		this.additionalinfo=itemJSONObject.getJSONObject("additionalInfo");
	}
	
	
	


}
