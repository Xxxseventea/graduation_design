package view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.healthylife.R;

public class VideoDetailActivity extends AppCompatActivity {

    cn.jzvd.JzvdStd std;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        std = findViewById(R.id.video_detail);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String image = intent.getStringExtra("image");
        String url = intent.getStringExtra("url");
        std.setUp(url
                , title);
        Glide.with(this).load(image).into(std.posterImageView);
    }
    @Override
    public void onBackPressed() {
        if (std.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        std.releaseAllVideos();
    }

}