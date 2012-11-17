package com.simpleyyt.screenshotmaster;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ImageTool extends Activity {
	ColorCircle cc;
	ColorMenu cm;
	RelativeLayout rl, rr;
	PenPopup pen_popup;
	RectPopup rect_popup;
	View pen_highlight;
	View arrow_selected, arrow_highlight;
	View hand_selected, hand_highlight;
	View text_selected, text_highlight;
	View rect_highlight, selected;
	ImageView pen_selected, pen, rect, rect_selected;
	TranslateAnimation downhideAction = new TranslateAnimation(
			Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
			Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
	TranslateAnimation downshowAction = new TranslateAnimation(
			Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
			Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
	TranslateAnimation upshowAction = new TranslateAnimation(
			Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
			Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
	TranslateAnimation uphideAction = new TranslateAnimation(
			Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
			Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
	boolean isShowMenu = true;
	public enum Statue {
		PEN, ARROW, HAND, TEXT, RECT, MARKER, LINE, CIRCLE
	}

	Statue tool, pen_tool, rect_tool;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.image_tool);
		cc = (ColorCircle) findViewById(R.id.circle);
		cm = (ColorMenu) findViewById(R.id.popup);
		pen_popup = (PenPopup) findViewById(R.id.pen_popup);
		rect_popup = (RectPopup) findViewById(R.id.rect_popup);
		pen_selected = (ImageView) findViewById(R.id.pen_selected);
		pen_highlight = findViewById(R.id.pen_highlight);
		arrow_selected = findViewById(R.id.arrow_selected);
		arrow_highlight = findViewById(R.id.arrow_highlight);
		hand_selected = findViewById(R.id.hand_selected);
		hand_highlight = findViewById(R.id.hand_highlight);
		text_selected = findViewById(R.id.text_selected);
		text_highlight = findViewById(R.id.text_highlight);
		rect_selected = (ImageView) findViewById(R.id.rect_selected);
		rect_highlight = findViewById(R.id.rect_highlight);
		pen = (ImageView) findViewById(R.id.pen);
		rect = (ImageView) findViewById(R.id.rect);
		rl = (RelativeLayout) findViewById(R.id.bottom_tool);
		rr = (RelativeLayout) findViewById(R.id.top_tool);
		upshowAction.setDuration(500);
		upshowAction.setFillEnabled(true);
		upshowAction.setFillAfter(true);
		downshowAction.setDuration(500);
		downshowAction.setFillEnabled(true);
		downshowAction.setFillAfter(true);
		uphideAction.setDuration(500);
		uphideAction.setFillEnabled(true);
		uphideAction.setFillAfter(true);
		downhideAction.setDuration(500);
		downhideAction.setFillEnabled(true);
		downhideAction.setFillAfter(true);
		pen_highlight.setVisibility(View.VISIBLE);
		pen_selected.setVisibility(View.VISIBLE);
		tool = Statue.PEN;
		pen_popup.setChangedListener(new ChangedListener() {

			@Override
			public void onChangedListener(int id) {
				// TODO Auto-generated method stub
				if (id == 0) {
					pen.setImageResource(R.drawable.pen_btn);
					pen_selected.setImageResource(R.drawable.pen_selected);
					pen_tool = Statue.PEN;
				} else {
					pen.setImageResource(R.drawable.marker);
					pen_selected.setImageResource(R.drawable.marker_selected);
					pen_tool = Statue.MARKER;
				}
			}
		});
		rect_popup.setChangedListener(new ChangedListener() {

			@Override
			public void onChangedListener(int id) {
				// TODO Auto-generated method stub
				if (id == 0) {
					rect.setImageResource(R.drawable.rect_btn);
					rect_selected.setImageResource(R.drawable.rect_selected);
					rect_tool = Statue.RECT;
				}
				if (id == 1) {
					rect.setImageResource(R.drawable.circle);
					rect_selected.setImageResource(R.drawable.circle_selected);
					rect_tool = Statue.CIRCLE;
				}
				if (id == 2) {
					rect.setImageResource(R.drawable.line);
					rect_selected.setImageResource(R.drawable.line_selected);
					rect_tool = Statue.LINE;
				}
			}
		});
		cm.setColorChangedListener(new ColorChangedListener() {

			@Override
			public void colorChanged(int color) {
				// TODO Auto-generated method stub
				cc.setPaintColor(color);

			}
		});
		cm.setWidthChangedListener(new WidthChangedListener() {

			@Override
			public void widthChanged(int width) {
				// TODO Auto-generated method stub
				cc.setPaintWidth(width);

			}
		});
		cc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (cm.getVisibility() == View.VISIBLE) {
					cm.setVisibility(View.GONE);
					setMenuMin();
				} else {
					pen_popup.setVisibility(View.GONE);
					rect_popup.setVisibility(View.GONE);
					cm.setVisibility(View.VISIBLE);
					setMenuMax();
				}
			}
		});
	}

	public void hideMenu() {
		rr.startAnimation(uphideAction);
		rl.startAnimation(downhideAction);
		isShowMenu = false;
	}
	public void showMenu() {
		rr.startAnimation(upshowAction);
		rl.startAnimation(downshowAction);
		isShowMenu = true;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if(isShowMenu) {
			hideMenu();
		} else {
			showMenu();
		}
		return false;
	}

	public void resetAll() {
		pen_popup.setVisibility(View.GONE);
		pen_selected.setVisibility(View.INVISIBLE);
		pen_highlight.setVisibility(View.GONE);
		arrow_selected.setVisibility(View.INVISIBLE);
		arrow_highlight.setVisibility(View.GONE);
		hand_selected.setVisibility(View.INVISIBLE);
		hand_highlight.setVisibility(View.GONE);
		text_selected.setVisibility(View.INVISIBLE);
		text_highlight.setVisibility(View.GONE);
		rect_selected.setVisibility(View.INVISIBLE);
		rect_highlight.setVisibility(View.GONE);
		cm.setVisibility(View.GONE);
		rect_popup.setVisibility(View.GONE);
		setMenuMin();
	}

	public void pen_clicked(View view) {
		if (tool == Statue.PEN) {
			if (pen_popup.getVisibility() == View.VISIBLE) {
				pen_popup.setVisibility(View.GONE);
				setMenuMin();
			} else {
				pen_popup.setVisibility(View.VISIBLE);
				setMenuMid();
			}
			cm.setVisibility(View.GONE);
		} else {
			resetAll();
			pen_highlight.setVisibility(View.VISIBLE);
			pen_selected.setVisibility(View.VISIBLE);
			tool = Statue.PEN;
		}
	}

	public void hand_clicked(View view) {
		resetAll();
		hand_highlight.setVisibility(View.VISIBLE);
		hand_selected.setVisibility(View.VISIBLE);
		tool = Statue.HAND;
	}

	public void arrow_clicked(View view) {
		resetAll();
		arrow_highlight.setVisibility(View.VISIBLE);
		arrow_selected.setVisibility(View.VISIBLE);
		tool = Statue.ARROW;
	}

	public void text_clicked(View view) {
		resetAll();
		text_highlight.setVisibility(View.VISIBLE);
		text_selected.setVisibility(View.VISIBLE);
		tool = Statue.TEXT;
	}

	public void rect_clicked(View view) {
		if (tool == Statue.RECT) {
			if (rect_popup.getVisibility() == View.VISIBLE) {
				rect_popup.setVisibility(View.GONE);
				setMenuMin();
			} else {
				rect_popup.setVisibility(View.VISIBLE);
				setMenuMid();
			}
			cm.setVisibility(View.GONE);
		} else {
			resetAll();
			rect_highlight.setVisibility(View.VISIBLE);
			rect_selected.setVisibility(View.VISIBLE);
			tool = Statue.RECT;
		}
	}

	public void setMenuMax() {
		rl.getLayoutParams().height = 320;
	}

	public void setMenuMin() {

		rl.getLayoutParams().height = 75;
	}

	public void setMenuMid() {

		rl.getLayoutParams().height = 144;
	}
}
