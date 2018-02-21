package com.example.finalproject;

import java.util.HashMap;

import netdb.softwarestudio.rest.RestManager;
import netdb.softwarestudio.rest.RestToolCallbackDelegate;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PickNumber extends Activity {
	String hostURL = "http://frank1234211.appspot.com";
	private String myNum;
	private TextView showNowNum;
	private String ShopId_s;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pick_number);			
		
		Intent intent = this.getIntent();
		showNowNum = (TextView)findViewById(R.id.now_number_text);
		ShopId_s=intent.getStringExtra("ShopId");
			
		@SuppressWarnings("serial")
		HashMap<String, Object> params = new HashMap<String, Object>(){{put("id",ShopId_s);}};
		String URI = hostURL + "/definition";
		RestManager.getObject(URI, params, ShopModel.class, new RestToolCallbackDelegate(){

			@Override
			public void fail(final String arg0) {
				PickNumber.this.runOnUiThread(new Runnable() {
				    public void run() {
				    	//System.out.println("<now number fail> " + arg0);
						Toast toast = Toast.makeText(PickNumber.this, "Please turn on your wifi!!", Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
				    }
				});
			}

			@Override
			public void success(final Object arg0) {
				// TODO Auto-generated method stub
				PickNumber.this.runOnUiThread(new Runnable(){
        			@Override
        			public void run() {
        				//System.out.println("<now number success> "+arg0);
						Toast toast = Toast.makeText(PickNumber.this, "Welcome!!", Toast.LENGTH_LONG);
						toast.setGravity(Gravity.BOTTOM, 0, 0);
						toast.show();
						showNowNum.setText(((ShopModel)arg0).getNumberGive().toString());
        			}
        		});
			}	
		});
	}
	
	public void pickNum(View view){
		String URI = hostURL + "/definition/numberGive";
		@SuppressWarnings("serial")
		HashMap<String, Object> params = new HashMap<String, Object>(){{put("id",ShopId_s);}};
		RestManager.getObject(URI, params, ShopModel.class, new RestToolCallbackDelegate(){
			
			@Override
			public void fail(final String arg0) {
				PickNumber.this.runOnUiThread(new Runnable(){
					@Override
					public void run() {
						//System.out.println("<pick num fail >" + arg0);
						Toast toast = Toast.makeText(PickNumber.this, "Int connect fail!!", Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
					}
				});				
			}

			@Override
			public void success(final Object arg0) {
				PickNumber.this.runOnUiThread(new Runnable(){
					@Override
					public void run() {
						//System.out.println("<pick num success> "+ arg0);
						Toast toast = Toast.makeText(PickNumber.this, "Pick a number!!", Toast.LENGTH_LONG);
						toast.setGravity(Gravity.BOTTOM, 0, 0);
						toast.show();
					}
				});						
				//server auto +1
				myNum = ((ShopModel)arg0).getNumberGive().toString();
				Intent intent = new Intent(PickNumber.this, ClientMainResult.class);
				intent.putExtra("ShopId", ShopId_s);
				intent.putExtra("MyNum",myNum);
				startActivity(intent);
				finish();
			}			
		});
	}
	
	public void finishBtn(View view){
		finish();
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pick_number, menu);
		return true;
	}
	


}
