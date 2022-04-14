package view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthylife.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

import adapter.RecordAdapter;
import adapter.RecordItemCheck;
import bean.RecordBean;
import db.SaveData;
import utils.StandardUtils;
import view.activity.RecordDetailActivity;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordFragment extends Fragment implements View.OnClickListener, RecordItemCheck {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FloatingActionButton recordAdd;
    EditText editText;
    RecordItemCheck recordItemCheck;


    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button b0;
    Button b_dot;
    Button b_finish;
    Button b_delete;
    TextView zhifang;
    TextView weight;
    TextView BMI;
    TextView weightPoint;


    String s;
    String sex;
    String height;
    String age;


    StandardUtils standardUtils;
    Double bmi_num;
    int isbmi_num;
    Double fat_num;
    int baselmetabolism_num;
    int isweight_num;
    int isfat;

    SharedPreferences todayInfo;
    SharedPreferences.Editor editor;

    RecyclerView recyclerView;
    RecordBean recordBean;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1){
                case 1:{
                    int sex1 = 0;
                    if (sex == "女"){
                        sex1 = 0;
                    }else {
                        sex1 = 1;
                    }


                    float weight_num = Float.valueOf(s);
                    recordBean = new RecordBean();
                   bmi_num =  standardUtils.BMI(weight_num,Integer.valueOf(height));
                   isbmi_num = standardUtils.isBMIStandard(bmi_num);
                   fat_num =  standardUtils.fat(bmi_num,Integer.valueOf(age),sex1);
                   baselmetabolism_num =  standardUtils.BasalMetabolism(sex1,weight_num,Integer.valueOf(height),Integer.valueOf(age));
                   isweight_num = standardUtils.isWeightStandard(Integer.valueOf(height),sex1,weight_num);
                   isfat = standardUtils.isFatStandard(fat_num);
                   zhifang.setText(String.valueOf(fat_num));
                   weight.setText(s);
                   weightPoint.setText(".0");
                   BMI.setText(String.valueOf(bmi_num));
                   Log.d(s,s);
                    if (s.contains(".")) {
                        String[] ss = s.split("\\.");
                        Log.d(ss[0],ss[1]);
                        weight.setText(ss[0]);
                        weightPoint.setText("."+ss[1]);
                    }

                    recordBean.setBMI(String.valueOf(bmi_num));
                    recordBean.setIsBMI(isbmi_num);
                    recordBean.setIsfat(isfat);
                    recordBean.setIsweight(isweight_num);
                    recordBean.setJichudaixie(String.valueOf(baselmetabolism_num));
                    recordBean.setTizhong(String.valueOf(weight_num));
                    recordBean.setZhifang(String.valueOf(fat_num));

                    ArrayList<RecordBean> arrayList = SaveData.get(getContext());
                    arrayList.add(recordBean);
                    SaveData.save(getContext(),arrayList);


                    recordItemCheck = new RecordItemCheck() {
                        @Override
                        public void check(int position) {
                            ArrayList<RecordBean> arrayList = SaveData.get(getContext());
                            RecordBean recordBean = arrayList.get(position);
                            Intent intent = new Intent(getContext(), RecordDetailActivity.class);
                            String json = new Gson().toJson(recordBean);
                            intent.putExtra("data",json);
                            startActivity(intent);
                        }
                    };

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                        linearLayoutManager.setStackFromEnd(true);
                        linearLayoutManager.setReverseLayout(true);//列表翻转
                      RecordAdapter adapter = new RecordAdapter(Integer.valueOf(getContext().getSharedPreferences("userINFO", Context.MODE_PRIVATE).getString("weight","")),getContext(),SaveData.get(getContext()),recordItemCheck);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);

                    editor.putString("bmi_num",String.valueOf(bmi_num));
                    editor.putString("fat_num",String.valueOf(fat_num));
                    editor.putString("weight",s);
                    editor.commit();
                   s = null;
                    break;
                }
            }
        }
    };

    public RecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecordFragment newInstance(String param1, String param2) {
        RecordFragment fragment = new RecordFragment();
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


        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_record,container,false);
        zhifang = view.findViewById(R.id.zhifang_zhifang);
        weight = view.findViewById(R.id.weight);
        BMI = view.findViewById(R.id.bmi_bmi);
        weightPoint = view.findViewById(R.id.weight_point);
        recyclerView = view.findViewById(R.id.record_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setReverseLayout(true);//列表翻转
        linearLayoutManager.setStackFromEnd(true);
        RecordAdapter adapter = new RecordAdapter(Integer.valueOf(getContext().getSharedPreferences("userINFO", Context.MODE_PRIVATE).getString("weight","")),getContext(),SaveData.get(getContext()),this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        FloatingActionButton actionButton = view.findViewById(R.id.record_add);



//        services = new DBServices();
        actionButton.setOnClickListener(this);
        //recordAdd.setOnClickListener(this);
        // Inflate the layout for this fragment

        sharaedpre();
        init();

        return view;


    }


    public void sharaedpre(){
        todayInfo = getContext().getSharedPreferences("todayInfo",Context.MODE_PRIVATE);
        editor = todayInfo.edit();


        if (todayInfo.getString("fat_num","") != null && todayInfo.getString("bmi_num","") != null &&todayInfo.getString("weight","") != null){
            zhifang.setText(todayInfo.getString("fat_num",""));
            weight.setText(todayInfo.getString("weight",""));
            BMI.setText(todayInfo.getString("bmi_num",""));
            weightPoint.setText(".0");
            if (todayInfo.getString("weight","").contains(".")) {
                String[] ss = todayInfo.getString("weight","").split("\\.");
                weight.setText(ss[0]);
                weightPoint.setText("."+ss[1]);
            }
        }
    }
    private void init(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("userINFO", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (sharedPreferences.getAll() != null){

            sex = sharedPreferences.getString("sex","");
            height = sharedPreferences.getString("height","");
            age  = sharedPreferences.getString("age","");
            Log.d("身高",height);
        }
        standardUtils = new StandardUtils();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.record_add: {
                popwindows();
                break;
            }
        }
    }

    public void popwindows(){

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.keyboard,null);
         b1 = view.findViewById(R.id.btn_1);
         b2 = view.findViewById(R.id.btn_2);
        b3 = view.findViewById(R.id.btn_3);
         b4 = view.findViewById(R.id.btn_4);
         b5 = view.findViewById(R.id.btn_5);
        b6 = view.findViewById(R.id.btn_6);
         b7 = view.findViewById(R.id.btn_7);
         b8 = view.findViewById(R.id.btn_8);
         b9 = view.findViewById(R.id.btn_9);
         b0 = view.findViewById(R.id.btn_0);
        b_dot = view.findViewById(R.id.btn_dot);
         b_finish = view.findViewById(R.id.btn_finish);
        b_delete = view.findViewById(R.id.btn_delete);

        editText = view.findViewById(R.id.keyboard_text);

        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setInputType(InputType.TYPE_NULL);


        s = new String();
        if ((editText.getText().toString().length() < 4 && editText.getText().toString().contains(".") )|| (editText.getText().toString().length() < 5 && !editText.getText().toString().contains("."))) {
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText.append("1");
                    s = s+"1";
                }
            });

            b0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText.append("0");
                    s = s+"0";
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText.append("2");
                    s = s+"2";
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText.append("3");
                    s = s+"3";
                }
            });
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText.append("4");
                    s = s+"4";
                }
            });
            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText.append("5");
                    s = s+"5";
                }
            });
            b6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText.append("6");
                    s = s+"6";
                }
            });
            b7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText.append("7");
                    s = s+"7";
                }
            });
            b8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText.append("8");
                    s = s+"8";
                }
            });
            b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText.append("9");
                    s = s+"9";
                }
            });
            b_dot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText.append(".");
                    s = s+".";
                }
            });
            b_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = editText.getSelectionStart();   //获取Edittext光标所在位置
                    String str = editText.getText().toString();
                    if (!str.equals("")) {//判断输入框不为空，执行删除
                        editText.getText().delete(index - 1, index);
                        s = s.substring(0,s.length() -1);
                    }
                }
            });

        }else {


        }




        PopupWindow popupWindow = new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);
// 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(R.style.PopupAnimation);
     //   popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popupwindow_background));
        popupWindow.showAtLocation(getView(),Gravity.BOTTOM, 0, 100);

        WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
        lp.alpha=0.3f;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
                lp.alpha=1.0f;
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getActivity().getWindow().setAttributes(lp);

            }
        });


        b_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                todayInfo = getContext().getSharedPreferences("todayInfo",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = todayInfo.edit();


                Message message = new Message();
                message.arg1 = 1;
                handler.sendMessage(message);
                popupWindow.dismiss();

            }
        });
//        popupWindow.showAtLocation(getView(),Gravity.BOTTOM, 0, 0);
//        WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
//        lp.alpha=0.3f;
//        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        getActivity().getWindow().setAttributes(lp);


    }

    @Override
    public void check(int position) {
        ArrayList<RecordBean> arrayList = SaveData.get(getContext());
        RecordBean recordBean = arrayList.get(position);
        Intent intent = new Intent(getContext(), RecordDetailActivity.class);
        String json = new Gson().toJson(recordBean);
        intent.putExtra("data",json);
        startActivity(intent);
    }
}