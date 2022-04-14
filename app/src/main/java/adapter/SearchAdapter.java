package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthylife.R;

import bean.SearchBean;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context context;
    SearchBean searchBean;
    FoodItemCheck foodItemCheck;

    public SearchAdapter(Context context, SearchBean searchBean, FoodItemCheck foodItemCheck) {
        this.context = context;
        this.searchBean = searchBean;
        this.foodItemCheck = foodItemCheck;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(searchBean.data.list.get(position).getName());
        holder.calory.setText(searchBean.data.list.get(position).getCalory()+"千卡/100g");
        if (searchBean.data.getList().get(position).getHealthLevel() == 1){
            holder.imageView.setImageResource(R.mipmap.green_point);
        }else if (searchBean.data.getList().get(position).getHealthLevel() == 2){
            holder.imageView.setImageResource(R.mipmap.orange_point);
        }else {
            holder.imageView.setImageResource(R.mipmap.red_point);
        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodItemCheck.check(holder.getAdapterPosition(),searchBean.data.getList().get(holder.getAdapterPosition()).getFoodId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchBean.data.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView calory;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.food_status);
            name = itemView.findViewById(R.id.food_name);
            calory = itemView.findViewById(R.id.food_calory);
            relativeLayout  = itemView.findViewById(R.id.search_item);
        }
    }
}
