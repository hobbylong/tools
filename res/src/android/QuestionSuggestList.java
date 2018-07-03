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
public class QuestionSuggestList extends Activity implements OnScrollListener {
Context context;
public View containView;
String searchText;
/**查询*/
Button queryButton;
/**提交*/
Button summitButton;
/**back*/
TextView backTextView;
/**需求及故障*/
TextView titleTextView;
/**至*/
TextView zhiTextView;
/**模块名称*/
TextView modelNameTextView;
/**系统名称*/
TextView applicationNameTextView;
/**|*/
TextView lineTextView;
/**需求及建议*/
TextView questionTypeTextView;
/**2018*/
TextView feedBackDateTimeTextView;
/**sfds*/
TextView questionTextView;
/***/
EditText dealNameEditText;
public QuestionSuggestList()
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
			containView = inflateView(R.layout.questionSuggestList);
//查询
queryButton = (Button) containView
					.findViewById(R.id.queryButton);
queryButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//提交
summitButton = (Button) containView
					.findViewById(R.id.summitButton);
summitButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
}
					});
//back
backTextView= (TextView) containView.findViewById(R.id.backTextView);
//需求及故障
titleTextView= (TextView) containView.findViewById(R.id.titleTextView);
//至
zhiTextView= (TextView) containView.findViewById(R.id.zhiTextView);
//模块名称
modelNameTextView= (TextView) containView.findViewById(R.id.modelNameTextView);
//系统名称
applicationNameTextView= (TextView) containView.findViewById(R.id.applicationNameTextView);
//|
lineTextView= (TextView) containView.findViewById(R.id.lineTextView);
//需求及建议
questionTypeTextView= (TextView) containView.findViewById(R.id.questionTypeTextView);
//2018
feedBackDateTimeTextView= (TextView) containView.findViewById(R.id.feedBackDateTimeTextView);
//sfds
questionTextView= (TextView) containView.findViewById(R.id.questionTextView);
//
dealNameEditText= (EditText) containView.findViewById(R.id.dealNameEditText);
dealNameEditText.addTextChangedListener(
new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
				
		}
}
);
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
		//intent.setClass(QuestionSuggestList.this,OperatorModify.class);
		//Bundle bundle = new Bundle();
		//bundle.putSerializable("operator",((RespondParam4463604) listData.get(position)));
		//intent.putExtras(bundle);
		//startActivityForResult(intent,n0000);
	}
}.execute("");}
public void setView(){
//查询
//提交
//back
backTextView.setText("");
//需求及故障
titleTextView.setText("");
//至
zhiTextView.setText("");
//模块名称
modelNameTextView.setText("");
//系统名称
applicationNameTextView.setText("");
//|
lineTextView.setText("");
//需求及建议
questionTypeTextView.setText("");
//2018
feedBackDateTimeTextView.setText("");
//sfds
questionTextView.setText("");
//
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
//查询
//提交
//back
//需求及故障
//至
//模块名称
//系统名称
//|
//需求及建议
//2018
//sfds
//
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

