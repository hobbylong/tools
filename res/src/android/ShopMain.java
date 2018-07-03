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
public class ShopMain extends Activity implements OnScrollListener {
Context context;
public View containView;
String searchText;
/**维护网点信息*/
Button updateShopButton;
/**维护个人信息*/
Button updateShopManagerButton;
/**广州信源大厦*/
TextView shopNameTextView;
/**广州大道中100号*/
TextView shopAddressTextView;
/**大堂经理*/
TextView shopManagerTitleTextView;
/**张三*/
TextView shopManagerValueTextView;
/**电话*/
TextView shopTelTitleTextView;
/**12323*/
TextView shopTelValueTextView;
public ShopMain()
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
			containView = inflateView(R.layout.shopMain);
//维护网点信息
updateShopButton = (Button) containView
					.findViewById(R.id.updateShopButton);
updateShopButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//维护个人信息
updateShopManagerButton = (Button) containView
					.findViewById(R.id.updateShopManagerButton);
updateShopManagerButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//广州信源大厦
shopNameTextView= (TextView) containView.findViewById(R.id.shopNameTextView);
//广州大道中100号
shopAddressTextView= (TextView) containView.findViewById(R.id.shopAddressTextView);
//大堂经理
shopManagerTitleTextView= (TextView) containView.findViewById(R.id.shopManagerTitleTextView);
//张三
shopManagerValueTextView= (TextView) containView.findViewById(R.id.shopManagerValueTextView);
//电话
shopTelTitleTextView= (TextView) containView.findViewById(R.id.shopTelTitleTextView);
//12323
shopTelValueTextView= (TextView) containView.findViewById(R.id.shopTelValueTextView);
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
		//intent.setClass(ShopMain.this,OperatorModify.class);
		//Bundle bundle = new Bundle();
		//bundle.putSerializable("operator",((RespondParam4463604) listData.get(position)));
		//intent.putExtras(bundle);
		//startActivityForResult(intent,n0000);
	}
}.execute("");}
public void setView(){
//维护网点信息
//维护个人信息
//广州信源大厦
shopNameTextView.setText("");
//广州大道中100号
shopAddressTextView.setText("");
//大堂经理
shopManagerTitleTextView.setText("");
//张三
shopManagerValueTextView.setText("");
//电话
shopTelTitleTextView.setText("");
//12323
shopTelValueTextView.setText("");
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
//维护网点信息
//维护个人信息
//广州信源大厦
//广州大道中100号
//大堂经理
//张三
//电话
//12323
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

