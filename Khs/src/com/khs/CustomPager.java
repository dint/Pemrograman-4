package com.khs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
 
public class CustomPager extends Fragment{
 
public static final String PAGE = "page";
ViewGroup viewGroup ;
ImageView imageView ;
 
public CustomPager() {
// TODO Auto-generated constructor stub
}
 
public static CustomPager create(int page) {
 
CustomPager customPager = new CustomPager();
Bundle data = new Bundle();
data.putInt(PAGE, page);
customPager.setArguments(data);
return customPager ;
}
 
@Override
public void onCreate(Bundle savedInstanceState) {
 
super.onCreate(savedInstanceState);
}
 
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
Bundle savedInstanceState) {
 
viewGroup = (ViewGroup) inflater.inflate(R.layout.slide, container, false);
imageView = (ImageView) viewGroup.findViewById(R.id.imageView);
 
switch (getArguments().getInt(PAGE)) {
 
case 0:
imageView.setImageResource(R.drawable.penggunaan1);
break;
case 1:
imageView.setImageResource(R.drawable.penggunaan2);
break;
case 2:
imageView.setImageResource(R.drawable.penggunaan3);
break;
case 3:
imageView.setImageResource(R.drawable.penggunaan4);
break;
case 4:
imageView.setImageResource(R.drawable.penggunaan5);
break;
 
default:
break;
}
 
return viewGroup;
}
 
}