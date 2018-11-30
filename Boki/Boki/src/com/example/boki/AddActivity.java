package com.example.boki;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends ActionBarActivity {
	
	private DatePicker datePicker;
	private EditText jpy;
	private EditText twd;
	private EditText description;
	private Button btnAdd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		datePicker = (DatePicker) findViewById(R.id.datePicker);
		jpy = (EditText) findViewById(R.id.jpy);
		twd = (EditText) findViewById(R.id.twd);
		description = (EditText) findViewById(R.id.description);
		btnAdd = (Button) findViewById(R.id.btnAdd);
		
		jpy.setText("-");		
		jpy.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				if (arg0.length() > 1) {
					twd.setText(String.valueOf( Math.round(Integer.parseInt(jpy.getText().toString()) * 0.28 )));
				}
				else
				{
					twd.setText(null);
				}
			}
		});
		
		btnAdd.setOnClickListener(new Button.OnClickListener(){
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				if(jpy.getText().length() == 0 || description.getText().length() == 0){
					Toast.makeText(view.getContext(), "is not over yet", Toast.LENGTH_SHORT).show(); 
					return;
				}
				
			    String out = (datePicker.getYear() - 2000) + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getDayOfMonth(); 
				out += ";" + jpy.getText().toString() + ";" + twd.getText().toString() + ";" + description.getText().toString();
			    try{
			        BufferedWriter bw = new BufferedWriter(
			        		new FileWriter(
			        				new File(Environment.getExternalStorageDirectory(), "boki_data.txt"), true));
			        bw.write(out);
			        bw.newLine();
			        bw.close();
			    }catch(IOException e){
			       e.printStackTrace();
			    }
			    startActivity(new Intent(getApplicationContext(), ListActivity.class));
			}         
        });   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch(item.getItemId()) {
			case R.id.action_add:
				this.startActivity(new Intent(this, AddActivity.class));
				break;
			case R.id.action_list:
				this.startActivity(new Intent(this, ListActivity.class));
				break;
			case R.id.action_sum:
				this.startActivity(new Intent(this, SumActivity.class));
				break;
			case R.id.action_main:
				this.startActivity(new Intent(this, MainActivity.class));
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		return true;
	}
}
