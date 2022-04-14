package db;

import static android.provider.Telephony.Mms.Part.FILENAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import bean.RecordBean;

public class SaveData {

    public static void save(Context context, ArrayList<RecordBean> arrayList){
        SharedPreferences.Editor editor = context.getSharedPreferences("RecordData", Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString("data",json);
        editor.commit();

    }
    public static ArrayList<RecordBean> get(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("RecordData",Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("data","");

            Type type = new TypeToken<ArrayList<RecordBean>>() {
            }.getType();
            ArrayList<RecordBean> arrayList = new ArrayList<>();
            Gson gson = new Gson();
            arrayList = gson.fromJson(json, type);
            return arrayList;

    }
}
