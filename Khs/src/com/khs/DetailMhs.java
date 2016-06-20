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

public class DetailMhs extends Activity {
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
             	 Intent m10 = new Intent();
                 m10.setClassName("com.khs", "com.khs.Mhs");
                 startActivity(m10);
        	}
        });
   

    }


  public class AmbilData extends AsyncTask<String, String, String> {
   
  ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
  String nama, npm, alamat, jk,nope,angkatan, kls;

  
  @Override
  protected void onPreExecute() {
   super.onPreExecute();
   pDialog = new ProgressDialog(DetailMhs.this);
   pDialog.setMessage("Loading Data...");
   pDialog.setIndeterminate(false);
   pDialog.setCancelable(false);
   pDialog.show();
  }

  @Override
  protected String doInBackground(String... arg0) {
  
	  
	  Bundle extras = getIntent().getExtras();
  String npm_mhs = extras.getString("tf_npm");
  String url ="http://blogs-fahrudin1.rhcloud.com/androidkhs/detail.php?npm="+npm_mhs;


  JSONParser jParser = new JSONParser();


   JSONObject json = jParser.getJSONFromUrl(url);
   
   try {
    contacts = json.getJSONArray("mahasiswa");

    for (int i = 0; i < contacts.length(); i++) {
     JSONObject c = contacts.getJSONObject(i);
     HashMap<String, String> map = new HashMap<String, String>();
     
     String nama = c.getString("nama").trim();
     String npm = c.getString("npm").trim();
     String alamat = c.getString("alamat").trim();
     String jk = c.getString("jk").trim();
     String nope = c.getString("nope").trim();
     String angkatan = c.getString("angkatan").trim();
     String kls = c.getString("kls").trim();
     
     map.put("nama", nama);
     map.put("npm", npm);
     map.put("alamat", alamat);
     map.put("jk", jk);
     map.put("nope", nope);
     map.put("angkatan", angkatan);
     map.put("kls", kls);
     
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
     contactList, R.layout.detailmhs,
     new String[] { "nama", "npm", "alamat", "jk", "nope", "angkatan", "kls" }, new int[] { R.id.nama, R.id.npm, R.id.alamat, R.id.jk, R.id.nope, R.id.angkatan, R.id.kls });

   lv.setAdapter(adapter2);
   

  }
  

 }
 
     
}