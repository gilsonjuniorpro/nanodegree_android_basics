package com.musicalstructure.br;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayingActivity extends AppCompatActivity {

    private Intent intent;
    private String artist;
    private String song;
    private String time;
    private String album;

    @BindView(R.id.tvArtist) TextView tvArtist;
    @BindView(R.id.tvSong) TextView tvSong;
    @BindView(R.id.tvTime) TextView tvTime;
    @BindView(R.id.tvAlbum) TextView tvAlbum;

    @BindView(R.id.ivPrevious) ImageView ivPrevious;
    @BindView(R.id.ivPlay) ImageView ivPlay;
    @BindView(R.id.ivNext) ImageView ivNext;

    private Boolean isPlaying = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        ButterKnife.bind(this);

        intent = getIntent();
        artist = intent.getStringExtra("artist");
        song = intent.getStringExtra("song");
        time = intent.getStringExtra("time");
        album = intent.getStringExtra("album");

        tvArtist.setText(artist);
        tvAlbum.setText(album);
        tvSong.setText(song);
        tvTime.setText(time);

        ivPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), getResources().getString(R.string.previous), Toast.LENGTH_SHORT).show();
            }
        });

        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlaying){
                    isPlaying = false;
                    Toast.makeText(v.getContext(), getResources().getString(R.string.pause), Toast.LENGTH_SHORT).show();
                    ivPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_circle_outline_white_48dp));
                }else{
                    isPlaying = true;
                    Toast.makeText(v.getContext(), getResources().getString(R.string.play), Toast.LENGTH_SHORT).show();
                    ivPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_circle_outline_white_48dp));
                }
            }
        });

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), getResources().getString(R.string.next), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
