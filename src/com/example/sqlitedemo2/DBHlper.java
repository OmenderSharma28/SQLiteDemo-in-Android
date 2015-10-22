package com.example.sqlitedemo2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHlper extends SQLiteOpenHelper{
	
	String query = "create table employee(id integer,name text,address text)";
	public DBHlper(Context context, String name, CursorFactory factory,	int version) {
		super(context, name, factory, version);
		Log.d("tag1", "on helper constructor ");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("tag1", "on create of helper");
		// TODO Auto-generated method stub
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.d("tag1", "on upgrade of helper");

	}

	public long insertIntoTable(int id,String address, String name){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("id", id);
		contentValues.put("name", name);
		contentValues.put("address", address);
		long a = db.insert("employee",null,contentValues);
		db.close();
		return a;
	}

	public StringBuffer getDataFromTable(){
		SQLiteDatabase db = getWritableDatabase();
		StringBuffer msg  = new StringBuffer();
		String [] columns ={"id", "name", "address"};
		Cursor myCur = db.query("employee", columns,null, null, null, null, "id");
		int idCol= myCur.getColumnIndex("id");
		int nameCol= myCur.getColumnIndex("name");
		int addCol= myCur.getColumnIndex("address");
		while(myCur.moveToNext()) {
			columns[0] = Integer.toString((myCur.getInt(0)));
			columns[1] = myCur.getString(1);
			columns[2] = myCur.getString(2);
					msg.append("\n"+ columns[0] + " "+ columns[1] + " "+ columns[2] );
		}
		db.close();
		return msg;
	}
	public int deletedata(){
		SQLiteDatabase db = getWritableDatabase();
		
		int deletedRow = db.delete("employee", "id='1'", null);
		return deletedRow;
	}

	public int updateTable() {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", "Anuj");
		int affactedRow = db.update("employee", contentValues, "id=1", null);
		return affactedRow;
	}
	

}
