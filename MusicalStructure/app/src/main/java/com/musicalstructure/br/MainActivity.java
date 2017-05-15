package com.musicalstructure.br;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.artist1) TextView artist1;
    @BindView(R.id.artist2) TextView artist2;
    @BindView(R.id.artist3) TextView artist3;
    @BindView(R.id.artist4) TextView artist4;
    @BindView(R.id.artist5) TextView artist5;
    @BindView(R.id.artist6) TextView artist6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        artist1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SongsActivity.class);
                intent.putExtra("artist", getResources().getString(R.string.artist1));
                startActivity(intent);
            }
        });

        artist2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SongsActivity.class);
                intent.putExtra("artist", getResources().getString(R.string.artist2));
                startActivity(intent);
            }
        });

        artist3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SongsActivity.class);
                intent.putExtra("artist", getResources().getString(R.string.artist3));
                startActivity(intent);
            }
        });

        artist4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SongsActivity.class);
                intent.putExtra("artist", getResources().getString(R.string.artist4));
                startActivity(intent);
            }
        });

        artist5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SongsActivity.class);
                intent.putExtra("artist", getResources().getString(R.string.artist5));
                startActivity(intent);
            }
        });

        artist6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SongsActivity.class);
                intent.putExtra("artist", getResources().getString(R.string.artist6));
                startActivity(intent);
            }
        });
    }
}
