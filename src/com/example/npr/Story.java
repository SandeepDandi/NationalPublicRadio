package com.example.npr;




import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Story {



@Override
	public String toString() {
		return "Story [title=" + title + ", teaser=" + teaser + ", miniTeaser="
				+ miniTeaser + ", pubDate=" + pubDate + ", storyDate="
				+ storyDate + ", mp3=" + mp3 + ", byLine=" + byLine
				+ ", length=" + length + ", link=" + link + ", id=" + id + "]";
	}

private String title,teaser,miniTeaser,pubDate,storyDate,mp3="No audio";
String byLine="no Value";
String length="no Value";
private String link="No link";
private int id;
public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getTeaser() {
	return teaser;
}

public void setTeaser(String teaser) {
	this.teaser = teaser;
}

public String getMiniTeaser() {
	return miniTeaser;
}

public void setMiniTeaser(String miniTeaser) {
	this.miniTeaser = miniTeaser;
}

public String getPubDate() {
	return pubDate;
}

public void setPubDate(String pubDate) {
	this.pubDate = pubDate;
}

public String getStoryDate() {
	return storyDate;
}

public void setStoryDate(String storyDate) {
	this.storyDate = storyDate;
}

public String getMp3() {
	return mp3;
}

public void setMp3(String mp3) {
	this.mp3 = mp3;
}

public String getByLine() {
	return byLine;
}

public void setByLine(String byLine) {
	this.byLine = byLine;
}

public String getLength() {
	return length;
}

public void setLength(String length) {
	this.length = length;
}

public String getLink() {
	return link;
}

public void setLink(String link) {
	this.link = link;
}




	public Story(JSONObject itemJSONObject)  {
		// TODO Auto-generated constructor stub
	
		try {
			this.id=itemJSONObject.getInt("id");
			this.title= itemJSONObject.getJSONObject("title").getString("$text");
			this.teaser= itemJSONObject.getJSONObject("teaser").getString("$text");
			this.miniTeaser= itemJSONObject.getJSONObject("miniTeaser").getString("$text");
			this.storyDate = itemJSONObject.getJSONObject("storyDate").getString("$text");
			this.pubDate = itemJSONObject.getJSONObject("pubDate").getString("$text");
			this.byLine=itemJSONObject.getJSONArray("byline").getJSONObject(0).getJSONObject("name").getString("$text");
			this.mp3=itemJSONObject.getJSONArray("audio").getJSONObject(0).getJSONObject("format").getJSONArray("mp3").getJSONObject(0).getString("$text");
			this.link=itemJSONObject.getJSONArray("link").getJSONObject(0).getString("$text");
			this.length=itemJSONObject.getJSONArray("audio").getJSONObject(0).getJSONObject("duration").getString("$text");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("error",e.toString());
			e.printStackTrace();
		}
		
	}

	public Story() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	

}
