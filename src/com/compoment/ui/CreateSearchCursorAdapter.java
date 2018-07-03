package com.compoment.ui;

import java.io.FileWriter;
import java.io.IOException;

public class CreateSearchCursorAdapter {
	public static  void create(String classname) {
		String m = "";
		
		m+="import android.content.ContentResolver;\n";
		m+="import android.content.Context;\n";
		m+="import android.database.Cursor;\n";
		m+="import android.support.v4.widget.CursorAdapter;\n";
		m+="import android.view.LayoutInflater;\n";
		m+="import android.view.View;\n";
		m+="import android.view.ViewGroup;\n";
		m+="import android.widget.TextView;\n";

		m+="public class "+classname+"Adapter extends CursorAdapter {  \n";
		m+="    private int columnIndex;  \n";
		m+="     Context context;\n";
		m+="    public "+classname+"Adapter(Context context, Cursor c, int col) {  \n";
		m+="        super(context, c);  \n";
		m+="        this.columnIndex = col;  \n";
		m+="        this.context=context;\n";
		m+="    }  \n";
		m+="    @Override  \n";
		m+="    public View newView(Context context, Cursor cursor, ViewGroup parent) {  \n";
		m+="        final LayoutInflater inflater = LayoutInflater.from(context);  \n";
		m+="        final TextView view = (TextView) inflater.inflate(  \n";
		m+="                android.R.layout.simple_dropdown_item_1line, parent, false);  \n";
		m+="        view.setText(cursor.getString(columnIndex));  \n";
		m+="        return view;  \n";
		m+="    }  \n";
		m+="    @Override  \n";
		m+="    public void bindView(View view, Context context, Cursor cursor) {  \n";
		m+="        ((TextView) view).setText(cursor.getString(columnIndex));  \n";
		m+="    }  \n";
		m+="      \n";
		m+="    @Override  \n";
		m+="    public String convertToString(Cursor cursor) {  \n";
		m+="        return cursor.getString(columnIndex);  \n";
		m+="    }  \n";
		m+="    @Override  \n";
		m+="    public Cursor runQueryOnBackgroundThread(CharSequence constraint) {  \n";
		m+="        if (constraint != null) {  \n";
		m+="            String selection = \" like \'\" + constraint.toString() +\"%\'\";  \n";
		m+="            ContentResolver resolver=context.getContentResolver();\n";
		m+="    		Cursor cursor = resolver\n";
		m+="    				.query(Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.CONTENT_URI,\n";
		m+="    						new String[] {\n";
		m+="							Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.PRODUCT_NAME\n";
		m+="							,\"_id\"},//String[] trainColumns = new String[] {\"train_no\", \"id as _id\" };\n";
		m+="							//欲查询匹配的列放第一,查询结果必须有_id列，因我的表中没有，所以把id as成_id,实践证明，其实随便哪个字段都可以as _id，不用主键，这里也可以train_no as _id.\n";
		m+="    						Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.PRODUCT_NAME\n";
		m+="    								+ selection, null, null);\n";
		m+="            return cursor;  \n";
		m+="        }  \n";
		m+="        else {  \n";
		m+="            return null;  \n";
		m+="        }  \n";
		m+="    }\n";

		m+="}  \n";

		stringToFile("d:\\"+ classname
				+ "Adapter.java",m);
	}
	
	


public static void stringToFile(String fileName,String str)
{
	FileWriter fw;
	try {
		fw = new FileWriter(fileName);
		fw.write(str); 
		fw.flush();//加上这句
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
