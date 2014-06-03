package com.example.eventostopo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class EventDetailActivity extends Activity {

	public static String EVENT_ID = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_detail);
		
		TextView t = new TextView(this);
		t = (TextView) findViewById(R.id.eventId);

		String newString;
		if (savedInstanceState == null) {
			Bundle extras = getIntent().getExtras();
			if (extras == null) {
				newString = null;
			} else {
				newString = extras.getString(EventDetailActivity.EVENT_ID);
			}
		} else {
			newString = (String) savedInstanceState
					.getSerializable(EventDetailActivity.EVENT_ID);
		}

		t.setText(newString);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_detail, menu);
		return true;
	}

}
