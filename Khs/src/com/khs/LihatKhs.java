package com.khs;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class LihatKhs extends Activity {
 ListView lv;
 ProgressDialog pDialog;
 JSONArray contacts = null;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.lihat);
        lv=(ListView)findViewById(R.id.list);
        new AmbilData().execute();
        
        Button btn_back;
        btn_back=(Button) findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v){
        		finish();
             	 Intent m10 = new Intent();
                 m10.setClassName("com.khs", "com.khs.MainActivity");
                 startActivity(m10);
        	}
        });
   

    }


  public class AmbilData extends AsyncTask<String, String, String> {
   
  ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
  String npm,nama;

  
  @Override
  protected void onPreExecute() {
   super.onPreExecute();
   pDialog = new ProgressDialog(LihatKhs.this);
   pDialog.setMessage("Loading Data...");
   pDialog.setIndeterminate(false);
   pDialog.setCancelable(false);
   pDialog.show();
  }

  @Override
  protected String doInBackground(String... arg0) {
  
	  
	  Bundle extras = getIntent().getExtras();
	  String angkatan = extras.getString("tf_angkatan");
  String url = "http://blogs-fahrudin1.rhcloud.com/androidkhs/index.php?angkat="+angkatan;


  JSONParser jParser = new JSONParser();


   JSONObject json = jParser.getJSONFromUrl(url);
   
   try {
    contacts = json.getJSONArray("data");

    for (int i = 0; i < contacts.length(); i++) {
     JSONObject c = contacts.getJSONObject(i);
     HashMap<String, String> map = new HashMap<String, String>();
     
     String npm = c.getString("npm").trim();
     String nama = c.getString("nama").trim();
     
     map.put("npm", npm);
     map.put("nama", nama);
     
     
     contactList.add(map);
    }

   } catch (JSONException e) {
    
    
   }

   return null;
  }

  @Override
  protected void onPostExecute(String result) {
   // TODO Auto-generated method stub
	  
   super.onPostExecute(result);
   
   pDialog.dismiss();
   
   ListAdapter adapter2 = new SimpleAdapter(getApplicationContext(),
     contactList, R.layout.list_khs,
     new String[] { "nama", "npm" }, new int[] { R.id.nama, R.id.npm });

   lv.setAdapter(adapter2);
   
   lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	   @Override
		public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
		   finish();
			String npm12 = ((TextView) view.findViewById(R.id.npm)).getText().toString();       
     	 Intent sm = new Intent();
     	 sm.putExtra("tf_npmk", npm12);
         sm.setClassName("com.khs", "com.khs.Semester");
         startActivity(sm);

		}
	});   
   

  }
  

 }
 
     
}