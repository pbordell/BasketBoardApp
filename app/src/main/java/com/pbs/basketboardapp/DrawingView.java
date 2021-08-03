package com.pbs.basketboardapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

import com.example.basketboardapp.R;

public class DrawingView extends View {

	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Path mPath;
	private Paint mPaint;

	private int grosor = 10;

	public DrawingView(Context c, Paint p) {
		super(c);

		mPaint = p;
		mPath = new Path();

		establecerBackground(R.drawable.ic_fondo_pizarra);
	}

	/************************* Eventos en la pizarra **********************************/

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(mBitmap, 0, 0, mPaint);
		mCanvas.drawPath(mPath, mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touch_start(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			touch_move(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			touch_up();
			invalidate();
			break;
		}
		return true;
	}

	/************************* Fin eventos en la pizarra **********************************/

	private void touch_start(float x, float y) {
		mPath.reset();
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
	}

	private void touch_move(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;
		}
	}

	private void touch_up() {
		mPath.lineTo(mX, mY);
		mCanvas.drawPath(mPath, mPaint);
		mPath.reset();
	}

	public void paint() {
		mPaint.setXfermode(null);
		mPaint.setStrokeWidth(this.grosor);
	}

	public void remove() {
		mPaint.setStrokeWidth(40);
		mPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
	}

	public void removeAll() {
		mCanvas.drawColor(0, Mode.CLEAR);
		invalidate();
	}

	public void establecerBackground(int idBackground) {
		Drawable backgroundBoard = getResources().getDrawable(idBackground);
		setBackground(backgroundBoard);
	}

	public void establecerColor(int color) {
		mPaint.setColor(color);
	}

	public void establecerGrosor(int grosor) {
		this.grosor = grosor;
		this.paint();
	}

}