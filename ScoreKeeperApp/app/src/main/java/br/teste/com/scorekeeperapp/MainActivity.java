package br.teste.com.scorekeeperapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreHome = 0;
    int scoreGuest = 0;

    TextView tvScoreHome = null;
    TextView tvScoreGuest = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btUpHome = (ImageButton) findViewById(R.id.btUpHome);
        btUpHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment(Dominios.HOME);
            }
        });

        ImageButton btDownHome = (ImageButton) findViewById(R.id.btDownHome);
        btDownHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement(Dominios.HOME);
            }
        });

        ImageButton btUpGuest = (ImageButton) findViewById(R.id.btUpGuest);
        btUpGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment(Dominios.GUEST);
            }
        });

        ImageButton btDownGuest = (ImageButton) findViewById(R.id.btDownGuest);
        btDownGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement(Dominios.GUEST);
            }
        });

        Button btReset = (Button) findViewById(R.id.btReset);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAll();
            }
        });

        tvScoreHome = (TextView) findViewById(R.id.tvScoreHome);
        tvScoreGuest = (TextView) findViewById(R.id.tvScoreGuest);
    }

    protected void increment(String type){
        if(type.equals(Dominios.HOME)){
            scoreHome = scoreHome + 1;
            setScore(type, scoreHome);
        }else{
            scoreGuest = scoreGuest + 1;
            setScore(type, scoreGuest);
        }
    }

    protected void decrement(String type){
        if(type.equals(Dominios.HOME) && scoreHome > 0){
            scoreHome = scoreHome - 1;
            setScore(type, scoreHome);
        }else{
            if(scoreGuest > 0) {
                scoreGuest = scoreGuest - 1;
                setScore(type, scoreGuest);
            }
        }
    }

    protected void setScore(String type, int score){
        if(type.equals(Dominios.HOME)){
            tvScoreHome.setText(String.valueOf(score));
        }else{
            tvScoreGuest.setText(String.valueOf(score));
        }
    }

    protected void resetAll(){
        scoreHome = 0;
        scoreGuest = 0;
        setScore(Dominios.HOME, scoreHome);
        setScore(Dominios.GUEST, scoreGuest);
    }
}
