package net.sunniwell.viewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	private String mString; // 将要绘制的字符串
	private Bitmap mBitmap; // 将要绘制的Bitmap

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 对界面进行测量从而得到此控件的位置
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec); // 宽度的模式
		int width = MeasureSpec.getSize(widthMeasureSpec);  //宽度大小
		int heightMode= MeasureSpec.getMode(heightMeasureSpec);//高度模式
		int height = MeasureSpec.getSize(heightMeasureSpec); //高度的大小
		 measureText();

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private void measureText() {
		// TODO Auto-generated method stub
		
	}

}
