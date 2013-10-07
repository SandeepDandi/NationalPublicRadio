package com.example.npr;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class FavTable {

	static final String ID = "id";
	static final String TABLE_NAME = "favorite";
	static final String TITLE = "title";
	static final String REPORTER = "byLine";
	static final String DATE = "pubDate";
	static final String TEASER = "teaser";
	static final String MP3 = "mp3";
	static final String LENGTH = "length";
	static final String LINK = "link";
	static final String MINITEASER="miniteaser";
	
	static public void onCreate(SQLiteDatabase db){		
		StringBuilder sb = new StringBuilder();		
		sb.append("CREATE TABLE " + FavTable.TABLE_NAME + " (");
		
		sb.append(FavTable.ID + " integer primary key not null, ");
		sb.append(FavTable.TITLE + " text , ");
		sb.append(FavTable.REPORTER + " text , ");
		sb.append(FavTable.DATE + " text,");
		sb.append(FavTable.TEASER + " text , ");
		sb.append(FavTable.MP3 + " text , ");
		sb.append(FavTable.LENGTH + " text , ");
		sb.append(FavTable.LINK + " text, ");
		sb.append(FavTable.MINITEASER+" text ");
		sb.append(");");		
		try{
			db.execSQL(sb.toString());
		} catch (SQLException e){				
			e.printStackTrace();
		}
	}

}
