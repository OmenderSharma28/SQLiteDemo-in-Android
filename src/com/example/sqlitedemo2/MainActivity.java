package com.example.sqlitedemo2;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText id ,name ,address;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		id = (EditText) findViewById(R.id.empId);
		name = (EditText) findViewById(R.id.empAddress);
		address = (EditText) findViewById(R.id.empName);
	}

	public void save(View v){
		DBHlper hlper = new DBHlper(this, "emp.db", null, 1);
		long a = hlper.insertIntoTable(Integer.parseInt(id.getText().toString()), 
				name.getText().toString(), address.getText().toString());
		//Toast.makeText(this, ""+a, 1).show();
		if(a!=-1){
			Toast.makeText(this, "data inserted at row "+a+" successfully", 1).show();
			id.setText("");
			name.setText("");
			address.setText("");
		}
	}

	public void show(View v){

		DBHlper h = new DBHlper(this, "emp.db", null, 1);
		StringBuffer sb = h.getDataFromTable();
		Toast.makeText(this, sb, 1).show();

	}

	public void delete(View v){

		DBHlper h = new DBHlper(this, "emp.db", null, 1);
		int i = h.deletedata();
		Toast.makeText(this, "no of deleted row is:: "+i, 1).show();

	}

	public void update(View v){

		DBHlper h = new DBHlper(this, "emp.db", null, 1);
		int i = h.updateTable();
		Toast.makeText(this, "no of updated row:: "+i, 1).show();

	}

}
