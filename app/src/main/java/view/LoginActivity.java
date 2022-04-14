package view;

import static db.DBHelper.EMAIL;
import static db.DBHelper.PASSWORD;
import static db.DBHelper.USER_TABLE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthylife.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import db.DBHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    @BindView(R.id.login_et_name)
    EditText loginEtName;
    @BindView(R.id.login_et_password)
    EditText loginEtPassword;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.register_btn)
    Button registerBtn;
    @BindView(R.id.remember_pass)
    CheckBox box_rememberpsw;

    private DBHelper dbHelper;
    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor editor;

    String email;
    String password;

    private static final int REQUEST_CODE_GO_TO_REGIST = 100;
    private static final long DELAY_TIME = 2000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_GO_TO_REGIST:
                if (resultCode == RESULT_OK) {
                    String name = data.getStringExtra("Email");
                    String psw = data.getStringExtra("Password");
                    loginEtName.setText(name);
                    loginEtPassword.setText(psw);
                }
                break;
        }
    }

    private void init(){

        dbHelper = new DBHelper(this);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        loginEtName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return false;
            }
        });

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = sharedPreferences.getBoolean("remember_password", false);
        if (isRemember) {
            String name = sharedPreferences.getString("email", "");
            String psw = sharedPreferences.getString("password", "");
            loginEtName.setText(name);
            loginEtPassword.setText(psw);
            box_rememberpsw.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_btn:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REQUEST_CODE_GO_TO_REGIST);
                break;
            case R.id.login_btn:
                if (loginEtName.getText().toString().equals("") ||
                        loginEtPassword.getText().toString().trim().equals("")) {
                    Toast.makeText(LoginActivity.this, "手机号或密码不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    readUserInfo();
                }
                break;
        }
    }

    private boolean login(String email, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "select * from " + USER_TABLE + " where " + EMAIL + "=? and " + PASSWORD + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{email, password});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }
    private void readUserInfo() {
         email = loginEtName.getText().toString();
        password = loginEtPassword.getText().toString().trim();
        editor = sharedPreferences.edit();
        if (login(email, password)) {
            if (box_rememberpsw.isChecked()) {
                editor.putBoolean("remember_password", true);
                editor.putString("email", email);
                editor.putString("password", password);
            } else {
                editor.clear();
            }
            editor.apply();
            Toast.makeText(LoginActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "手机号或密码错误，请重新输入！", Toast.LENGTH_SHORT).show();
        }
    }
}