package com.atguigu.mobileplayer.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.atguigu.mobileplayer.R;

public class SystemVideoPlayerActivity extends AppCompatActivity {

    private VideoView vv;
    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_video_player);
        vv = (VideoView)findViewById(R.id.vv);
        //得到播放地址
        uri = getIntent().getData();

        //设置播放器三个监听:播放准备好的监听，播放完成的监听，播放出错的监听
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            //底层准备播放完成的时候回调
            @Override
            public void onPrepared(MediaPlayer mp) {
                vv.start();
            }
        });
        vv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(SystemVideoPlayerActivity.this, "播放出错了", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(SystemVideoPlayerActivity.this, "视频播放完成", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        vv.setVideoURI(uri);
        vv.setMediaController(new MediaController(this));
    }
}
