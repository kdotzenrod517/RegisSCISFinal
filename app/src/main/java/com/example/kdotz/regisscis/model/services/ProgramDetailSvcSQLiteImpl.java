package com.example.kdotz.regisscis.model.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kdotz.regisscis.ProgramDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdotz on 7/17/2016.
 */
public class ProgramDetailSvcSQLiteImpl extends SQLiteOpenHelper implements IProgramDetailSvc {

    public static final String TABLE_NAME = "programdetails";
    public static final String ID_FIELD = "_id";
    public static final String TITLE_FIELD = "programTitle";
    public static final String DESCRIP_FIELD = "programDescrip";

    public ProgramDetailSvcSQLiteImpl (Context context) {
        super(context,
                /*db name=*/ "programdetails_db2",
                /*cursorFactory=*/ null,
                /*db version=*/1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("db", "onCreate");
        String sql = "CREATE TABLE " + TABLE_NAME
                + " (" + ID_FIELD + " INTEGER, "
                + TITLE_FIELD + " TEXT,"
                + DESCRIP_FIELD + " TEXT,"
                + " PRIMARY KEY (" + ID_FIELD + "));";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("db", "onUpdate");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //re create the table
        onCreate(db);
    }

    @Override
    public ProgramDetail create(ProgramDetail programDetail) {
        Log.d("db", "create");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE_FIELD, programDetail.getProgramName());
        values.put(DESCRIP_FIELD, programDetail.getProgramDescrip());
        long id = db.insert(TABLE_NAME, null, values);
        programDetail.setId(id);
        db.close();
        return programDetail;
    }

    public ProgramDetail getProgramDetail (long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        ID_FIELD, TITLE_FIELD, DESCRIP_FIELD}, ID_FIELD + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor !=null){
            cursor.moveToFirst();
            ProgramDetail programDetail = new ProgramDetail(
                    cursor.getString(1),
                    cursor.getString(2));
            programDetail.setId(cursor.getLong(0));
            return programDetail;
        }

        return null;
    }

    @Override
    public List<ProgramDetail> retrieveAllProgramDetails() {
        List<ProgramDetail> programDetails = new ArrayList<ProgramDetail>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query("programdetail", new String[]
                {"id", "title", "descrip"}, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            ProgramDetail programDetail = getProgramDetail(cursor);
            programDetails.add(programDetail);
            cursor.moveToNext();
        }
        cursor.close();
        return programDetails;
    }


    public ProgramDetail getProgramDetail(Cursor cursor) {
        ProgramDetail programDetail = new ProgramDetail();
        programDetail.setId(cursor.getInt(0));
        programDetail.setProgramName(cursor.getString(1));
        programDetail.setProgramDescrip(cursor.getString(2));
        return programDetail;
    }

    @Override
    public int update(ProgramDetail programDetail) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITLE_FIELD, programDetail.getProgramName());
        values.put(DESCRIP_FIELD, programDetail.getProgramDescrip());

        return db.update(TABLE_NAME, values, ID_FIELD + " = ?",
                new String[] { String.valueOf(programDetail.getId()) });
    }


    @Override
    public ProgramDetail delete(ProgramDetail programDetail) {
        return null;
    }


    public void delete(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID_FIELD + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }
}
