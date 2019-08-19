package net.sunniwell.study.selfdefinedview;
import net.sunniwell.study.selfdefinedview.R;
import net.sunniwell.study.selfdefinedview.view.SWMyView;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TextView mTv;
	private SWMyView  mMyView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
	}

	
	/**
	 * @desc  初始化数据
	 */
	private void initData() {
		boolean i=mMyView.getArr1();
		int  b=mMyView.getArr2();
		
		mTv.setText("getArr1:"+i+"getArr2:"+b);
	}


	/**
	 * @desc  初始化View 的值
	 * 
	 */
	private void initView() {
		mMyView=(SWMyView) findViewById(R.id.app_list);
		mTv=(TextView) findViewById(R.id.main_tv_showtext);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
