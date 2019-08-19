package net.sunniwell.study.selfdefinedview.view;
 
import net.sunniwell.study.selfdefinedview.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

public class SWMyView extends View {

	private boolean arr1; // 自定义属性的第一个属性值
	private int arr2; // 自定义属性的第二个属性值

	public SWMyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		getAttrs(context, attrs);
	}

	public SWMyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getAttrs(context, attrs);
	}

	/**
	 * @desc 用来得到属性的值进行调用
	 * @param context
	 * @param attrs
	 */
	private void getAttrs(Context context, AttributeSet attrs) {
		TypedArray ta = context
				.obtainStyledAttributes(attrs, R.styleable.myViewDefinedAttr);
	 arr1=ta.getBoolean(R.styleable.myViewDefinedAttr_attr1,true  );
	 arr2=ta.getInteger(R.styleable.myViewDefinedAttr_attr2, 1);
	 ta.recycle();
	
	
	
	
	}
	/**
	 * @desc 得到arr2 的属性值
	 * @return
	 */
	public int getArr2(){
		
		return arr2;
	}
	
	/**
	 * @desc 得到arr1 的属性值
	 * @return
	 */
	public boolean getArr1(){
		
		return arr1;
	}
	/**
	 * @desc 构造函数 没有属性值
	 * @param context
	 */
	public SWMyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

}
