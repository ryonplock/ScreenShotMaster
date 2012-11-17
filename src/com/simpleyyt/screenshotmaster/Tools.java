package com.simpleyyt.screenshotmaster;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

public class Tools extends View {
	private BitmapDrawable selector, icon, icon_selected, up_arrow, menu;
	private boolean isMenu;
	public Tools(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context, attrs);
	}

	public Tools(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Tools);
		int icon_id = typedArray.getResourceId(R.styleable.Tools_icon, R.drawable.pen_btn);
		int icon_selected_id = typedArray.getResourceId(R.styleable.Tools_icon_selected, R.drawable.pen_selected);
		boolean menu = typedArray.getBoolean(R.styleable.Tools_menu, false);
		typedArray.recycle();
		isMenu = menu;
		Bitmap icon_bt = ((BitmapDrawable) getResources().getDrawable(icon_id)).getBitmap();
		Bitmap icon_selected_bt = ((BitmapDrawable) getResources().getDrawable(icon_selected_id)).getBitmap();
		Bitmap up_arrow_bt = ((BitmapDrawable) getResources().getDrawable(R.drawable.up_arrow)).getBitmap();
		Bitmap selector_bt = ((BitmapDrawable) getResources().getDrawable(R.drawable.selected)).getBitmap();
		icon = new BitmapDrawable(icon_bt);
		icon_selected = new BitmapDrawable(icon_selected_bt);
		up_arrow = new BitmapDrawable(up_arrow_bt);
		selector = new BitmapDrawable(selector_bt);
		Rect bounds = new Rect(0,0,icon_bt.getWidth(),icon_bt.getHeight());
		icon.setBounds(bounds);
		icon_selected.setBounds(bounds);
		up_arrow.setBounds(bounds);
		selector.setBounds(bounds);
	}

	public void showMenu() {

	}

	public void hideMenu() {

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		this.setMeasuredDimension(icon.getBitmap().getWidth(), icon.getBitmap().getHeight());
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		icon.draw(canvas);
		selector.draw(canvas);
		icon_selected.draw(canvas);
		up_arrow.draw(canvas);
		super.onDraw(canvas);
	}
}
