package com.example.npr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends Activity {
	String urlTopicAPI="http://api.npr.org/list?id=3002&output=JSON";
	String urlProgramsAPI="http://api.npr.org/list?id=3004&output=JSON";
	ArrayList<Item> items;
	String load;
	ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		Bundle extra=getIntent().getExtras();
		String value=extra.getString("Value");
		list=(ListView)findViewById(R.id.listView1);
		Log.d("value",value);
		if(value.equals("programs"))
		{
			load="Loading Programs";
			new getList().execute(urlProgramsAPI);
		}
		if(value.equals("topics"))
		{
			load="Loading Topics";
			new getList().execute(urlTopicAPI);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}
	public void display()
	{
		ArrayList<String> AS =new ArrayList<String>();
		for(Item i:items)
		{
			String temp = null;
			JSONObject j=i.getTitle();
			try {
				temp= j.getString("$text");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			AS.add(temp);
		}
		list.setAdapter(new ColorAdapter(ListActivity.this,AS));
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Log.d("position",String.valueOf(position));
				Item it=items.get(position);
				int ids=it.getId();

				Intent i=new Intent(getBaseContext(),StoriesActivity.class);
				i.putExtra("Value", ""+ids+"");
				startActivity(i);			
			}				
		});
	}
	public static class ColorAdapter extends ArrayAdapter<String>{
		Context context;
		private ArrayList<String> storydata;
		public ColorAdapter(Context context,ArrayList<String> objects) {
			super(context, R.layout.item, objects);
			this.context = context;
			this.storydata= objects;
		}


		public int getCount() {
			// TODO Auto-generated method stub
			return storydata.size();
		}

		public String getItem(int position) {
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
				v = vi.inflate(R.layout.item, null);
			}


			TextView title = (TextView)v.findViewById(R.id.title11);

			String temp = storydata.get(position);
			title.setText(temp);                             

			return v;
		}

	}					
	public class  getList extends AsyncTask<String,Void,ArrayList<Item>>
	{
		ProgressDialog progressdialog= ProgressDialog.show(ListActivity.this,"",""+load+"");

		@Override
		protected ArrayList<Item> doInBackground(String... params) {
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
					items = ITemUtil.ItemsJSONParser.parseItems(sb.toString());
					return items;
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


		protected void onPostExecute(ArrayList<Item> result) {
			progressdialog.dismiss();
			if(items != null){
				Log.d("demo", items.toString());
				display();
			} else{
				Log.d("demo", "null result");
			}

		}

	}
}

