package com.khs;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
 
public class Penggunaan extends FragmentActivity implements OnClickListener {
 
ViewPager viewPager ;
Button buttonKiri, buttonKanan;
ViewPagerAdapter viewPagerAdapter ;
int page = 0 ;
 
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.penggunaan_pag);

Button btn_back;
btn_back=(Button) findViewById(R.id.back);
btn_back.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v){
		finish();
     	 Intent menu = new Intent();
         menu.setClassName("com.khs", "com.khs.MainActivity");
         startActivity(menu);
	}
});
 
initObject();
 
initComponentViews();
 
initConfigurationView();
 
initOnImplementsView();
 
}
 
private void initObject(){
viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
}
 
private void initComponentViews(){
viewPager = (ViewPager) findViewById(R.id.viewPager);
buttonKiri = (Button) findViewById(R.id.buttonKiri);
buttonKanan = (Button) findViewById(R.id.buttonKanan);
 
}
 
private void initConfigurationView(){
viewPager.setAdapter(viewPagerAdapter);
}
 
private void initOnImplementsView(){
buttonKiri.setOnClickListener(this);
buttonKanan.setOnClickListener(this);
}
 
@Override
public void onClick(View v) {
 
switch (v.getId()) {
 
case R.id.buttonKiri:
 
page -= 1;
 
viewPager.setCurrentItem(page);
if (page<=0) {
buttonKiri.setVisibility(Button.INVISIBLE);
}else{
buttonKiri.setVisibility(Button.VISIBLE);
}
if (page==4) {
buttonKanan.setVisibility(Button.VISIBLE);
}
break;
 
case R.id.buttonKanan:
page += 1;
viewPager.setCurrentItem(page);
 
if (page>=ViewPagerAdapter.PAGER_LENGTH) {
buttonKanan.setVisibility(Button.INVISIBLE);
}else{
buttonKiri.setVisibility(Button.VISIBLE);
}
if (page==1) {
buttonKiri.setVisibility(Button.VISIBLE);
}
break;
 
default:
break;
}
}
}