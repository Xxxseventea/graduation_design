package view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;

import com.example.healthylife.R;
import com.google.gson.Gson;

import java.io.IOException;

import adapter.FoodItemCheck;
import adapter.SearchAdapter;
import bean.SearchBean;
import bean.VideoBean;
import http.MyCallBack;
import http.OkHttpUtils;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity implements FoodItemCheck {
    RecyclerView recyclerView;
    SearchView searchView;
    SearchAdapter searchAdapter;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1){
                case 1:{
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
                    recyclerView.setLayoutManager(manager);
                    searchAdapter = new SearchAdapter(getApplicationContext(), (SearchBean) msg.obj,SearchActivity.this);
                    recyclerView.setAdapter(searchAdapter);
                    break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
    }

    public void init(){
        searchView = findViewById(R.id.search);
        recyclerView = findViewById(R.id.search_rv);
        //设置SearchView自动缩小为图标
        searchView.setIconifiedByDefault(false);//设为true则搜索栏 缩小成俄日一个图标点击展开
        //设置该SearchView显示搜索按钮
        searchView.setSubmitButtonEnabled(true);
        //设置默认提示文字
        searchView.setQueryHint("输入您想查找的内容");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpUtils.getInstance().Get("https://www.mxnzp.com/api/food_heat/food/search?keyword=" + searchView.getQuery() + "&page=1&app_id=czkzgjfrgvsllllo&app_secret=TXlkZjBTYkRzUlpTV2Y0VGpCa0lMUT09", new MyCallBack() {
                            @Override
                            public void onLoadingBefore(Request request) {

                            }

                            @Override
                            public void onSuccess(Response response) throws IOException {

                                String s = response.body().string();

                                Gson gson = new Gson();
                                SearchBean searchBean= gson.fromJson(s,SearchBean.class);
                                Message message = new Message();
                                message.arg1 = 1;
                                message.obj = searchBean;
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
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void check(int position, String foodId) {
        Intent intent = new Intent(this,FoodDetailActivity.class);
        intent.putExtra("foodid",foodId);

        startActivity(intent);
    }
}