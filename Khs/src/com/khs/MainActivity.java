package com.khs;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.content.DialogInterface;

import android.view.KeyEvent;
public class MainActivity extends Activity{

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Button btn_lihat;
        Button btn_cari;
        Button btn_keluar;
        Button btn_mhs;
        
        btn_lihat=(Button) findViewById(R.id.lihat_khs);
        btn_lihat.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v){
        	 finish();	
           	 Intent a = new Intent();
             a.setClassName("com.khs", "com.khs.Angkatan");
             startActivity(a);

        	}
        });
     
        btn_cari=(Button) findViewById(R.id.cari_khs);
        btn_cari.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v){
        		 finish();
              	 Intent b = new Intent();
                 b.setClassName("com.khs", "com.khs.CariKhs");
                 startActivity(b);
        	}
        });        

        btn_mhs=(Button) findViewById(R.id.mhs);
        btn_mhs.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v){
        		 finish();
              	 Intent c = new Intent();
                 c.setClassName("com.khs", "com.khs.Mhs");
                 startActivity(c);

        	}
        });         
        
        
        btn_keluar=(Button) findViewById(R.id.keluar);
        btn_keluar.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v){
        		keluar();
        	}
        });        

    }
	public void keluar(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("keluar?")
		.setCancelable(false)
		.setPositiveButton("Ya",new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog,int id) {
		                  MainActivity.this.finish();
		            }
		      })
		.setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog,int id) {
		                  dialog.cancel();
		            }
		      }).show();
		}	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
         keluar();
      
        }   
        return super.onKeyDown(keyCode, event);
     }
	
 }