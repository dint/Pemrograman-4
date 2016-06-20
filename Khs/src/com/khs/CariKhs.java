package com.khs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class CariKhs extends Activity{
	private EditText txtnpm;
	private EditText txtsem;
	Button button_cari; 
	Button btn_back;
    /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       
       setContentView(R.layout.cari);
       txtnpm = (EditText) findViewById(R.id.txtnpm);
       txtsem = (EditText) findViewById(R.id.txtsem);
       button_cari = (Button)this.findViewById(R.id.button_cari);
       button_cari.setOnClickListener(new View.OnClickListener() {
          	public void onClick(View v){	
          		EditText txtwarning;
          		txtwarning = (EditText) findViewById(R.id.warning);
          		if(txtnpm.getText().length() == 0 && txtsem.getText().length() == 0){
          			txtwarning.setText("Belum ada yang di isi");
          			txtnpm.requestFocus();
          		}else if(txtnpm.getText().length() == 0){
          			txtwarning.setText("NPM Belum di isi");
          			txtnpm.requestFocus();
          			
          		}else if(txtsem.getText().length() == 0){
          			txtwarning.setText("Semester Belum di isi");
          			txtnpm.requestFocus();          			
          			
          		}else{	
          		finish();
          		String cari_npm = txtnpm.getText().toString();
          		String cari_sem = txtsem.getText().toString();
               	 Intent d = new Intent();
               	  d.putExtra("tf_npm1", cari_npm);
               	  d.putExtra("tf_semester", cari_sem);
                   d.setClassName("com.khs", "com.khs.Matkul");
                   startActivity(d);
          		}

          	}
          });
       
       
       btn_back=(Button) findViewById(R.id.back);
       btn_back.setOnClickListener(new View.OnClickListener() {
       	public void onClick(View v){
       		finish();
            	 Intent m1 = new Intent();
                m1.setClassName("com.khs", "com.khs.MainActivity");
                startActivity(m1);
       	}
       });
       
       
   }

}
