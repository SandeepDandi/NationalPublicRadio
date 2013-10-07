/*
 * Sandeep Dandi 
 * Abhijit Pasupuleti 
 * */

package com.example.npr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class StoriesActivity extends Activity {
	ListView list;
	String url;
	String value;
	ArrayList<Story> stories=new ArrayList<Story>();
	final static String QUESTIONS_KEY = "story";
	public DataManager dm;
	ArrayList<Story> temp1;
	ArrayList<Story> result=new ArrayList<Story>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stories);
		list=(ListView)findViewById(R.id.listView1);
		dm=new DataManager(this);
		Bundle extra=getIntent().getExtras();
		value=extra.getString("Value");
		Log.d("value",value);
		url="http://api.npr.org/query?id="+value+"&output=JSON&apiKey=MDExMjUwNjA5MDEzNjY1MDU4MDk5OTRjZg001";
		if(value.equals("favorites"))
		{			
			result=(ArrayList<Story>) dm.getAllStory();
			Log.d("results",result.toString());
			display(result);
		}
		else{
			new getList().execute(url);
		}
	}



	public void display(ArrayList<Story> temp)
	{
		if(temp.size()>0){
		temp1=temp;
		list.setAdapter(new ColorAdapter(StoriesActivity.this,temp));
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
				Log.d("position",String.valueOf(position));
				Story it;
				it=temp1.get(position);
				Log.d("demo1", it.getLink());
				storyobject so=new storyobject();
				so.setId(it.getId());
				so.setByline(it.getByLine());
				so.setLength(it.getLength());
				Log.d("mp3",it.getMp3());
				so.setMp3(it.getMp3());
				so.setPubdate(it.getPubDate());
				so.setTeaser(it.getTeaser());
				so.setTitle(it.getTitle());
				so.setLink(it.getLink());
				so.setMiniteaser(it.getMiniTeaser());
				Intent i=new Intent(getBaseContext(),StoryActivity.class);
				i.putExtra("story",so);
				startActivity(i);
			}
		});
		}
		else{
			Toast.makeText(getApplicationContext(), "No Stories to display... ", Toast.LENGTH_LONG).show();
		}
	}

	public static class ColorAdapter extends ArrayAdapter<Story>{
		Context context;
		private ArrayList<Story> storydata;
		public ColorAdapter(Context context,ArrayList<Story> objects) {
			super(context, R.layout.item_row_layout, objects);
			this.context = context;
			this.storydata= objects;
		}
		public int getCount() {
			// TODO Auto-generated method stub
			return storydata.size();
		}

		public Story getItem(int position) {
			// TODO Auto-generated method stub
			return storydata.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v = convertView;
			if (v == null)
			{
				LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.item_row_layout, null);
			}
			TextView title = (TextView)v.findViewById(R.id.title);
			TextView pub = (TextView)v.findViewById(R.id.pub);
			TextView tease = (TextView)v.findViewById(R.id.tease);

			Story temp = storydata.get(position);

			title.setText(temp.getTitle());
			pub.setText(temp.getPubDate());
			tease.setText(temp.getMiniTeaser());

			return v;
		}

	}					



	public class  getList extends AsyncTask<String,Void,ArrayList<Story>>
	{
		ProgressDialog progressdialog= ProgressDialog.show(StoriesActivity.this,"","Loading Stories");

		@Override
		protected ArrayList<Story> doInBackground(String... params) {
			// TODO Auto-generated method stub

			String urlString = params[0];
			try {
				URL url = new URL(urlString);			
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.connect();			
				int statusCode = con.getResponseCode();
				if (statusCode == HttpURLConnection.HTTP_OK) {				
					BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
					StringBuilder sb = new StringBuilder();
					String line = reader.readLine();
					while (line != null) {
						sb.append(line);
						line = reader.readLine();
					}
					stories = StoryUtil.StoriesJSONParser.parseStories(sb.toString());
					return stories;
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return null;
		}


		protected void onPostExecute(ArrayList<Story> result) {
			progressdialog.dismiss();
			if(stories != null){
				Log.d("demo", stories.toString());

			} else{
				Log.d("demo", "null result");
			}
			display(stories);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stories, menu);
		return true;
	}

}
