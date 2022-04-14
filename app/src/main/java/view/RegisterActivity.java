package view;

import static db.DBHelper.EMAIL;
import static db.DBHelper.PASSWORD;
import static db.DBHelper.USER_TABLE;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthylife.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import db.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_et_name)
    EditText registerEtName;
    @BindView(R.id.register_et_password)
    EditText registerEtPassword;
    @BindView(R.id.register_btn)
    Button registerBtn;

    private SQLiteDatabase sqLiteDatabase;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        dbHelper = new DBHelper(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = registerEtName.getText().toString();
                String password = registerEtPassword.getText().toString();

                if (email.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(), "不能为空！", Toast.LENGTH_SHORT).show();
                }else {
                    if (isEmail(email)){
                        if (isRegistered(email)){
                            Toast.makeText(getApplicationContext(), "该手机号已被注册，注册失败！", Toast.LENGTH_SHORT).show();
                        }else {
                            RegisterUserInfo(email, password);
                            Toast.makeText(getApplicationContext(), "注册成功！", Toast.LENGTH_SHORT).show();
                            Intent intent_1 = new Intent(getApplicationContext(), LoginActivity.class);
                            intent_1.putExtra("Email", email);
                            intent_1.putExtra("Password", password);
                            setResult(RESULT_OK, intent_1);
                            finish();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "邮箱输入不正确！！", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }


    public boolean isEmail(String email) {
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        boolean isMatched = matcher.matches();
        return isMatched;
    }

    // 检查是否存在
    private boolean isRegistered(String email){
        sqLiteDatabase = dbHelper.getWritableDatabase();
        String sql = "select * from "+USER_TABLE+" where "+EMAIL+"=?";
        Cursor c = sqLiteDatabase.rawQuery(sql,new String[]{email});

        if (c.getCount() > 0){
            c.close();
            return true;
        }
        c.close();
        return false;
    }

    // 添加账号和密码
    private void RegisterUserInfo(String email,String password){
        sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL,email);
        values.put(PASSWORD,password);
        sqLiteDatabase.insert(USER_TABLE,null,values);
        sqLiteDatabase.close();
    }
}