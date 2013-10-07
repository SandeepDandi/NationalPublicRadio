/*
 * Sandeep Dandi 
 * Abhijit Pasupuleti 
 * */

package com.example.npr;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button programs,topics,favorites,exit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		exit=(Button)findViewById(R.id.button4);
		programs=(Button)findViewById(R.id.button1);
		topics=(Button)findViewById(R.id.button2);
		favorites=(Button)findViewById(R.id.button3);
		programs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getBaseContext(),ListActivity.class);
				i.putExtra("Value", "programs");
				startActivity(i);
			}
		});
		topics.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getBaseContext(),ListActivity.class);
				i.putExtra("Value", "topics");
				startActivity(i);

			}
		});
		favorites.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getBaseContext(),StoriesActivity.class);
				i.putExtra("Value", "favorites");
				startActivity(i);
			}
		});
		exit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
