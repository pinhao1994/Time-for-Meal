package com.example.finalproject;

import java.util.HashMap;

import netdb.softwarestudio.rest.RestManager;
import netdb.softwarestudio.rest.RestToolCallbackDelegate;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ClientMainResult extends Activity {
	private TextView showMyNum;
	public Integer myNum;
	String shopId_s;
	private boolean isOver = false; 
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client_main_result);
		
		Intent intent = this.getIntent();
		myNum = Integer.parseInt(intent.getStringExtra("MyNum"));
		shopId_s = intent.getStringExtra("ShopId");
		
		showMyNum = (TextView)findViewById(R.id.your_number_text);
		showMyNum.setText(myNum.toString());
		
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				
				while(!isOver){
					String URI = "http://frank1234211.appspot.com/definition/numberCome";
					@SuppressWarnings("serial")
					HashMap<String, Object> params = new HashMap<String, Object>(){{put("id",shopId_s);}};
					RestManager.getObject(URI, params, ShopModel.class,new RestToolCallbackDelegate(){

						@Override
						public void fail(String arg0) {
							ClientMainResult.this.runOnUiThread(new Runnable(){
								@Override
								public void run() {
									//System.out.println("<Timer fail> " + arg0);
									Toast toast = Toast.makeText(ClientMainResult.this, "Please call the Store!!", Toast.LENGTH_LONG);
									toast.setGravity(Gravity.CENTER, 0, 0);
									toast.show();
								}
							});
						}

						@Override
						public void success(final Object arg0) {
							ClientMainResult.this.runOnUiThread(new Runnable(){
								@Override
								public void run() {
									//System.out.println("<Timer success> " + arg0);
									//callNum.setText(((ShopModel)arg0).getNumberCome().toString());
									/*Toast toast = Toast.makeText(ClientMainResult.this, "Timer update!!", Toast.LENGTH_LONG);
									toast.setGravity(Gravity.CENTER, 0, 0);
									toast.show();*/
								}
							});
							
							int nowCallNum = Integer.parseInt(((ShopModel)arg0).getNumberCome().toString());
							if(myNum.equals(nowCallNum)){
								NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
								PendingIntent appIntent=PendingIntent.getActivity(ClientMainResult.this,0,getIntent(),0);
								Notification notification = new Notification();
								notification.icon=R.drawable.logo;
								notification.tickerText="notification on status bar.";
								notification.defaults=Notification.DEFAULT_ALL;
								notification.setLatestEventInfo(ClientMainResult.this,"Your meal is ready!!","Please press the \"Get the meal\" buttom!!",appIntent);
								notificationManager.notify(0,notification);
								
								ClientMainResult.this.runOnUiThread(new Runnable(){
									@Override
									public void run() {
										Toast toast = Toast.makeText(ClientMainResult.this, "Please press the \"Get the meal\" buttom!!", Toast.LENGTH_LONG);
										toast.setGravity(Gravity.BOTTOM, 0, 0);
										toast.show();
									}
								});			
								isOver = true;
							}
						}});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		t.start();	
	}
	
	public void gotMeal(View view){
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.client_main_result, menu);
		return true;
	}

}
