package com.example.boki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListActivity extends ActionBarActivity {
	
	private ListView listView = null;
    private List<Map<String, Object>> list = null;
    private SimpleAdapter adapter = null;
    
    private TextView stat;
    private int total_jpy = 0;
    private int total_twd = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		stat = (TextView) findViewById(R.id.stat);
		listView = (ListView) findViewById(R.id.listView);
		list = initList();
		adapter = new SimpleAdapter(this, list, R.layout.listitem,
                new String[] { "time", "description", "jpy", "twd" }, new int[] {
                        R.id.time, R.id.description, R.id.jpy, R.id.twd });
		listView.setAdapter(adapter);
		stat.setText(list.size() + ": " + total_jpy + " = " + total_twd);
	}
	
	private List<Map<String, Object>> initList() {
		
		List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        
        try {
			BufferedReader br = new BufferedReader(
					new FileReader(
							new File(Environment.getExternalStorageDirectory(), "boki_data.txt")) );
			String newLine = br.readLine();
			while(newLine != null)
			{
				String[] arr = newLine.split(";");
				map = new HashMap<String, Object>();
	            map.put("time", arr[0]);
	            map.put("jpy", arr[1]);
	            map.put("twd", arr[2]);
	            map.put("description", arr[3]);
	            tempList.add(0, map);
	            total_jpy += Integer.parseInt(arr[1]);
	            total_twd += Integer.parseInt(arr[2]);
				newLine = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return tempList;
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
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
