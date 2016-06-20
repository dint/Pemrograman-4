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

public class Matkul extends Activity {
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
  String id_mk,npm,semester,nama_mk;

  
  @Override
  protected void onPreExecute() {
   super.onPreExecute();
   pDialog = new ProgressDialog(Matkul.this);
   pDialog.setMessage("Loading Data...");
   pDialog.setIndeterminate(false);
   pDialog.setCancelable(false);
   pDialog.show();
  }

  @Override
  protected String doInBackground(String... arg0) {
  
	  
	  Bundle extras = getIntent().getExtras();
	  if(extras!=null){
	  String sem = extras.getString("tf_semester");
	  String npmkh = extras.getString("tf_npm1");
  String url = "http://blogs-fahrudin1.rhcloud.com/androidkhs/matkul.php?sem="+sem+"&npmkh="+npmkh;


  JSONParser jParser = new JSONParser();


   JSONObject json = jParser.getJSONFromUrl(url);
   
   try {
    contacts = json.getJSONArray("data");

    for (int i = 0; i < contacts.length(); i++) {
     JSONObject c = contacts.getJSONObject(i);
     HashMap<String, String> map = new HashMap<String, String>();
     
     String id_mk = c.getString("id_mk").trim();
     String npm = c.getString("npm").trim();
     String semester = c.getString("semester").trim();
     String nama_mk = c.getString("nama_mk").trim();
     
     map.put("id_mk", id_mk);
     map.put("npm", npm);
     map.put("semester", semester);
     map.put("nama_mk", nama_mk);
     
     
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
     contactList, R.layout.matkul,
     new String[] { "id_mk", "npm", "semester", "nama_mk" }, new int[] { R.id.id_mk, R.id.npm, R.id.semester, R.id.nama_mk });

   lv.setAdapter(adapter2);
   
   lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	   @Override
		public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
		   finish();
			String npmdt = ((TextView) view.findViewById(R.id.npm)).getText().toString();
			String semdt = ((TextView) view.findViewById(R.id.semester)).getText().toString();
			String mkdt = ((TextView) view.findViewById(R.id.id_mk)).getText().toString();
     	 Intent mhs_detail = new Intent();
     	 mhs_detail.putExtra("tf_npmdt", npmdt);
     	mhs_detail.putExtra("tf_semdt", semdt);
     	mhs_detail.putExtra("tf_mkdt", mkdt);
         mhs_detail.setClassName("com.khs", "com.khs.DetailKhs");
         startActivity(mhs_detail);

		}
	});    
   

  }
  

 }
 
     
}