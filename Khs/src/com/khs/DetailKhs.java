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
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DetailKhs extends Activity {
 ListView lv;
 ProgressDialog pDialog;
 JSONArray contacts = null;
 
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.detail);
        lv=(ListView)findViewById(R.id.list);
        new AmbilData().execute();
        
        Button btn_back;
        btn_back=(Button) findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v){
        		finish();
             	 Intent m2 = new Intent();
                 m2.setClassName("com.khs", "com.khs.MainActivity");
                 startActivity(m2);
        	}
        });
   

    }


  public class AmbilData extends AsyncTask<String, String, String> {
   
  ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
  String nama,npm, semester, nama_mk, nilai;

  @Override
  protected void onPreExecute() {
   super.onPreExecute();
   pDialog = new ProgressDialog(DetailKhs.this);
   pDialog.setMessage("Loading Data...");
   pDialog.setIndeterminate(false);
   pDialog.setCancelable(false);
   pDialog.show();
  }

  @Override
  protected String doInBackground(String... arg0) {
	  
	  
	  Bundle extras = getIntent().getExtras();
	  if (extras != null) {
	  String semdt = extras.getString("tf_semdt"); 
	  String npmdt = extras.getString("tf_npmdt"); 
	  String mkdt = extras.getString("tf_mkdt"); 
  String url ="http://blogs-fahrudin1.rhcloud.com/androidkhs/nilai.php?semdt="+semdt+"&npmdt="+npmdt+"&mkdt="+mkdt;


  JSONParser jParser = new JSONParser();


   JSONObject json = jParser.getJSONFromUrl(url);
   
   try {
    contacts = json.getJSONArray("data");

    for (int i = 0; i < contacts.length(); i++) {
     JSONObject c = contacts.getJSONObject(i);
     HashMap<String, String> map = new HashMap<String, String>();

     String nama = c.getString("nama").trim();
     String npm = c.getString("npm").trim();
     String semester = c.getString("semester").trim();
     String nama_mk = c.getString("nama_mk").trim();
     String nilai = c.getString("nilai").trim();
     
     map.put("nama", nama);
     map.put("npm", npm);
     map.put("semester", semester);
     map.put("nama_mk", nama_mk);
     map.put("nilai", nilai);
     
     
     contactList.add(map);
    }

   } catch (JSONException e) {
    
    
   }
	  }
   return null;
	  
  }

  @Override
  protected void onPostExecute(String result) {
   // TODO Auto-generated method stub
   super.onPostExecute(result);
   
   pDialog.dismiss();
   
   ListAdapter adapter2 = new SimpleAdapter(getApplicationContext(),
     contactList, R.layout.detailkhs,
     new String[] { "nama", "npm", "semester", "nama_mk", "nilai" }, new int[] { R.id.nama, R.id.npm, R.id.semester, R.id.nama_mk, R.id.nilai });

   lv.setAdapter(adapter2);

  }
  

 }
 
     
}