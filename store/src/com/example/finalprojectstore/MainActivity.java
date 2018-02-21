package com.example.finalprojectstore;

import java.util.HashMap;

import netdb.softwarestudio.rest.RestManager;
import netdb.softwarestudio.rest.RestToolCallbackDelegate;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity { //STORE
	String hostURL = "http://frank1234211.appspot.com";
	String whoseMeal;
	private TextView textShow;
	private Button btnOne,btnTwo,btnThree,btnFour,btnFive,btnSix,btnSeven,btnEight,btnNine,btnZero,btnCancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		textShow=(TextView)findViewById(R.id.text_show);
		btnOne=(Button)findViewById(R.id.btnOne);
		btnTwo=(Button)findViewById(R.id.btnTwo);
		btnThree=(Button)findViewById(R.id.btnThree);
		btnFour=(Button)findViewById(R.id.btnFour);
		btnFive=(Button)findViewById(R.id.btnFive);
		btnSix=(Button)findViewById(R.id.btnSix);
		btnSeven=(Button)findViewById(R.id.btnSeven);
		btnEight=(Button)findViewById(R.id.btnEight);
		btnNine=(Button)findViewById(R.id.btnNine);
		btnZero=(Button)findViewById(R.id.btnZero);
		btnCancel=(Button)findViewById(R.id.btnCancel);
		
		btnOne.setOnClickListener(myListner);
		btnTwo.setOnClickListener(myListner);
		btnThree.setOnClickListener(myListner);
		btnFour.setOnClickListener(myListner);
		btnFive.setOnClickListener(myListner);
		btnSix.setOnClickListener(myListner);
		btnSeven.setOnClickListener(myListner);
		btnEight.setOnClickListener(myListner);
		btnNine.setOnClickListener(myListner);
		btnZero.setOnClickListener(myListner);
		btnCancel.setOnClickListener(myListner);
		
	}
	
	
	
	private Button.OnClickListener myListner = new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			whoseMeal = textShow.getText().toString();
			switch(v.getId()){ 
				case R.id.btnZero:{
					textShow.setText(whoseMeal + "0");
					break;
				}
				case R.id.btnOne:{
					textShow.setText(whoseMeal + "1");
					break;
				}
				case R.id.btnTwo:{
					textShow.setText(whoseMeal + "2");
					break;
				}
				case R.id.btnThree:{
					textShow.setText(whoseMeal + "3");
					break;
				}
				case R.id.btnFour:{
					textShow.setText(whoseMeal + "4");
					break;
				}
				case R.id.btnFive:{
					textShow.setText(whoseMeal + "5");
					break;
				}
				case R.id.btnSix:{
					textShow.setText(whoseMeal + "6");
					break;
				}
				case R.id.btnSeven:{
					textShow.setText(whoseMeal + "7");
					break;
				}
				case R.id.btnEight:{
					textShow.setText(whoseMeal + "8");
					break;
				}
				case R.id.btnNine:{
					textShow.setText(whoseMeal + "9");
					break;
				}
				case R.id.btnCancel:{
					if(whoseMeal.length()>0){
						whoseMeal = whoseMeal.substring(0, whoseMeal.length()-1);
						textShow.setText(whoseMeal);
					}
					break;
				}
			}
		}	
	};
	
	
	public void btnEnter(View v){
		String URI = hostURL + "/definition/numberCome";
		whoseMeal = textShow.getText().toString();
		if(whoseMeal.length() > 0){
			final Integer whoseMeal_int = Integer.parseInt(whoseMeal);
			if(whoseMeal_int == 0){
				runOnUiThread(new Runnable() {
					public void run() {
						Toast toast = Toast.makeText(MainActivity.this, "Doesn't have this number 0 !", Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
						whoseMeal = "";
						textShow.setText(whoseMeal);
					}
				});
			}
			else{
				Long Id = (long) 111;
				ShopModel callCustomer = new ShopModel(Id,0,whoseMeal_int);
				
				@SuppressWarnings("serial")
				HashMap<String, Object> params = new HashMap<String, Object>(){{put("id","111");}};
				RestManager.putObject(URI, params, callCustomer, ShopModel.class, null, new RestToolCallbackDelegate(){
	
					@Override
					public void fail(final String arg0) {
						runOnUiThread(new Runnable() {
							public void run() {
								System.out.println("<Call customer fail> " + arg0);
								Toast toast = Toast.makeText(MainActivity.this, "Doesn't have this number!", Toast.LENGTH_LONG);
								toast.setGravity(Gravity.CENTER, 0, 0);
								toast.show();
								whoseMeal = "";
								textShow.setText(whoseMeal);
							}
						});
					}
	
					@Override
					public void success(final Object arg0) {
						runOnUiThread(new Runnable() {
							public void run() {
								System.out.println("<Call customer success> " + arg0);
								Toast toast = Toast.makeText(MainActivity.this, "Calling " + whoseMeal_int.toString() +"!", Toast.LENGTH_LONG);
								toast.setGravity(Gravity.CENTER, 0, 0);
								toast.show();
								whoseMeal = "";
								textShow.setText(whoseMeal);
							}
						});	
						
					}});
				}
		
			/*RestManager.postObject(URI, callCustomer, ShopModel.class, null , new RestToolCallbackDelegate(){

				@Override
				public void fail(final String arg0) {
					runOnUiThread(new Runnable() {
						public void run() {
							System.out.println("<Call customer fail> " + arg0);
							Toast toast = Toast.makeText(MainActivity.this, "Doesn't have this number!", Toast.LENGTH_LONG);
							toast.setGravity(Gravity.CENTER, 0, 0);
							toast.show();
							whoseMeal = "";
							textShow.setText(whoseMeal);
						}
					});
				}

				@Override
				public void success(final Object arg0) {
					runOnUiThread(new Runnable() {
						public void run() {
							System.out.println("<Call customer success> " + arg0);
							Toast toast = Toast.makeText(MainActivity.this, "Calling " + whoseMeal_int.toString() +"!", Toast.LENGTH_LONG);
							toast.setGravity(Gravity.CENTER, 0, 0);
							toast.show();
							whoseMeal = "";
							textShow.setText(whoseMeal);
						}
					});	
				}
			});*/
		}
		else{
			Toast toast = Toast.makeText(MainActivity.this, "Please enter number!", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}
	
	public void btnClear(View v){
		String URI = hostURL + "/definition";
		Long Id = (long)111;
		ShopModel clearAll = new ShopModel(Id,0,0);
		
		RestManager.putObject(URI, null, clearAll, ShopModel.class , null, new RestToolCallbackDelegate(){

			@Override
			public void fail(final String arg0) {
				runOnUiThread(new Runnable() {
				    public void run() {
				    	//System.out.println("<Clear fail> " + arg0);
						Toast toast = Toast.makeText(MainActivity.this, "Clear ALL <FAIL!!>", Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
				    }
				});
			}

			@Override
			public void success(final Object arg0) {
				runOnUiThread(new Runnable() {
				    public void run() {
				    	//System.out.println("<Clear success> " + arg0);
						Toast toast = Toast.makeText(MainActivity.this, "Clear ALL <SUCCESS!!>", Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
				    }
				});
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
		
}
