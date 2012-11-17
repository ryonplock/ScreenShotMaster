package com.simpleyyt.screenshotmaster;

import java.io.FileOutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	public final String file_name = "/sdcard/a.png";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			Bitmap bt = ScreenShot.getScreenBitmap(this);
			FileOutputStream out = new FileOutputStream(file_name);
			bt.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
			Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
