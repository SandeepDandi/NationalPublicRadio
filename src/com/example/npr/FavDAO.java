package com.example.npr;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FavDAO {
private SQLiteDatabase db;

	
	public FavDAO(SQLiteDatabase db){
		this.db = db;
	}
	
	public long save(Story story){
		ContentValues values = new ContentValues();

		values.put(FavTable.ID, story.getId());
		values.put(FavTable.TITLE, story.getTitle());
		values.put(FavTable.REPORTER, story.getByLine());
		values.put(FavTable.DATE, story.getPubDate());
		values.put(FavTable.TEASER, story.getTeaser());
		values.put(FavTable.MP3, story.getMp3());
		values.put(FavTable.LENGTH, story.getLength());
		values.put(FavTable.LINK, story.getLink());
		values.put(FavTable.MINITEASER, story.getMiniTeaser());
		return db.insert(FavTable.TABLE_NAME, null, values);
	}
	public boolean exists(Story story){
		Cursor c = db.query(true, FavTable.TABLE_NAME, 
				new String[]{FavTable.ID}, 
				FavTable.ID+"="+ story.getId(), null, null, null, null, null);

		if(c != null &&c.getCount()>0){
			if(!c.isClosed()){
				c.close();
			}
			return true;
		}else{ 
			if(!c.isClosed()){
				c.close();
			}
			return false;
		}			
	}
	
	
	public boolean delete(Story story){
		return db.delete(FavTable.TABLE_NAME, FavTable.ID+"="+ story.getId(), null)>0;
	}
	
	public Story get(String id){
		Story story = null;
		Cursor c = db.query(true, FavTable.TABLE_NAME, 
				new String[]{FavTable.ID, FavTable.TITLE, FavTable.REPORTER
				, FavTable.DATE, FavTable.TEASER, FavTable.MP3, 
				FavTable.LENGTH, FavTable.LINK,FavTable.MINITEASER}, 
				FavTable.ID+"="+ id, null, null, null, null, null);
		if(c != null){
			c.moveToFirst();
			story = this.buildNoteFromCursor(c);			
		}	
		
		if(!c.isClosed()){
			c.close();
		}		
		return story;
	}

	
	public List<Story> getAll(){
		List<Story> list = new ArrayList<Story>();
		Cursor c = db.query(FavTable.TABLE_NAME, 
				new String[]{FavTable.ID, FavTable.TITLE, FavTable.REPORTER
				, FavTable.DATE, FavTable.TEASER, FavTable.MP3, 
				FavTable.LENGTH, FavTable.LINK,FavTable.MINITEASER}, 
				null, null, null, null, null);
		if(c != null){
			c.moveToFirst();			
			do{
				Story story = this.buildNoteFromCursor(c);
				if(story != null){
					list.add(story);
				}				
			} while(c.moveToNext());
			
			if(!c.isClosed()){
				c.close();
			}
		}
		return list;
	}
	
	private Story buildNoteFromCursor(Cursor c){
		Story story = null;		
		if(c!= null && c.getCount()>0){
			story = new Story();
			story.setId(c.getInt(0));
			story.setTitle(c.getString(1));
			story.setByLine(c.getString(2));
			story.setPubDate(c.getString(3));
			story.setTeaser(c.getString(4));
			story.setMp3(c.getString(5));
			story.setLength(c.getString(6));
			story.setLink(c.getString(7));
			story.setMiniTeaser(c.getString(8));
			
		}
		return story;
	}

}
