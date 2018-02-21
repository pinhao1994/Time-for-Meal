package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity { //customer
	private TextView textShow;
	String restaurantId;
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
			restaurantId = textShow.getText().toString();
			switch(v.getId()){ 
				case R.id.btnOne:{
					textShow.setText(restaurantId + "1");
					break;
				}
				case R.id.btnTwo:{
					textShow.setText(restaurantId + "2");
					break;
				}
				case R.id.btnThree:{
					textShow.setText(restaurantId + "3");
					break;
				}
				case R.id.btnFour:{
					textShow.setText(restaurantId + "4");
					break;
				}
				case R.id.btnFive:{
					textShow.setText(restaurantId + "5");
					break;
				}
				case R.id.btnSix:{
					textShow.setText(restaurantId + "6");
					break;
				}
				case R.id.btnSeven:{
					textShow.setText(restaurantId + "7");
					break;
				}
				case R.id.btnEight:{
					textShow.setText(restaurantId + "8");
					break;
				}
				case R.id.btnNine:{
					textShow.setText(restaurantId + "9");
					break;
				}
				case R.id.btnZero:{
					textShow.setText(restaurantId + "0");
					break;
				}
				case R.id.btnCancel:{
					if(restaurantId.length()>0){
						restaurantId = restaurantId.substring(0, restaurantId.length()-1);
						textShow.setText(restaurantId);
					}
					break;
				}
			}
		}	
	};
	
	public void btnEnter(View v){
		restaurantId = textShow.getText().toString();
		if(restaurantId.length() >0 ){
			int restaurantId_int = Integer.parseInt(restaurantId);
			if(restaurantId_int == 111){
				Intent intent = new Intent(this,PickNumber.class);
				intent.putExtra("ShopId", restaurantId);
				restaurantId="";
				textShow.setText(restaurantId);
				startActivity(intent);
				finish();
			}
			else{
				Toast toast = Toast.makeText(MainActivity.this, "Wrong ID!!", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
	
				restaurantId="";
				textShow.setText(restaurantId);
			}
		}
		else{
			Toast toast = Toast.makeText(MainActivity.this, "Please enter the id", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
