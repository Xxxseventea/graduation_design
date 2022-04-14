package view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthylife.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.lang.reflect.Type;

import adapter.ItemCheck;
import adapter.VideoAdapter;
import bean.VideoBean;
import http.MyCallBack;
import http.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import view.activity.VideoDetailActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment implements ItemCheck {
    VideoAdapter adapter;
    RecyclerView recyclerView;
    String s = " ";
    VideoBean videoBean;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1){
                case 1:{
                    LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter  = new VideoAdapter(getContext(), (VideoBean) msg.obj,VideoFragment.this);
                    recyclerView.setAdapter(adapter);
                    break;
                }
            }
        }
    };

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VideoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        recyclerView = view.findViewById(R.id.video_rv);

//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//                OkHttpClient client=new OkHttpClient();
//                Request request = new Request.Builder().url("") .build();
//                Response response= null;
//                try {
//                    response = client.newCall(request).execute();
//                    String message=response.body().string();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://baobab.kaiyanapp.com/api/v1/search?start=1&num=50&query=健康减肥";
                OkHttpUtils.getInstance().Get(url, new MyCallBack() {
                    @Override
                    public void onLoadingBefore(Request request) {

                    }

                    @Override
                    public void onSuccess(Response response) throws IOException {

                        String s = response.body().string();

                        Gson gson = new Gson();
                        VideoBean videoBean = gson.fromJson(s,VideoBean.class);
                        Message message = new Message();
                        message.arg1 = 1;
                        message.obj = videoBean;
                        handler.sendMessage(message);
                     //   Log.d("111", "onResponse: " + response.body().string());
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


        return  view;
    }

    @Override
    public void checkVideoItem(String url, String title, String image) {
        Intent intent = new Intent(getContext(), VideoDetailActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
        intent.putExtra("image",image);
        startActivity(intent);
    }
}

