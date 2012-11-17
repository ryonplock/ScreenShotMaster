package com.simpleyyt.screenshotmaster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class RectPopup extends View {

	public BitmapDrawable circle, rect, line, rect_popup,selected;
	public Bitmap circle_bt, rect_bt, line_bt, rect_popup_bt,selected_bt;
	private final Rect rect_bounds = new Rect(5,5,85,73);
	private final Rect circle_bounds = new Rect(85,5,165,73);
	private final Rect line_bounds = new Rect(165,5,245,73);
	private ChangedListener listener;
	public RectPopup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public RectPopup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public RectPopup(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {
        circle_bt = ((BitmapDrawable)getResources().getDrawable(
        		R.drawable.circle_selected)).getBitmap();
        rect_bt = ((BitmapDrawable)getResources().getDrawable(
            R.drawable.rect_selected)).getBitmap();
        line_bt = ((BitmapDrawable)getResources().getDrawable(
            R.drawable.line_selected)).getBitmap();
        selected_bt = ((BitmapDrawable)getResources().getDrawable(
                R.drawable.selector_bg)).getBitmap();
        rect_popup_bt =((BitmapDrawable)getResources().getDrawable(
                R.drawable.rect_popup)).getBitmap();
        circle = new BitmapDrawable(circle_bt);
        rect = new BitmapDrawable(rect_bt);
        line = new BitmapDrawable(line_bt);
        selected = new BitmapDrawable(selected_bt);
        rect_popup = new BitmapDrawable(rect_popup_bt);
        rect_popup.setBounds(0,0,rect_popup_bt.getWidth(),rect_popup_bt.getHeight());
        rect.setBounds(rect_bounds);
        line.setBounds(line_bounds);
        circle.setBounds(circle_bounds);
		selected.setBounds(rect_bounds);
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
			if(rect_bounds.contains(x, y)) {
				selectRect();
				listener.onChangedListener(0);
			}
			if(circle_bounds.contains(x, y)){
				selectCircle();
				listener.onChangedListener(1);
			}
			if(line_bounds.contains(x, y)){
				selectLine();
				listener.onChangedListener(2);
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
		rect_popup.draw(canvas);
		selected.draw(canvas);
		circle.draw(canvas);
		rect.draw(canvas);
		line.draw(canvas);
		super.onDraw(canvas);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		this.setMeasuredDimension(getMenuWidth(), getMenuHeight());
	}

	public int getMenuWidth() {
		return rect_popup_bt.getWidth();
	}
	public int getMenuHeight() {
		return rect_popup_bt.getHeight();
	}
	public void selectRect() {
		selected.setBounds(rect_bounds);
		this.invalidate();
	}
	public void selectCircle() {
		selected.setBounds(circle_bounds);
		this.invalidate();
	}
	public void selectLine() {
		selected.setBounds(line_bounds);
		this.invalidate();
	}
}
