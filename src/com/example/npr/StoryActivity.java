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

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.TextView;
import android.widget.Toast;

public class StoryActivity extends Activity implements OnPreparedListener, MediaPlayerControl {
	TextView t,t1,t2,t3,t4;
	ImageView i,i1,i2,i3;
	storyobject s;
	public Story storyobject;
	private MediaPlayer mediaPlayer;
	private MediaController mediaController;
	public DataManager dm;
	public boolean flag;
	private Handler handler = new Handler();
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mediaController.show();
		return false;
	}
	@Override
	public void onResume() {
		super.onResume();  // Always call the superclass method first
		if(mediaPlayer!=null || mediaPlayer.isPlaying()==true)
		{//mediaPlayer.reset();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story);
		s= (storyobject) getIntent().getSerializableExtra("story");
		storyobject=new Story();
		storyobject.setTitle(s.getTitle());
		storyobject.setByLine(s.getByline());
		storyobject.setTeaser(s.getTeaser());
		storyobject.setLength(s.getLength());
		storyobject.setLink(s.getLink());
		storyobject.setMiniTeaser(s.getMiniteaser());
		storyobject.setMp3(s.getMp3());
		storyobject.setPubDate(s.getPubdate());
		storyobject.setId(s.getId());
		Log.d("storyobject", s.toString());
		t=(TextView)findViewById(R.id.textView11);
		t1=(TextView)findViewById(R.id.textView22);
		t2=(TextView)findViewById(R.id.textView33);
		t3=(TextView)findViewById(R.id.textView44);
		t4=(TextView)findViewById(R.id.textView55);
		i=(ImageView)findViewById(R.id.imageView1);
		i1=(ImageView)findViewById(R.id.imageView2);
		i2=(ImageView)findViewById(R.id.imageView3);
		i3=(ImageView)findViewById(R.id.imageView4);
		t.setText(s.getTitle());
		if((s.getByline().equalsIgnoreCase("no Value"))!=true)
		{
			t1.setText(s.getByline());
		}
		t2.setText(s.getPubdate());
		if((s.getLength().equalsIgnoreCase("no Value"))!=true)
		{
			String length=s.getLength();
			int i=Integer.parseInt(length);
			int b=(i/60);
			int c=(i%60);
			t3.setText(b+" min "+c+"sec");
		}
		t4.setText(s.getTeaser());
		i.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				finish();
			}
		});
		i1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if(s.getLink().equalsIgnoreCase("No link")!=true)
				{
					Uri url = Uri.parse(s.getLink());
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW, url);
					startActivity(launchBrowser);
				}
				else
				{
					Toast.makeText(getApplicationContext(), "No Link found ", Toast.LENGTH_LONG).show();
				}

			}
		});
		i2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				{
					if(s.getMp3().equals("No audio"))
					{
						Toast.makeText(getApplicationContext(), "There is no Mp3!!! ", Toast.LENGTH_LONG).show();	
					}
					else{
						String url = s.getMp3();
						new AsyncAudioGet().execute(url);
					}

				}
			}
		});
		dm=new DataManager(this);

		if(dm.exists(storyobject))
		{
			i3.setImageResource(R.drawable.b);
			flag=true;

		}
		else
		{
			i3.setImageResource(R.drawable.c);
			flag=false;
		}
		i3.setClickable(true);
		i3.setOnClickListener(new View.OnClickListener()
		{

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(flag)
				{
					i3.setImageResource(R.drawable.c);
					dm.deleteStory(storyobject);
					flag=false;

				}
				else
				{
					dm.saveStory(storyobject);
					flag=true;
					i3.setImageResource(R.drawable.b);
				}
			}

		});
		@SuppressWarnings("unused")
		String url = "http://pd.npr.org/anon.npr-mp3/npr/atc/2013/04/20130407_atc_04.mp3";
		mediaPlayer = new MediaPlayer();
		mediaController = new MediaController(this);
		mediaPlayer.setOnPreparedListener(this);	


	}
	class AsyncAudioGet extends AsyncTask<String, Void, String>{
		ProgressDialog progressdialog= ProgressDialog.show(StoryActivity.this,"","Loading Media");
		@Override
		protected String doInBackground(String... params) {
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
					String programs=sb.toString();
					Log.d("music string", "music "+programs);
					return programs;
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			progressdialog.dismiss();
			try {
				mediaPlayer.setDataSource(result);
				mediaPlayer.prepareAsync();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.story, menu);
		return true;
	}
	@Override
	public boolean canPause() {
		return true;
	}

	@Override
	public boolean canSeekBackward() {
		return true;
	}

	@Override
	public boolean canSeekForward() {
		return true;
	}

	@Override
	public int getBufferPercentage() {
		return 0;
	}

	@Override
	public int getCurrentPosition() {
		try{
			return mediaPlayer.getCurrentPosition();
		}
		catch(IllegalStateException e) { 
			Log.d("IllegalStateException", e.toString());
		}
		return 0;
	}

	@Override
	public int getDuration() {
		try{

			return mediaPlayer.getDuration();
		}
		catch(IllegalStateException e) { 
			Log.d("IllegalStateException", e.toString());
		}
		return 0;
	}

	@Override
	public boolean isPlaying() {
		try{
			return mediaPlayer.isPlaying();
		}
		catch(IllegalStateException e) { 
			Log.d("IllegalStateException", e.toString());
		}
		return false;
	}

	@Override
	public void pause() {
		try{
			mediaPlayer.pause();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void seekTo(int pos) {
		try{
			mediaPlayer.seekTo(pos);}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
		try{
			mediaPlayer.start();}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		mediaPlayer.start();
		mediaController.setMediaPlayer(this);
		mediaController.setAnchorView(findViewById(R.id.LinearLayout1));

		handler.post(new Runnable() {
			public void run() {
				mediaController.setEnabled(true);
				mediaController.show();
			}
		});
	}

	@Override
	protected void onStop() {
		super.onStop();
		try{
			mediaController.hide();
			mediaPlayer.stop();
			mediaPlayer.release();}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void onBackPressed() {
		super.onBackPressed();

		mediaPlayer.release();

	}


}
