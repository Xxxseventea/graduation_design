package view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.healthylife.R;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import bean.RecordBean;

public class RecordDetailActivity extends AppCompatActivity {

    TextView weight;
    TextView jichudaixie;
    TextView zhifang;
    TextView BMI;
    TextView tizhong;

    ImageView isweight;
    ImageView istizhong;
    ImageView iszhifang;
    ImageView isBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);
        Intent intent = getIntent();
        String json = intent.getStringExtra("data");

        RecordBean recordBean = new Gson().fromJson(json,RecordBean.class);

        weight  = findViewById(R.id.record_detail_weight);
        jichudaixie = findViewById(R.id.record_detail_jichudaixie);
        zhifang = findViewById(R.id.record_detail_zhifang);
        BMI = findViewById(R.id.record_detail_bmi);
        tizhong = findViewById(R.id.record_detail_tizhong);

        isweight = findViewById(R.id.record_detail_isweight);
        istizhong = findViewById(R.id.record_detail_istizhong);
        iszhifang = findViewById(R.id.record_detail_iszhifang);
        isBMI = findViewById(R.id.record_detail_isbmi);

        weight.setText("体重：  "+recordBean.getTizhong()+"斤");
        jichudaixie.setText("基础代谢    "+recordBean.getJichudaixie()+"  kcal");
        zhifang.setText("脂肪    "+recordBean.getZhifang()+"  %");
        BMI.setText("BMI    "+recordBean.getBMI());
        tizhong.setText("体重    "+recordBean.getTizhong()+"  斤");


        if (Integer.valueOf(recordBean.getIsBMI() ) .equals(0)){
            isBMI.setImageResource(R.drawable.piandi);

        }else if (Integer.valueOf(recordBean.getIsBMI() ) .equals(1)){
            isBMI.setImageResource(R.drawable.zhengchang);
        }else if (Integer.valueOf(recordBean.getIsBMI() ) .equals(2)){
            isBMI.setImageResource(R.drawable.piangao);
        }else {
            isBMI.setImageResource(R.drawable.chaogao);
        }


        if (Integer.valueOf(recordBean.getIsweight()) .equals(0)  || Integer.valueOf(recordBean.getIsweight()) .equals(1)){
            isweight.setImageResource(R.drawable.pianshou);
            istizhong.setImageResource(R.drawable.pianshou);

        }else if (Integer.valueOf(recordBean.getIsweight()) .equals(2) ){
            isweight.setImageResource(R.drawable.zhengchang);
            istizhong.setImageResource(R.drawable.zhengchang);
        }else if (Integer.valueOf(recordBean.getIsweight()) .equals(3)){
            isweight.setImageResource(R.drawable.chaozhong);
            istizhong.setImageResource(R.drawable.chaozhong);
        }else {
            isweight.setImageResource(R.drawable.feipang);
            istizhong.setImageResource(R.drawable.feipang);
        }

        Log.d("fat",String.valueOf(recordBean.getIsfat()));
        if (Integer.valueOf(recordBean.getIsfat()) .equals(0)){

           iszhifang.setImageResource(R.drawable.piandi);

        }else if (Integer.valueOf(recordBean.getIsfat()) .equals(1)){
            Log.d("进入了吗1","?");
            iszhifang.setImageResource(R.drawable.zhengchang);
        }else if (Integer.valueOf(recordBean.getIsfat()) .equals(2)){
            Log.d("进入了吗2","?");
            iszhifang.setImageResource(R.drawable.piangao);
        }else {
            Log.d("进入了吗3","?");
            iszhifang.setImageResource(R.drawable.chaogao);
        }

    }
}