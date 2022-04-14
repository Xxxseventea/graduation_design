package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.healthylife.R;

import java.util.ArrayList;

import bean.VideoBean;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    Context context;

    VideoBean videoBean;

    ItemCheck itemCheck;

    public VideoAdapter(Context context, VideoBean videoBean,ItemCheck itemCheck) {
        this.context = context;
        this.videoBean = videoBean;
        this.itemCheck = itemCheck;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        holder.title.setText(videoBean.itemList.get(position).data.getTitle());
        holder.userName.setText(videoBean.itemList.get(position).data.getAuthor().getName());

        Glide.with(context).load(videoBean.itemList.get(position).data.getCover().detail).into(holder.titleImage);
        Glide.with(context).load(videoBean.itemList.get(position).data.getAuthor().icon).into(holder.userHead);

        holder.titleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCheck.checkVideoItem(videoBean.getItemList().get(holder.getAdapterPosition()).data.getPlayUrl(),videoBean.getItemList().get(holder.getAdapterPosition()).data.getTitle(),videoBean.getItemList().get(holder.getAdapterPosition()).data.getCover().getDetail());
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoBean.itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView userHead;
        ImageView titleImage;
        TextView title;
        TextView userName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.video_username);
            title = itemView.findViewById(R.id.video_title);
            userHead = itemView.findViewById(R.id.video_userhead);
            titleImage = itemView.findViewById(R.id.video_titleimage);
        }
    }
}
