package com.example.nikhil.personalinfodata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
// create DBHelper class and extends from SQLiteOpenHelper

    // declare DB_NAME,DB_VERSION,TABLE_NAME,NAME,ID,PHONE,ADDRESS as private static final String

    private static final String DB_NAME = "felix.sqlite";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "person";
    private static final String NAME = "name";
    private static final String ID = "id";
    private static final String PHONE = "phone";
    private static final String ADDRESS = "address";


    public DBHelper(Context context) {
        //DB creation
        super(context, DB_NAME, null, DB_VERSION);

    }

    public void onCreate(SQLiteDatabase db) {
        //create table TABLE_NAME (ID integer primary key autoincrement, NAME text, ADDRESS text, PHONE text);
        String createTableQuery =
                "create table " + TABLE_NAME + " ( "
                        + ID + " integer primary key autoincrement, "
                        + NAME + " text, "
                        + ADDRESS + " text, "
                        + PHONE + " text);";  //  perfect query
/*
        String createTableQuery = "create table " + TABLE_NAME + " ( " + ID + " integer primary key autoincrement, "
                + NAME + " text," + PHONE + " text, " + ADDRESS + " text )";*/
        db.execSQL(createTableQuery);
    }

    public boolean addPerson(Person person) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, person.getName());
        values.put(PHONE, person.getPhone());
        values.put(ADDRESS, person.getAddress());
        long no = db.insert(TABLE_NAME, null, values);


        if (no == 0) {
            return false;
        } else {

            return true;
        }
        // return no>0;
    }


    @Override
   /* public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//this will update the table

    }*/
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Person> getPersonList() {

        List<Person> personList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor result;// to retrive the data from query
        result = db.rawQuery(query, null);
        //db.execSQL(query,null);
        if (result.moveToFirst()) {
            do {
                String name = result.getString(result.getColumnIndex(NAME));
                String phone = result.getString(result.getColumnIndex(PHONE));
                String address = result.getString(result.getColumnIndex(ADDRESS));

                Person person = new Person(name, phone, address);

                personList.add(person);

            } while (result.moveToNext());
        }
        return personList;

    }

    public Person getPersonData(String name) {
        Person person = null;
        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " where " + NAME + " = '" + name + "'";

        Cursor result;// to retrive the data from query
        result = db.rawQuery(query, null);
        if (result.moveToFirst()) {

            String Name = result.getString(result.getColumnIndex(NAME));
            String address = result.getString(result.getColumnIndex(ADDRESS));
            String phone = result.getString(result.getColumnIndex(PHONE));
            person = new Person(Name, phone, address);
        }
        return person;
    }

    public boolean updatePerson(Person person) {


        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADDRESS, person.getAddress());
        values.put(PHONE, person.getPhone());

       /* double noOfRows = db.update(TABLE_NAME, values, NAME + "='" + person.getName() + "'", null);
        double noOfRows=db.update(TABLE_NAME,
                values,
                NAME + " = ? AND " + PHONE + " = ?",
                new String[]{"Sfcc", "7356"}); its working

        double noOfRows = db.update(TABLE_NAME,
                values,
                NAME + " = ? AND " + PHONE + " = ?",
                new String[]{String.valueOf(person.getName()), String.valueOf(person.getPhone())}); */
        double noOfRows = db.update(TABLE_NAME,
                values,
                NAME + " = ?",
                new String[]{String.valueOf(person.getName())});

        return noOfRows > 0;

    }

    public Person getPersonDataDelete(String name, String phone) {
        Person person = null;
        SQLiteDatabase db = getReadableDatabase();
        //String query = "select * from " + TABLE_NAME + " where " + NAME + " = '" + name + "'";
        String select = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME + " = ? AND " + PHONE + " = ?";

        Cursor result = db.rawQuery(select, new String[]{name, phone});


        if (result.moveToFirst()) {

            String Name = result.getString(result.getColumnIndex(NAME));
            String Address = result.getString(result.getColumnIndex(ADDRESS));
            String Phone = result.getString(result.getColumnIndex(PHONE));
            person = new Person(Name, Phone, Address);
        }
        return person;
    }

    //getPersonDataDelete
    //deletePerson(person);
    public boolean deletePerson(Person person) {


        SQLiteDatabase db = getWritableDatabase();


        //double noOfRows=db.update(TABLE_NAME, values, NAME + "='"+person.getName()+"'",null);
      /*  double noOfRows=db.update(TABLE_NAME,
                values,
                NAME + " = ? AND " + PHONE + " = ?",
                new String[]{"Sfcc", "7356"}); its working*/

        double noOfRows = db.delete(TABLE_NAME, NAME + " = ? AND " + PHONE + " = ?",
                new String[]{String.valueOf(person.getName()), String.valueOf(person.getPhone())});

        return noOfRows > 0;

    }
}
