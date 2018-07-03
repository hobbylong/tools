import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
public class MyCanAddPackage extends Activity implements OnScrollListener {
Context context;
public View containView;
String searchText;
/**我的套餐*/
Button mypackageButton;
<<<<<<< HEAD
/**可加倍套餐*/
TextView titleTextView;
=======
/**办理*/
Button doitButton;
/**可加办套餐*/
TextView titleTextView;
/**Y60元*/
TextView moneyTextView;
/**2013秋天*/
TextView titlevalueTextView;
/**周期*/
TextView monthtitleTextView;
/**6个月*/
TextView monthvalueTextView;
/**总计*/
TextView totaltitleTextView;
/**123*/
TextView toalvalueTextView;
/**首页*/
TextView shouyeTextView;
/**套餐*/
TextView packageTextView;
/**我的*/
TextView myTextView;
/**check*/
CheckBox checkCheckBox;
>>>>>>> 141a12c7031b19f7ad7fb0af62925724bfc2a729
public MyCanAddPackage()
{
}
  @Override
    public void onDestroy() {
        super.onDestroy();
 }
@Override
public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	Bundle extras = getIntent().getExtras();
	if (extras != null) {
		//userPara = extras.getString("userPara");
	}
context = this;
View view = init();
this.setContentView(view);
 }
public View init() {
		if (containView == null) {
			containView = inflateView(R.layout.myCanAddPackage);
//我的套餐
mypackageButton = (Button) containView
					.findViewById(R.id.mypackageButton);
mypackageButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
<<<<<<< HEAD
//可加倍套餐
titleTextView= (TextView) containView.findViewById(R.id.titleTextView);
=======
//办理
doitButton = (Button) containView
					.findViewById(R.id.doitButton);
doitButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//可加办套餐
titleTextView= (TextView) containView.findViewById(R.id.titleTextView);
//Y60元
moneyTextView= (TextView) containView.findViewById(R.id.moneyTextView);
//2013秋天
titlevalueTextView= (TextView) containView.findViewById(R.id.titlevalueTextView);
//周期
monthtitleTextView= (TextView) containView.findViewById(R.id.monthtitleTextView);
//6个月
monthvalueTextView= (TextView) containView.findViewById(R.id.monthvalueTextView);
//总计
totaltitleTextView= (TextView) containView.findViewById(R.id.totaltitleTextView);
//123
toalvalueTextView= (TextView) containView.findViewById(R.id.toalvalueTextView);
//首页
shouyeTextView= (TextView) containView.findViewById(R.id.shouyeTextView);
//套餐
packageTextView= (TextView) containView.findViewById(R.id.packageTextView);
//我的
myTextView= (TextView) containView.findViewById(R.id.myTextView);
//check
checkCheckBox= (CheckBox) containView.findViewById(R.id.checkCheckBox);
checkCheckBox.setOnClickListener(new OnClickListener() {
       @Override
       public void onClick(View v) {
if (checkCheckBox.isChecked()) {
 adapter.selectAllCheckBox();
 }else{
 adapter.disSelectAllCheckBox();
}      }
    });
>>>>>>> 141a12c7031b19f7ad7fb0af62925724bfc2a729
		}
request...();
return containView;
}
public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
}
public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
}
private View inflateView(int resource) {
		LayoutInflater vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return vi.inflate(resource, null);
}
{
		Thread thread = new Thread(new Runnable() {
			public void run() {
			}
		});
		thread.start();

Handler handler = new Handler() {
	public void handleMessage(Message msg) {
		switch (msg.what) {
			// case SHOW_LOCKER:
			// break;
			default:
				super.handleMessage(msg);
			}
		}
	};

 new  AsyncTask<String, Integer, Boolean>() {
	@Override
	protected void onPreExecute() {
	}
	@Override
	protected Boolean doInBackground(String... params) {
return true;
	}
	@Override
	protected void onPostExecute(Boolean isOk) {
		//Intent intent = new Intent();
		//intent.setClass(MyCanAddPackage.this,OperatorModify.class);
		//Bundle bundle = new Bundle();
		//bundle.putSerializable("operator",((RespondParam4463604) listData.get(position)));
		//intent.putExtras(bundle);
		//startActivityForResult(intent,n0000);
	}
}.execute("");}
public void setView(){
//我的套餐
<<<<<<< HEAD
//可加倍套餐
titleTextView.setText("");
=======
//办理
//可加办套餐
titleTextView.setText("");
//Y60元
moneyTextView.setText("");
//2013秋天
titlevalueTextView.setText("");
//周期
monthtitleTextView.setText("");
//6个月
monthvalueTextView.setText("");
//总计
totaltitleTextView.setText("");
//123
toalvalueTextView.setText("");
//首页
shouyeTextView.setText("");
//套餐
packageTextView.setText("");
//我的
myTextView.setText("");
//check
>>>>>>> 141a12c7031b19f7ad7fb0af62925724bfc2a729
}
/**返回本页刷新数据*/
int n0000=0;
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == n0000) {
				// 页面刷新  重新查询一次
				if (listData != null)
					listData.clear();
				if (adapter != null)
					adapter.notifyDataSetChanged();
				//page = 1;//分页使用
				//recordCount = 1;//分页使用
				request...();
			return;
		}
		if (data == null)
			return;
	
		Bundle bundle = data.getExtras();
		String body = bundle.getString("jsonString");
	
		if (body!=null) {
			//有数据 且头没错
				try {
//我的套餐
<<<<<<< HEAD
//可加倍套餐
=======
//办理
//可加办套餐
//Y60元
//2013秋天
//周期
//6个月
//总计
//123
//首页
//套餐
//我的
//check
>>>>>>> 141a12c7031b19f7ad7fb0af62925724bfc2a729
//注入RequestRespond
//End注入RequestRespond
} catch (Exception e) {
e.printStackTrace();
}

} else  {
//page--;//分页使用
} 
}
}

