package com.musicalstructure.br;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.musicalstructure.br.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SongsActivity extends AppCompatActivity {


    @BindView(R.id.song1) TextView song1;
    @BindView(R.id.timeSong1) TextView timeSong1;
    @BindView(R.id.album1) TextView album1;

    @BindView(R.id.song2) TextView song2;
    @BindView(R.id.timeSong2) TextView timeSong2;
    @BindView(R.id.album2) TextView album2;

    @BindView(R.id.song3) TextView song3;
    @BindView(R.id.timeSong3) TextView timeSong3;
    @BindView(R.id.album3) TextView album3;

    @BindView(R.id.song4) TextView song4;
    @BindView(R.id.timeSong4) TextView timeSong4;
    @BindView(R.id.album4) TextView album4;

    @BindView(R.id.song5) TextView song5;
    @BindView(R.id.timeSong5) TextView timeSong5;
    @BindView(R.id.album5) TextView album5;

    @BindView(R.id.song6) TextView song6;
    @BindView(R.id.timeSong6) TextView timeSong6;
    @BindView(R.id.album6) TextView album6;

    @BindView(R.id.viewSong1) View viewSong1;
    @BindView(R.id.viewSong2) View viewSong2;
    @BindView(R.id.viewSong3) View viewSong3;
    @BindView(R.id.viewSong4) View viewSong4;
    @BindView(R.id.viewSong5) View viewSong5;
    @BindView(R.id.viewSong6) View viewSong6;

    private Intent intent;
    private String artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        ButterKnife.bind(this);

        intent = getIntent();
        artist = intent.getStringExtra("artist");

        if(artist.equals("The Cranberries")){
            song1.setText(R.string.title_song1_artist1);
            timeSong1.setText(R.string.time_song1_artist1);
            album1.setText(R.string.artist1album1);

            song2.setText(R.string.title_song2_artist1);
            timeSong2.setText(R.string.time_song2_artist1);
            album2.setText(R.string.artist1album1);

            song3.setText(R.string.title_song3_artist1);
            timeSong3.setText(R.string.time_song3_artist1);
            album3.setText(R.string.artist1album2);

            song4.setText(R.string.title_song4_artist1);
            timeSong4.setText(R.string.time_song4_artist1);
            album4.setText(R.string.artist1album2);

            song5.setText(R.string.title_song5_artist1);
            timeSong5.setText(R.string.time_song5_artist1);
            album5.setText(R.string.artist1album3);

            song6.setText(R.string.title_song6_artist1);
            timeSong6.setText(R.string.time_song6_artist1);
            album6.setText(R.string.artist1album3);

        }else if(artist.equals("Lana Del Rey")){
            song1.setText(R.string.title_song1_artist2);
            timeSong1.setText(R.string.time_song1_artist2);
            album1.setText(R.string.artist2album1);

            song2.setText(R.string.title_song2_artist2);
            timeSong2.setText(R.string.time_song2_artist2);
            album2.setText(R.string.artist2album1);

            song3.setText(R.string.title_song3_artist2);
            timeSong3.setText(R.string.time_song3_artist2);
            album3.setText(R.string.artist2album2);

            song4.setText(R.string.title_song4_artist2);
            timeSong4.setText(R.string.time_song4_artist2);
            album4.setText(R.string.artist2album2);

            song5.setText(R.string.title_song5_artist2);
            timeSong5.setText(R.string.time_song5_artist2);
            album5.setText(R.string.artist2album3);

            song6.setText(R.string.title_song6_artist2);
            timeSong6.setText(R.string.time_song6_artist2);
            album6.setText(R.string.artist2album3);

        }else if(artist.equals("John Legend")){
            song1.setText(R.string.title_song1_artist3);
            timeSong1.setText(R.string.time_song1_artist3);
            album1.setText(R.string.artist3album1);

            song2.setText(R.string.title_song2_artist3);
            timeSong2.setText(R.string.time_song2_artist3);
            album2.setText(R.string.artist3album1);

            song3.setText(R.string.title_song3_artist3);
            timeSong3.setText(R.string.time_song3_artist3);
            album3.setText(R.string.artist3album2);

            song4.setText(R.string.title_song4_artist3);
            timeSong4.setText(R.string.time_song4_artist3);
            album4.setText(R.string.artist3album2);

            song5.setText(R.string.title_song5_artist3);
            timeSong5.setText(R.string.time_song5_artist3);
            album5.setText(R.string.artist3album3);

            song6.setText(R.string.title_song6_artist3);
            timeSong6.setText(R.string.time_song6_artist3);
            album6.setText(R.string.artist3album3);

        }else if(artist.equals("Passenger")){
            song1.setText(R.string.title_song1_artist4);
            timeSong1.setText(R.string.time_song1_artist4);
            album1.setText(R.string.artist4album1);

            song2.setText(R.string.title_song2_artist4);
            timeSong2.setText(R.string.time_song2_artist4);
            album2.setText(R.string.artist4album1);

            song3.setText(R.string.title_song3_artist4);
            timeSong3.setText(R.string.time_song3_artist4);
            album3.setText(R.string.artist4album2);

            song4.setText(R.string.title_song4_artist4);
            timeSong4.setText(R.string.time_song4_artist4);
            album4.setText(R.string.artist4album2);

            song5.setText(R.string.title_song5_artist4);
            timeSong5.setText(R.string.time_song5_artist4);
            album5.setText(R.string.artist4album3);

            song6.setText(R.string.title_song6_artist4);
            timeSong6.setText(R.string.time_song6_artist4);
            album6.setText(R.string.artist4album3);

        }else if(artist.equals("Coldplay")){
            song1.setText(R.string.title_song1_artist5);
            timeSong1.setText(R.string.time_song1_artist5);
            album1.setText(R.string.artist5album1);

            song2.setText(R.string.title_song2_artist5);
            timeSong2.setText(R.string.time_song2_artist5);
            album2.setText(R.string.artist5album1);

            song3.setText(R.string.title_song3_artist5);
            timeSong3.setText(R.string.time_song3_artist5);
            album3.setText(R.string.artist5album2);

            song4.setText(R.string.title_song4_artist5);
            timeSong4.setText(R.string.time_song4_artist5);
            album4.setText(R.string.artist5album2);

            song5.setText(R.string.title_song5_artist5);
            timeSong5.setText(R.string.time_song5_artist5);
            album5.setText(R.string.artist5album3);

            song6.setText(R.string.title_song6_artist5);
            timeSong6.setText(R.string.time_song6_artist5);
            album6.setText(R.string.artist5album3);

        }else if(artist.equals("4 Non Blondes")){
            song1.setText(R.string.title_song1_artist6);
            timeSong1.setText(R.string.time_song1_artist6);
            album1.setText(R.string.artist6album1);

            song2.setText(R.string.title_song2_artist6);
            timeSong2.setText(R.string.time_song2_artist6);
            album2.setText(R.string.artist6album1);

            song3.setText(R.string.title_song3_artist6);
            timeSong3.setText(R.string.time_song3_artist6);
            album3.setText(R.string.artist6album2);

            song4.setText(R.string.title_song4_artist6);
            timeSong4.setText(R.string.time_song4_artist6);
            album4.setText(R.string.artist6album2);

            song5.setText(R.string.title_song5_artist6);
            timeSong5.setText(R.string.time_song5_artist6);
            album5.setText(R.string.artist6album3);

            song6.setText(R.string.title_song6_artist6);
            timeSong6.setText(R.string.time_song6_artist6);
            album6.setText(R.string.artist6album3);

        }


        viewSong1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), PlayingActivity.class);
                intent.putExtra("artist", artist);
                intent.putExtra("song", Util.getSongFromArtist(artist, 1));
                intent.putExtra("time", Util.getTimeFromSong(artist, 1));
                intent.putExtra("album", Util.getAlbumFromSong(artist, 1));
                startActivity(intent);
            }
        });


        viewSong2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), PlayingActivity.class);
                intent.putExtra("artist", artist);
                intent.putExtra("song", Util.getSongFromArtist(artist, 2));
                intent.putExtra("time", Util.getTimeFromSong(artist, 2));
                intent.putExtra("album", Util.getAlbumFromSong(artist, 2));
                startActivity(intent);
            }
        });

        viewSong3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), PlayingActivity.class);
                intent.putExtra("artist", artist);
                intent.putExtra("song", Util.getSongFromArtist(artist, 3));
                intent.putExtra("time", Util.getTimeFromSong(artist, 3));
                intent.putExtra("album", Util.getAlbumFromSong(artist, 3));
                startActivity(intent);
            }
        });

        viewSong4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), PlayingActivity.class);
                intent.putExtra("artist", artist);
                intent.putExtra("song", Util.getSongFromArtist(artist, 4));
                intent.putExtra("time", Util.getTimeFromSong(artist, 4));
                intent.putExtra("album", Util.getAlbumFromSong(artist, 4));
                startActivity(intent);
            }
        });

        viewSong5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), PlayingActivity.class);
                intent.putExtra("artist", artist);
                intent.putExtra("song", Util.getSongFromArtist(artist, 5));
                intent.putExtra("time", Util.getTimeFromSong(artist, 5));
                intent.putExtra("album", Util.getAlbumFromSong(artist, 5));
                startActivity(intent);
            }
        });

        viewSong6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), PlayingActivity.class);
                intent.putExtra("artist", artist);
                intent.putExtra("song", Util.getSongFromArtist(artist, 6));
                intent.putExtra("time", Util.getTimeFromSong(artist, 6));
                intent.putExtra("album", Util.getAlbumFromSong(artist, 6));
                startActivity(intent);
            }
        });
    }
}
