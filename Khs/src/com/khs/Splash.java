package com.khs;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Splash extends Activity {

 protected boolean _active = true;
    protected int _splashTime = 3000;
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.splash);
  StartAnimations();//Menjalankan Method Start Animasi
  
  Thread splashThread = new Thread() {
   //Timer Splash
   public void run() {
    try{
     int waited = 0;
      while(_active && (waited < _splashTime)) {
                         sleep(100);
                         if(_active) {
                             waited += 100;
                         }
                     }
                 } catch(InterruptedException e) {
                     // do nothing
                 } finally {
                     finish();
                     Intent newIntent=new Intent(Splash.this, MainActivity.class);//pindah Activity Main
               startActivityForResult(newIntent,0);
                 }
             }
         };
         splashThread.start();
     }
  
     @Override
     public boolean onTouchEvent(MotionEvent event) {
         if (event.getAction() == MotionEvent.ACTION_DOWN) {
             _active = false;
         }
         return true;
     }
 




 //Disini Deklarasi Animasinya(StartAnimation)
 private void StartAnimations() {
  // TODO Auto-generated method stub
  //Animasi untuk Frame Layout mengunakan alpha.xml
  Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        FrameLayout l=(FrameLayout) findViewById(R.id.FrameLayout1);
        l.clearAnimation();
        l.startAnimation(anim);
        
      //Animasi untuk ProgressBar1 mengunakan alpha.xml
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim1.reset();
        ProgressBar l1=(ProgressBar) findViewById(R.id.progressBar1);
        l1.clearAnimation();
        l1.startAnimation(anim);
        
      //Animasi untuk Gambar mengunakan Translate.xml
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splashimg);
        iv.clearAnimation();
        iv.startAnimation(anim);
 }
    

}
