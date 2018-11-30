package com.example.boki;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	private TextView timer;
	private TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Date date = new Date();
        //Calendar startDay = new GregorianCalendar(2014,Calendar.SEPTEMBER,24);
        Calendar endDay = new GregorianCalendar(2015,Calendar.AUGUST,29);
        //long pastDays = (((date.getTime() - startDay.getTimeInMillis()) / 1000)) / 86400 - 1;
        long lastDays = (((endDay.getTimeInMillis() - date.getTime()) / 1000)) / 86400 + 1;
        
        timer = (TextView) findViewById(R.id.timer);
        //timer.setText(pastDays + " / "  + lastDays);
        timer.setText(lastDays + " days left");
        
        PackageInfo pkgInfo;
		try {
			pkgInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			version = (TextView) findViewById(R.id.version);
		    version.setText("version " + pkgInfo.versionName + "." + pkgInfo.versionCode);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
