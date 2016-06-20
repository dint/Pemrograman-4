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

public class Mhs extends Activity {
 ListView lv;
 ProgressDialog pDialog;
 JSONArray contacts = null;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.list);
        new AmbilData().execute();
        
        Button btn_back;
        btn_back=(Button) findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v){
        		finish();
             	 Intent z = new Intent();
                 z.setClassName("com.khs", "com.khs.MainActivity");
                 startActivity(z);
        	}
        });
   

    }


  public class AmbilData extends AsyncTask<String, String, String> {
   
  ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
  String nama, npm;

  
  @Override
  protected void onPreExecute() {
   super.onPreExecute();
   pDialog = new ProgressDialog(Mhs.this);
   pDialog.setMessage("Loading Data...");
   pDialog.setIndeterminate(false);
   pDialog.setCancelable(false);
   pDialog.show();
  }

  @Override
  protected String doInBackground(String... arg0) {
   
  String url ="http://blogs-fahrudin1.rhcloud.com/androidkhs/mhs.php";


  JSONParser jParser = new JSONParser();


   JSONObject json = jParser.getJSONFromUrl(url);
   
   try {
    contacts = json.getJSONArray("data");

    for (int i = 0; i < contacts.length(); i++) {
     JSONObject c = contacts.getJSONObject(i);
     HashMap<String, String> map = new HashMap<String, String>();
     
     String nama = c.getString("nama").trim();
     String npm = c.getString("npm").trim();
     
     map.put("nama", nama);
     map.put("npm", npm);
     
     
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
     contactList, R.layout.dmhs,
     new String[] { "nama", "npm" }, new int[] { R.id.nama_mhs, R.id.npm_mhs });

   lv.setAdapter(adapter2);
   
    
   lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	   @Override
		public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
		   finish();
			String npm1 = ((TextView) view.findViewById(R.id.npm_mhs)).getText().toString();       
     	 Intent mhs_detail = new Intent();
     	 mhs_detail.putExtra("tf_npm", npm1);
         mhs_detail.setClassName("com.khs", "com.khs.DetailMhs");
         startActivity(mhs_detail);

		}
	});
   
   

  }
  

 }
 
     
}