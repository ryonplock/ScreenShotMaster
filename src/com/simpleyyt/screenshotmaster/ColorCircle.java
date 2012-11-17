package com.simpleyyt.screenshotmaster;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

public class ColorCircle extends View {
	private ShapeDrawable circle;
	private final int centerX = 40;
	private final int centerY = 35;

	public ColorCircle(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public ColorCircle(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public ColorCircle(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {
		circle = new ShapeDrawable(new OvalShape());
		circle.getPaint().setStyle(Style.FILL);
		setPaintWidth(7);
		setPaintColor(0xFFFCF121);
	}

	public void setPaintColor(int color) {
		circle.getPaint().setColor(color);
		// this.invalidate();
	}

	public void setPaintWidth(int width) {
		circle.setBounds(centerX - width, centerY - width, centerX + width,
				centerY + width);
		// this.invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		circle.draw(canvas);
		super.onDraw(canvas);
	}
}
