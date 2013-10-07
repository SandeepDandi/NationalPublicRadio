package com.example.npr;

import java.util.List;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataManager {
	Context mContext;
	DatabaseHelper dbOpenHelper;
	SQLiteDatabase db;
	FavDAO FavDAO;
	
	public DataManager(Context mContext){
		this.mContext = mContext;
		dbOpenHelper = new DatabaseHelper(mContext);
		db = dbOpenHelper.getWritableDatabase();
		FavDAO = new FavDAO(db);
	}
	public void close(){
		db.close();
	}
	
	public long saveStory(Story story){
		return FavDAO.save(story);
	}
	public boolean exists(Story story){
		return FavDAO.exists(story);
	}
	public boolean deleteStory(Story story){
		return FavDAO.delete(story);
	}
	
	public Story getStory(String id){
		return FavDAO.get(id);
	}
	
	public List<Story> getAllStory(){
		return FavDAO.getAll();
	}

}
