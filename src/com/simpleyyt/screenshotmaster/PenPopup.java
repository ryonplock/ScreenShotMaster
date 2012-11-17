package com.simpleyyt.screenshotmaster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class PenPopup extends View {

	private BitmapDrawable pen_popup;
	private BitmapDrawable pen_selected;
	private BitmapDrawable marker_selected;
	private BitmapDrawable selector;
	private final Rect pen_bounds = new Rect(5,5,85,73);
	private final Rect marker_bounds = new Rect(85,5,165,73);
	private final Rect pen_popup_bounds = new Rect(0, 0, 172, 144);
	private ChangedListener listener;
	public PenPopup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public PenPopup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public PenPopup(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		Bitmap pen_popup_bt = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.pen_popover)).getBitmap();
		Bitmap pen_selected_bt = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.pen_selected)).getBitmap();
		Bitmap marker_selected_bt = ((BitmapDrawable) getResources()
				.getDrawable(R.drawable.marker_selected)).getBitmap();
		Bitmap selector_bt = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.selector_bg)).getBitmap();
		pen_popup = new BitmapDrawable(pen_popup_bt);
		pen_selected = new BitmapDrawable(pen_selected_bt);
		marker_selected = new BitmapDrawable(marker_selected_bt);
		selector = new BitmapDrawable(selector_bt);
		selector.setBounds(pen_bounds);
		pen_popup.setBounds(pen_popup_bounds);
		marker_selected.setBounds(marker_bounds);
		pen_selected.setBounds(pen_bounds);
	}
	
	public void setChangedListener(ChangedListener listener) {
		this.listener = listener;
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int x=(int)event.getX();
		int y=(int)event.getY();
		switch(event.getAction()) {
		case MotionEvent.ACTION_MOVE:
		case MotionEvent.ACTION_DOWN:
			if(pen_bounds.contains(x, y)) {
				selectPen();
				listener.onChangedListener(0);
			}
			if(marker_bounds.contains(x, y)){
				selectMarker();
				listener.onChangedListener(1);
			}
			break;
		case MotionEvent.ACTION_UP:
			this.setVisibility(View.GONE);
			break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		pen_popup.draw(canvas);
		selector.draw(canvas);
		pen_selected.draw(canvas);
		marker_selected.draw(canvas);
		super.onDraw(canvas);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		this.setMeasuredDimension(getMenuWidth(), getMenuHeight());
	}

	public int getMenuWidth() {
		return pen_popup.getBitmap().getWidth();
	}
	public int getMenuHeight() {
		return pen_popup.getBitmap().getHeight();
	}
	public void selectPen() {
		selector.setBounds(pen_bounds);
		this.invalidate();
	}
	public void selectMarker() {
		selector.setBounds(marker_bounds);
		this.invalidate();
	}
}
