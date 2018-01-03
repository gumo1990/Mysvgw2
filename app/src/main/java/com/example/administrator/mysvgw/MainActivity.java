package com.example.administrator.mysvgw;

import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView mVideoView;
    String path;
  /*  ProgressBar pb;
    Handler handler = new Handler();
        Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(!mVideoView.isPlaying()){
                pb.setVisibility(View.VISIBLE);
            }else{
                pb.setVisibility(View.GONE);
            }
            handler.postDelayed(runnable, 500);//每0.5秒监听一次是否在播放视频
        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play();
    }

    private void play() {
        mVideoView = (VideoView) findViewById(R.id.vv);
   //     pb = (ProgressBar) findViewById(R.id.pb);
        MediaController mc = new MediaController(this);
        mc.setVisibility(View.GONE);  //隐藏VideoView自带的进度条
        mVideoView.setMediaController(mc);
        path = "http://lss2.gztv.com/streaming/gzbn_gouwu/index.m3u8";
        mVideoView.setVideoPath(path);//播放网络视频
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        mVideoView.start();

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // 播放结束后的动作
                mVideoView.start();
            }
        });
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return true;
            }
        });
       /* mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if(what == MediaPlayer.MEDIA_INFO_BUFFERING_START){
                    pb.setVisibility(View.VISIBLE);
                }else if(what == MediaPlayer.MEDIA_INFO_BUFFERING_END){
                    //此接口每次回调完START就回调END,若不加上判断就会出现缓冲图标一闪一闪的卡顿现象
                    if(mp.isPlaying()){
                        pb.setVisibility(View.GONE);
                    }
                }
                return true;
            }
        });*/
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
             //   pb.setVisibility(View.GONE);//缓冲完隐藏
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
     //   handler.removeCallbacks(runnable);
    }
}
