package com.simpleyyt.screenshotmaster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ColorMenu extends View {
	private BitmapDrawable colorpop, hightlighter, slider;
	private ShapeDrawable circle;
	private Rect[] color;
	private final int[] colorNum = { 0xFFFCF121, 0xFFE28520, 0xFF6AD919,
			0xFF000000, 0xFF3C78FC, 0xFFD43857, 0xFFD63410, 0xFFFFFFFF };
	private final Rect moveRight = new Rect(72, 0, 72, 0);
	private final Rect moveDown = new Rect(0, 54, 0, 54);
	private final Rect initPos = new Rect(31, 38, 91, 83);
	private final Rect offset = new Rect(-7, -5, 5, 4);
	private final Rect sliderRange = new Rect(29, 194 - 33, 29, 194 + 33);
	private final int sliderMax = 22;
	private final int sliderMin = 2;
	private final int initColor = 0;
	private final int initSliderPos = 100;
	private Rect sliderrect = new Rect(31, 173, 307, 216);
	private ColorChangedListener colorchangedlistener;
	private WidthChangedListener widthchangedlistener;

	public ColorMenu(Context context) {
		super(context);
		init();
	}

	public int getPaintColor() {
		return circle.getPaint().getColor();
	}

	public ColorMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
		// TODO Auto-generated constructor stub
	}

	public ColorMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		// TODO Auto-generated constructor stub
	}

	private void init() {
		color = new Rect[8];
		color[0] = new Rect(initPos);
		for (int i = 1; i < 4; i++) {
			color[i] = new Rect(color[i - 1]);
			color[i] = addRect(color[i], moveRight);
		}

		color[4] = new Rect(initPos);
		color[4] = addRect(color[4], moveDown);
		for (int i = 5; i < 8; i++) {
			color[i] = new Rect(color[i - 1]);
			color[i] = addRect(color[i], moveRight);
		}
		Bitmap color_popover = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.color_popover)).getBitmap();
		Bitmap color_highlighter = ((BitmapDrawable) getResources()
				.getDrawable(R.drawable.color_highlighter)).getBitmap();
		Bitmap color_slider = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.color_slider)).getBitmap();
		colorpop = new BitmapDrawable(color_popover);
		hightlighter = new BitmapDrawable(color_highlighter);
		slider = new BitmapDrawable(color_slider);
		circle = new ShapeDrawable(new OvalShape());
		circle.getPaint().setStyle(Style.FILL);
		colorpop.setBounds(0, 0, color_popover.getWidth(),
				color_popover.getHeight());

		setHighterPos(color[initColor]);
		colorChanged(colorNum[initColor]);
		setSliderPos(initSliderPos);
		widthChanged(reflectToWidth(initSliderPos));
		this.setFocusable(true);
		this.setFocusableInTouchMode(true);
	}

	private void colorChanged(int color) {
		if (colorchangedlistener != null) {
			colorchangedlistener.colorChanged(color);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		this.setMeasuredDimension(getMenuWidth(), getMenuHeight());
	}

	private void widthChanged(int width) {
		if (widthchangedlistener != null) {
			widthchangedlistener.widthChanged(width);
		}
	}

	public void setColorChangedListener(
			ColorChangedListener colorchangedlistener) {
		this.colorchangedlistener = colorchangedlistener;
	}

	public void setWidthChangedListener(
			WidthChangedListener widthchangedlistener) {
		this.widthchangedlistener = widthchangedlistener;
	}

	public int getPaintWidth() {
		return circle.getBounds().width();
	}

	public int getMenuWidth() {
		return colorpop.getBitmap().getWidth();
	}

	public int getMenuHeight() {
		return colorpop.getBitmap().getHeight();
	}

	private Rect addRect(Rect r1, Rect r2) {
		return new Rect(r1.left + r2.left, r1.top + r2.top,
				r1.right + r2.right, r1.bottom + r2.bottom);
	}

	private int reflectToWidth(float pos) {
		return (int) ((pos - sliderrect.left)
				/ (sliderrect.right - sliderrect.left)
				* (sliderMax - sliderMin) + sliderMin);
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		// TODO Auto-generated method stub
		Log.i("MY","Coming");
		if(!gainFocus) {
			this.setVisibility(View.GONE);
		}
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}

	private void setSliderPos(int x) {
		slider.setBounds(x - sliderRange.left, sliderRange.top, x
				+ sliderRange.right, sliderRange.bottom);
		this.invalidate();
	}

	private void setHighterPos(Rect rect) {
		Rect r = new Rect(rect);
		r = addRect(r, offset);
		hightlighter.setBounds(r);
		this.invalidate();
	}

	protected void onDraw(Canvas canvas) {
		colorpop.draw(canvas);
		hightlighter.draw(canvas);
		slider.draw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 8; i++) {
			if (color[i].contains((int) event.getX(), (int) event.getY())) {
				setHighterPos(color[i]);
				colorChanged(colorNum[i]);
			}
		}
		if (sliderrect.contains((int) event.getX(), (int) event.getY())) {
			setSliderPos((int) event.getX());
			widthChanged(reflectToWidth(event.getX()));
		}
		return super.onTouchEvent(event);
	}
}
