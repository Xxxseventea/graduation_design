package view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.healthylife.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import adapter.SearchAdapter;
import bean.FoodDetailBean;
import bean.SearchBean;
import http.MyCallBack;
import http.OkHttpUtils;
import okhttp3.Request;
import okhttp3.Response;
import utils.ChartUtils;

public class FoodDetailActivity extends AppCompatActivity {
    TextView name;
    ImageView point;
    TextView status;
    TextView energy;


    TextView ginum;
    TextView glnum;
    TextView gidsp;
    TextView gldsp;

    String[] ss;
    PieChart mChart;
    ChartUtils c;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1){
                case 1:{
                    FoodDetailBean foodDetailBean = (FoodDetailBean) msg.obj;
                    name.setText(foodDetailBean.data.getName());
                    status.setText(foodDetailBean.data.getHealthTips() + "  "+foodDetailBean.data.getHealthSuggest());
                    energy.setText(foodDetailBean.data.getJoule() + foodDetailBean.data.getJouleUnit());
                    ginum.setText(foodDetailBean.data.getGlycemicInfoData().getGi().getValue());
                    gidsp.setText(foodDetailBean.data.getGlycemicInfoData().getGi().getLabel());
                    glnum.setText(foodDetailBean.data.getGlycemicInfoData().getGl().getValue());
                    gldsp.setText(foodDetailBean.data.getGlycemicInfoData().getGl().getLabel());
                    if (foodDetailBean.data.getHealthLight() == 1) {
                        point.setImageResource(R.mipmap.green_point);
                    } else if(foodDetailBean.data.getHealthLight() == 2){
                        point.setImageResource(R.mipmap.orange_point);
                    } else {
                        point.setImageResource(R.mipmap.red_point);
                    }
//
                   ArrayList arrayList = new ArrayList<>();
                    arrayList.add(Float.valueOf(foodDetailBean.data.getProtein()));
                    arrayList.add(Float.valueOf(foodDetailBean.data.getFat()));
                    arrayList.add(Float.valueOf(foodDetailBean.data.getCarbohydrate()));


                    String[] ss = {"蛋白质   "+foodDetailBean.data.getProtein()+foodDetailBean.data.getProteinUnit(),"脂肪   "+foodDetailBean.data.getFat()+foodDetailBean.data.getFatUnit(),"碳水化合物   "+foodDetailBean.data.getCarbohydrate()+foodDetailBean.data.getCarbohydrateUnit()};
                    ChartUtils c = new ChartUtils(getApplication(),ss);
                 //   String[] ss = {"蛋白质   ","脂肪   ","碳水化合物   "};
                    PieData mPieData = c.getPieData(4,arrayList);
                    c.showChart(mChart, mPieData,foodDetailBean.data.getJoule());

                    break;
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        init();
    }

    public void init(){

        Intent intent = getIntent();
        String id = intent.getStringExtra("foodid");
        name = findViewById(R.id.food_detail_name);
        point = findViewById(R.id.food_detail_point);
        status = findViewById(R.id.food_detail_status);
        energy = findViewById(R.id.food_detail_energy);

        ginum = findViewById(R.id.food_detail_gi_num);
        glnum = findViewById(R.id.food_detail_gl_num);
        gidsp = findViewById(R.id.food_detail_gi_dsp);
        gldsp = findViewById(R.id.food_detail_gl_dsp);

       mChart = (PieChart) findViewById(R.id.food_detail_piechart);

     //  c = new ChartUtils(this);

//
//        ArrayList arrayList = new ArrayList<>();
//        arrayList.add(Float.valueOf(10));
//        arrayList.add(Float.valueOf(20));
//        arrayList.add(Float.valueOf(30));
//                    String[] ss = {"蛋","脂","碳"};
//        PieData mPieData = c.getPieData(4,arrayList);
//        c.showChart(mChart, mPieData,ss);

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils.getInstance().Get("https://www.mxnzp.com/api/food_heat/food/details?foodId=" + id + "&page=1&app_id=czkzgjfrgvsllllo&app_secret=TXlkZjBTYkRzUlpTV2Y0VGpCa0lMUT09", new MyCallBack() {
                    @Override
                    public void onLoadingBefore(Request request) {

                    }

                    @Override
                    public void onSuccess(Response response) throws IOException {


                        String s = response.body().string();

                        Gson gson = new Gson();
                        FoodDetailBean foodDetailBean = gson.fromJson(s,FoodDetailBean.class);

                        Message message = new Message();
                        message.arg1 = 1;
                        message.obj = foodDetailBean;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(Request request, Exception e) {

                    }

                    @Override
                    public void onError(Response response) {

                    }
                });
            }
        }).start();
    }

}