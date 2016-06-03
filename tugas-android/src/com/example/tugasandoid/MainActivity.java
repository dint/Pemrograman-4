package com.example.tugasandoid;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

 public class MainActivity extends ListActivity {
    	private static String url = "http://jsonplaceholder.typicode.com/posts";
    	private static final String TAG_USERID = "userId";
    	private static final String TAG_ID = "id";
    	private static final String TAG_TITLE = "title";
    	private static final String TAG_BODY = "body";
    	private JSONArray jArray;
    	private ArrayList<HashMap<String, String>> userId;
    	
    	@Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            
            JSONParser jParser = new JSONParser();
            JSONObject jURL = jParser.getJSONFromUrl(url);
            ArrayList<HashMap<String, String>> userId = new ArrayList<HashMap<String, String>>();
        	try {
        		jArray = jURL.getJSONArray(TAG_USERID);
        		for (int i = 0; i < jArray.length(); i++) {
        			JSONObject job = jArray.getJSONObject(i);
        			String id = job.getString("id");
        			String title = job.getString("title");
        			String body = job.getString("body");				

        			HashMap<String, String> hash = new HashMap<String, String>();
        			hash.put(TAG_ID, id);
        			hash.put(TAG_TITLE, title);
        			hash.put(TAG_BODY, body);

        			userId.add(hash);
        		}
        	} catch (JSONException e) {
        		e.printStackTrace();
        	}
        		this.adapter_listview();	
        	}
    
    	private void adapter_listview() {
    		ListAdapter adapter = new SimpleAdapter(this, userId, R.layout.list_item,
    				new String[] { TAG_ID, TAG_TITLE, TAG_BODY }, new int[] {
    				R.id.id, R.id.title, R.id.body });
    		setListAdapter(adapter);	
  		
    	}
 }  	