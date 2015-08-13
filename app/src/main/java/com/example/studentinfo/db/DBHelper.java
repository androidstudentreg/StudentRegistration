package com.example.studentinfo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.studentinfo.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinay on 28/6/15.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_STUDENT = "student";

    public static final String KEY_REGNO = "regno";
    public static final String KEY_NAME = "name";
    public static final String KEY_DEGREE = "degree";
    public static final String KEY_DEPT = "dept";

    public static final String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT + " " +
            "(" + KEY_REGNO + " TEXT PRIMARY KEY," +
            "" + KEY_NAME + " TEXT," +
            "" + KEY_DEGREE + " TEXT," +
            "" + KEY_DEPT + " TEXT)";

    public static final String UPDATE_STUDENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_STUDENT;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(UPDATE_STUDENT_TABLE);
        onCreate(sqLiteDatabase);
    }



    public List<Student> getStudentList() {
        List<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String [] sqlSelect = {KEY_REGNO, KEY_NAME, KEY_DEGREE, KEY_DEPT};
        queryBuilder.setTables(TABLE_STUDENT);

        Cursor cursor = queryBuilder.query(db, sqlSelect, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setRegNo(cursor.getString(0));
                student.setName(cursor.getString(1));
                student.setDegree(cursor.getString(2));
                student.setDept(cursor.getString(3));
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        return studentList;
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_REGNO, student.getRegNo());
        contentValues.put(KEY_NAME, student.getName());
        contentValues.put(KEY_DEGREE, student.getDegree());
        contentValues.put(KEY_DEPT, student.getDept());

        db.insert(TABLE_STUDENT, null, contentValues);
    }

    public void updateStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_REGNO, student.getRegNo());
        contentValues.put(KEY_NAME, student.getName());
        contentValues.put(KEY_DEGREE, student.getDegree());
        contentValues.put(KEY_DEPT, student.getDept());

        db.update(TABLE_STUDENT, contentValues, KEY_REGNO + " = ?", new String[]{student.getRegNo()});
    }

    public void deleteStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_STUDENT, KEY_REGNO + " = ?", new String[]{student.getRegNo()});
    }
}
