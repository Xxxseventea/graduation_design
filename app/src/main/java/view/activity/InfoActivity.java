package view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.configure.PickerOptions;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.healthylife.R;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener{
    TextView height;
    TextView sex;
    TextView age;
    TextView weight;

    RelativeLayout rlheight;
    RelativeLayout rlsex;
    RelativeLayout rlage;
    RelativeLayout rlweight;

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        sharedPreferences = getSharedPreferences("userINFO", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        height = findViewById(R.id.info_height);
        age = findViewById(R.id.info_age);
        sex = findViewById(R.id.info_sex);
        weight = findViewById(R.id.info_weight);

        rlheight  = findViewById(R.id.height1);
        rlsex = findViewById(R.id.sex1);
        rlage = findViewById(R.id.age1);
        rlweight = findViewById(R.id.weight1);


        if (sharedPreferences.getString("height","") != null && sharedPreferences.getString("age","") != null &&sharedPreferences.getString("sex","") != null){
            height.setText(sharedPreferences.getString("height",""));
            sex.setText(sharedPreferences.getString("sex",""));
            age.setText(sharedPreferences.getString("age",""));
            weight.setText(sharedPreferences.getString("weight",""));
        }

        rlage.setOnClickListener(this);
        rlsex.setOnClickListener(this);
        rlheight.setOnClickListener(this);
        rlweight.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.height1:{
                showPickerView(height,getData(140,200),"height");
                break;
            }
            case R.id.age1:{
                showPickerView(age,getData(0,100),"age");
                break;
            }
            case R.id.sex1:{
                showPickerView(sex,getSex(),"sex");
                break;
            }
            case R.id.weight1:{
                showPickerView(weight,getData(70,200),"weight");
                break;
            }
        }
    }

    /**
     * 展示选择器
     * 核心代码
     */
    private void showPickerView(TextView T, List<String> list, String name) {
//      要展示的数据
        final List<String> listData = list;
//      监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
//               返回的分别是三个级别的选中位置
//              展示选中数据
                T.setText(listData.get(options1));
                editor.putString(name,listData.get(options1));
                editor.commit();
            }
        })
                .setSelectOptions(0)//设置选择第一个
                .setLineSpacingMultiplier(1.8f)//行间距
                .setOutSideCancelable(true)//点击背的地方不消失
                .setLineSpacingMultiplier((float) 2.5) //设置item的高度
                .build();//创建
//      把数据绑定到控件上面
        pvOptions.setPicker(listData);
//      展示
        pvOptions.show();
    }

    /**
     * 造一组数据
     */
    private List<String> getData(int start, int end) {
        List<String> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private List<String> getSex() {
        List<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        return list;
    }
}