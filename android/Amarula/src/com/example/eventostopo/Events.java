package com.example.eventostopo;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Events extends Activity {

	ListView list;
	TextView description;
	TextView title;

	ArrayList<HashMap<String, String>> oslist = new ArrayList<HashMap<String, String>>();
	private static String url = "http://192.168.1.6:3000/api/events";

	private static final String TAG_TITLE = "title";
	private static final String TAG_DESCRIPTION = "description";
	private static final String TAG_ID = "id";

	JSONArray android = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
		setTitle("Lista de Eventos");

		oslist = new ArrayList<HashMap<String, String>>();
		new JSONParse().execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.events, menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.events, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.new_event:
			startActivity(new Intent(getApplicationContext(), EventCRUDActivity.class));;
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
	
	
	private class JSONParse extends AsyncTask<String, String, JSONObject> {
		private ProgressDialog pDialog;
		

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			title = (TextView) findViewById(R.id.title);
			description = (TextView) findViewById(R.id.description);
			pDialog = new ProgressDialog(Events.this);
			pDialog.setMessage("Getting Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected JSONObject doInBackground(String... args) {
			JSONParser jParser = new JSONParser();
			// Getting JSON from URL
			JSONObject json = jParser.getJSONFromUrl(url);
			return json;
		}

		@Override
		protected void onPostExecute(JSONObject json) {
			pDialog.dismiss();
			// try {
			// Getting JSON Array from URL
			try {
				android = json.getJSONArray("events");
				for (int i = 0; i < android.length(); i++) {
					JSONObject c = android.getJSONObject(i);
					// Storing JSON item in a Variable
					String description = c.getString(TAG_DESCRIPTION);
					String title = c.getString(TAG_TITLE);
					String eventId = c.getString(TAG_ID);

					// Adding value HashMap key => value
					HashMap<String, String> map = new HashMap<String, String>();
					map.put(TAG_TITLE, title);
					map.put(TAG_DESCRIPTION, description);
					map.put(TAG_ID, eventId);
					oslist.add(map);
					list = (ListView) findViewById(R.id.eventsList);
					ListAdapter adapter = new SimpleAdapter(Events.this,
							oslist, R.layout.event_element, new String[] {
									TAG_TITLE, TAG_DESCRIPTION }, new int[] {
									R.id.title, R.id.description });
					list.setAdapter(adapter);

					list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							Toast.makeText(
									Events.this,
									"You Clicked at "
											+ oslist.get(+position).get(
													"description"),
									Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(Events.this,
									EventDetailActivity.class);
							intent.putExtra(EventDetailActivity.EVENT_ID,
									oslist.get(+position).get("id"));
							// set the request code to any code you like,
							// you can identify the callback via this code
							startActivity(intent);
						}
					});
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
