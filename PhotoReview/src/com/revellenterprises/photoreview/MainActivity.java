package com.revellenterprises.photoreview;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.widget.*;
import android.view.*;

public class MainActivity extends Activity 
{

	boolean intro;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		intro = true;
		if(intro){
			showIntro();
		}
    }
	
	@Override
	protected void onResume()
	{
		super.onResume();
		if(!intro){
			Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivity(i);
		} else {
			showIntro();
		}
	}

	@Override
	public void onBackPressed()
	{
		this.finishAndRemoveTask();
		super.onBackPressed();
	}



	public void showIntro(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.intro)
			.setMessage("Press 'OK' to start taking photos, or press 'Rate' to rate this, if you are so inclined.")
			.setPositiveButton("OK", new DialogInterface.OnClickListener(){

				public void onClick(DialogInterface d, int i){
					Intent j = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					Toast t = Toast.makeText(getApplicationContext(),"Use the home button or the recent screens menu to exit.",Toast.LENGTH_LONG);
					t.show();
					intro = false;
					startActivity(j);

				}

			})
			.setNeutralButton("Rate", new DialogInterface.OnClickListener(){

				public void onClick(DialogInterface d, int i){
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getApplicationContext().getPackageName())));
				}

			})
			.setNegativeButton("Exit", new DialogInterface.OnClickListener(){
				
				public void onClick(DialogInterface DialogInterface, int which){
					finishAndRemoveTask();
				}
				
			})
			.setCancelable(false)
			.create()
			.show();
	}

	public void notImplementedDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Sorry!")
			.setMessage("Not implemented yet.")
			.setPositiveButton("Aww..", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface d, int i){
					showIntro();
				}
			})
			.setIcon(R.drawable.ic_launcher)
			.setCancelable(false)
			.create()
			.show();
	}
}
