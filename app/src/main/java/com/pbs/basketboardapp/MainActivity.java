package com.pbs.basketboardapp;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.basketboardapp.R;
import com.pbs.basketboardapp.dialogs.DialogBackground;
import com.pbs.basketboardapp.dialogs.DialogStyle;

public class MainActivity extends Activity {

	private DrawingView dv;
	private Paint mPaint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.GREEN);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(12);

		dv = new DrawingView(this, mPaint);
		setContentView(dv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_paint:
			dv.paint();
			return true;
		case R.id.action_delete:
			dv.remove();
			return true;
		case R.id.action_clear:
			dv.removeAll();
			return true;
		case R.id.action_change_background:
			DialogBackground dialogBackground = new DialogBackground();
			dialogBackground.show(getFragmentManager(), "dialogBackground");
			dv.paint();
			return true;
		case R.id.action_change_style:
			DialogStyle dialogStyle = new DialogStyle();
			dialogStyle.show(getFragmentManager(), "dialogStyle");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void changeBackground(int idBackground) {
		dv.establecerBackground(idBackground);
	}

	public void changeColor(int color) {
		dv.establecerColor(color);
	}

	public void changeStroke(int grosor) {
		dv.establecerGrosor(grosor);
	}
}
