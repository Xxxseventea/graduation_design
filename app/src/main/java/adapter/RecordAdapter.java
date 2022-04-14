package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthylife.R;

import java.util.ArrayList;

import bean.RecordBean;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    int isSunorRain;
    Context context;
    ArrayList<RecordBean> arrayList;
  RecordItemCheck recordItemCheck;

    public RecordAdapter(int isSunorRain, Context context, ArrayList<RecordBean> arrayList) {
        this.isSunorRain = isSunorRain;
        this.context = context;
        this.arrayList = arrayList;
    }

    public RecordAdapter(int isSunorRain, Context context, ArrayList<RecordBean> arrayList, RecordItemCheck recordItemCheck) {
        this.isSunorRain = isSunorRain;
        this.context = context;
        this.arrayList = arrayList;
        this.recordItemCheck = recordItemCheck;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_record,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.zhifang_num.setText(arrayList.get(position).getZhifang());
        holder.zhifang.setText("脂肪");
        holder.weight.setText("体重");
        holder.weight_num.setText(arrayList.get(position).getTizhong());
        if (isSunorRain > Float.valueOf(arrayList.get(position).getTizhong())){

            holder.sunorRain.setImageResource(R.drawable.sun);
        }else {
            holder.sunorRain.setImageResource(R.drawable.rain);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordItemCheck.check(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView sunorRain;
        TextView weight;
        TextView weight_num;
        TextView zhifang;
        TextView zhifang_num;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sunorRain = itemView.findViewById(R.id.record_sun);
            weight = itemView.findViewById(R.id.record_weight);
            weight_num = itemView.findViewById(R.id.record_weight_number);
            zhifang = itemView.findViewById(R.id.record_zhifang);
            zhifang_num = itemView.findViewById(R.id.record_zhifang_number);
            cardView = itemView.findViewById(R.id.cv);
        }
    }
}
