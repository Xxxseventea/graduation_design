package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Data.db";
    public static final String USER_TABLE = "usersinfo";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";



    public DBHelper(Context context) {
        super(context,DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //新建用户信息表
       sqLiteDatabase.execSQL("CREATE TABLE " + USER_TABLE + " ("
                + EMAIL + " TEXT PRIMARY KEY, "
                + PASSWORD + " TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("alter table " + USER_TABLE + " add column other string");
    }
}
