package com.quizapp.br;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.quizapp.br.util.Dominios;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rgAnswers1) RadioGroup rgAnswers1;
    @BindView(R.id.rgAnswers2) RadioGroup rgAnswers2;
    @BindView(R.id.rgAnswers3) RadioGroup rgAnswers3;
    @BindView(R.id.rgAnswers4) RadioGroup rgAnswers4;
    @BindView(R.id.rgAnswers5) RadioGroup rgAnswers5;
    @BindView(R.id.rgAnswers6) RadioGroup rgAnswers6;
    @BindView(R.id.rgAnswers7) RadioGroup rgAnswers7;
    @BindView(R.id.rgAnswers8) RadioGroup rgAnswers8;
    @BindView(R.id.rgAnswers9) RadioGroup rgAnswers9;
    @BindView(R.id.rgAnswers10) RadioGroup rgAnswers10;

    @BindView(R.id.rbAnswer1_1) RadioButton rbAnswer1_1;
    @BindView(R.id.rbAnswer1_2) RadioButton rbAnswer1_2;
    @BindView(R.id.rbAnswer1_3) RadioButton rbAnswer1_3;
    @BindView(R.id.rbAnswer1_4) RadioButton rbAnswer1_4;

    @BindView(R.id.rbAnswer2_1) RadioButton rbAnswer2_1;
    @BindView(R.id.rbAnswer2_2) RadioButton rbAnswer2_2;
    @BindView(R.id.rbAnswer2_3) RadioButton rbAnswer2_3;
    @BindView(R.id.rbAnswer2_4) RadioButton rbAnswer2_4;

    @BindView(R.id.rbAnswer3_1) RadioButton rbAnswer3_1;
    @BindView(R.id.rbAnswer3_2) RadioButton rbAnswer3_2;
    @BindView(R.id.rbAnswer3_3) RadioButton rbAnswer3_3;
    @BindView(R.id.rbAnswer3_4) RadioButton rbAnswer3_4;

    @BindView(R.id.rbAnswer4_1) RadioButton rbAnswer4_1;
    @BindView(R.id.rbAnswer4_2) RadioButton rbAnswer4_2;
    @BindView(R.id.rbAnswer4_3) RadioButton rbAnswer4_3;
    @BindView(R.id.rbAnswer4_4) RadioButton rbAnswer4_4;

    @BindView(R.id.rbAnswer5_1) RadioButton rbAnswer5_1;
    @BindView(R.id.rbAnswer5_2) RadioButton rbAnswer5_2;
    @BindView(R.id.rbAnswer5_3) RadioButton rbAnswer5_3;
    @BindView(R.id.rbAnswer5_4) RadioButton rbAnswer5_4;

    @BindView(R.id.rbAnswer6_1) RadioButton rbAnswer6_1;
    @BindView(R.id.rbAnswer6_2) RadioButton rbAnswer6_2;
    @BindView(R.id.rbAnswer6_3) RadioButton rbAnswer6_3;
    @BindView(R.id.rbAnswer6_4) RadioButton rbAnswer6_4;

    @BindView(R.id.rbAnswer7_1) RadioButton rbAnswer7_1;
    @BindView(R.id.rbAnswer7_2) RadioButton rbAnswer7_2;
    @BindView(R.id.rbAnswer7_3) RadioButton rbAnswer7_3;
    @BindView(R.id.rbAnswer7_4) RadioButton rbAnswer7_4;

    @BindView(R.id.rbAnswer8_1) RadioButton rbAnswer8_1;
    @BindView(R.id.rbAnswer8_2) RadioButton rbAnswer8_2;
    @BindView(R.id.rbAnswer8_3) RadioButton rbAnswer8_3;
    @BindView(R.id.rbAnswer8_4) RadioButton rbAnswer8_4;

    @BindView(R.id.rbAnswer9_1) RadioButton rbAnswer9_1;
    @BindView(R.id.rbAnswer9_2) RadioButton rbAnswer9_2;
    @BindView(R.id.rbAnswer9_3) RadioButton rbAnswer9_3;
    @BindView(R.id.rbAnswer9_4) RadioButton rbAnswer9_4;

    @BindView(R.id.rbAnswer10_1) RadioButton rbAnswer10_1;
    @BindView(R.id.rbAnswer10_2) RadioButton rbAnswer10_2;
    @BindView(R.id.rbAnswer10_3) RadioButton rbAnswer10_3;
    @BindView(R.id.rbAnswer10_4) RadioButton rbAnswer10_4;

    @BindView(R.id.cbAnswer11_1) CheckBox cbAnswer11_1;
    @BindView(R.id.cbAnswer11_2) CheckBox cbAnswer11_2;
    @BindView(R.id.cbAnswer11_3) CheckBox cbAnswer11_3;
    @BindView(R.id.cbAnswer11_4) CheckBox cbAnswer11_4;
    @BindView(R.id.cbAnswer11_5) CheckBox cbAnswer11_5;
    @BindView(R.id.cbAnswer11_6) CheckBox cbAnswer11_6;
    @BindView(R.id.cbAnswer11_7) CheckBox cbAnswer11_7;
    @BindView(R.id.cbAnswer11_8) CheckBox cbAnswer11_8;

    @BindView(R.id.edAnswer12_1) EditText edAnswer12_1;

    @BindView(R.id.tvResult) TextView tvResult;

    private int score = 0;
    private String resQuestion1 = null;
    private String resQuestion2 = null;
    private String resQuestion3 = null;
    private String resQuestion4 = null;
    private String resQuestion5 = null;
    private String resQuestion6 = null;
    private String resQuestion7 = null;
    private String resQuestion8 = null;
    private String resQuestion9 = null;
    private String resQuestion10 = null;
    private String resQuestion11 = null;
    private String resQuestion12 = null;

    @BindView(R.id.btSubmit) Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                Boolean isAllChecked = verifyIsChecked();
                if(isAllChecked){
                    verifyAnswers();
                }
            }
        });
    }

    public void verifyAnswers(){
        if(rbAnswer1_1.isChecked()){
            resQuestion1 = rbAnswer1_1.getText().toString();
        }else if(rbAnswer1_2.isChecked()){
            resQuestion1 = rbAnswer1_2.getText().toString();
        }else if(rbAnswer1_3.isChecked()){
            resQuestion1 = rbAnswer1_3.getText().toString();
        }else if(rbAnswer1_4.isChecked()){
            resQuestion1 = rbAnswer1_4.getText().toString();
        }

        if(resQuestion1.equals(Dominios.RIGHT_ANSWER_1)){
            score++;
        }

        if(rbAnswer2_1.isChecked()){
            resQuestion2 = rbAnswer2_1.getText().toString();
        }else if(rbAnswer2_2.isChecked()){
            resQuestion2 = rbAnswer2_2.getText().toString();
        }else if(rbAnswer2_3.isChecked()){
            resQuestion2 = rbAnswer2_3.getText().toString();
        }else if(rbAnswer2_4.isChecked()){
            resQuestion2 = rbAnswer2_4.getText().toString();
        }

        if(resQuestion2.equals(Dominios.RIGHT_ANSWER_2)){
            score++;
        }

        if(rbAnswer3_1.isChecked()){
            resQuestion3 = rbAnswer3_1.getText().toString();
        }else if(rbAnswer3_2.isChecked()){
            resQuestion3 = rbAnswer3_2.getText().toString();
        }else if(rbAnswer3_3.isChecked()){
            resQuestion3 = rbAnswer3_3.getText().toString();
        }else if(rbAnswer3_4.isChecked()){
            resQuestion3 = rbAnswer3_4.getText().toString();
        }

        if(resQuestion3.equals(Dominios.RIGHT_ANSWER_3)){
            score++;
        }

        if(rbAnswer4_1.isChecked()){
            resQuestion4 = rbAnswer4_1.getText().toString();
        }else if(rbAnswer4_2.isChecked()){
            resQuestion4 = rbAnswer4_2.getText().toString();
        }else if(rbAnswer4_3.isChecked()){
            resQuestion4 = rbAnswer4_3.getText().toString();
        }else if(rbAnswer4_4.isChecked()){
            resQuestion4 = rbAnswer4_4.getText().toString();
        }

        if(resQuestion4.equals(Dominios.RIGHT_ANSWER_4)){
            score++;
        }

        if(rbAnswer5_1.isChecked()){
            resQuestion5 = rbAnswer5_1.getText().toString();
        }else if(rbAnswer5_2.isChecked()){
            resQuestion5 = rbAnswer5_2.getText().toString();
        }else if(rbAnswer5_3.isChecked()){
            resQuestion5 = rbAnswer5_3.getText().toString();
        }else if(rbAnswer5_4.isChecked()){
            resQuestion5 = rbAnswer5_4.getText().toString();
        }

        if(resQuestion5.equals(Dominios.RIGHT_ANSWER_5)){
            score++;
        }

        if(rbAnswer6_1.isChecked()){
            resQuestion6 = rbAnswer6_1.getText().toString();
        }else if(rbAnswer6_2.isChecked()){
            resQuestion6 = rbAnswer6_2.getText().toString();
        }else if(rbAnswer6_3.isChecked()){
            resQuestion6 = rbAnswer6_3.getText().toString();
        }else if(rbAnswer6_4.isChecked()){
            resQuestion6 = rbAnswer6_4.getText().toString();
        }

        if(resQuestion6.equals(Dominios.RIGHT_ANSWER_6)){
            score++;
        }

        if(rbAnswer7_1.isChecked()){
            resQuestion7 = rbAnswer7_1.getText().toString();
        }else if(rbAnswer7_2.isChecked()){
            resQuestion7 = rbAnswer7_2.getText().toString();
        }else if(rbAnswer7_3.isChecked()){
            resQuestion7 = rbAnswer7_3.getText().toString();
        }else if(rbAnswer7_4.isChecked()){
            resQuestion7 = rbAnswer7_4.getText().toString();
        }

        if(resQuestion7.equals(Dominios.RIGHT_ANSWER_7)){
            score++;
        }

        if(rbAnswer8_1.isChecked()){
            resQuestion8 = rbAnswer8_1.getText().toString();
        }else if(rbAnswer8_2.isChecked()){
            resQuestion8 = rbAnswer8_2.getText().toString();
        }else if(rbAnswer8_3.isChecked()){
            resQuestion8 = rbAnswer8_3.getText().toString();
        }else if(rbAnswer8_4.isChecked()){
            resQuestion8 = rbAnswer8_4.getText().toString();
        }

        if(resQuestion8.equals(Dominios.RIGHT_ANSWER_8)){
            score++;
        }

        if(rbAnswer9_1.isChecked()){
            resQuestion9 = rbAnswer9_1.getText().toString();
        }else if(rbAnswer9_2.isChecked()){
            resQuestion9 = rbAnswer9_2.getText().toString();
        }else if(rbAnswer9_3.isChecked()){
            resQuestion9 = rbAnswer9_3.getText().toString();
        }else if(rbAnswer9_4.isChecked()){
            resQuestion9 = rbAnswer9_4.getText().toString();
        }

        if(resQuestion9.equals(Dominios.RIGHT_ANSWER_9)){
            score++;
        }

        if(rbAnswer10_1.isChecked()){
            resQuestion10 = rbAnswer10_1.getText().toString();
        }else if(rbAnswer10_2.isChecked()){
            resQuestion10 = rbAnswer10_2.getText().toString();
        }else if(rbAnswer10_3.isChecked()){
            resQuestion10 = rbAnswer10_3.getText().toString();
        }else if(rbAnswer10_4.isChecked()){
            resQuestion10 = rbAnswer10_4.getText().toString();
        }

        if(resQuestion10.equals(Dominios.RIGHT_ANSWER_10)){
            score++;
        }

        if(cbAnswer11_1.isChecked() && cbAnswer11_2.isChecked()
                && cbAnswer11_3.isChecked() && cbAnswer11_2.isChecked()
                && cbAnswer11_5.isChecked() && !cbAnswer11_6.isChecked()
                && !cbAnswer11_7.isChecked() && !cbAnswer11_8.isChecked()){
            score++;
        }

        String resQuestion12 = edAnswer12_1.getText().toString().toUpperCase();
        if(resQuestion12.equals("GAME OF THRONES")){
            score++;
        }

        Toast.makeText(getApplicationContext(), "Total: " + score + " of 11 questions", Toast.LENGTH_SHORT).show();
        tvResult.setText("Total: " + score + " of 11 questions");
    }

    public Boolean verifyIsChecked(){
        Boolean res = true;

        if(rgAnswers1.getCheckedRadioButtonId() == -1){
            Toast.makeText(getApplicationContext(), "Please answer question 1", Toast.LENGTH_SHORT).show();
            res = false;
        }else if(rgAnswers2.getCheckedRadioButtonId() == -1){
            Toast.makeText(getApplicationContext(), "Please answer question 2", Toast.LENGTH_SHORT).show();
            res = false;
        }else if(rgAnswers3.getCheckedRadioButtonId() == -1){
            Toast.makeText(getApplicationContext(), "Please answer question 3", Toast.LENGTH_SHORT).show();
            res = false;
        }else if(rgAnswers4.getCheckedRadioButtonId() == -1){
            Toast.makeText(getApplicationContext(), "Please answer question 4", Toast.LENGTH_SHORT).show();
            res = false;
        }else if(rgAnswers5.getCheckedRadioButtonId() == -1){
            Toast.makeText(getApplicationContext(), "Please answer question 5", Toast.LENGTH_SHORT).show();
            res = false;
        }else if(rgAnswers6.getCheckedRadioButtonId() == -1){
            Toast.makeText(getApplicationContext(), "Please answer question 6", Toast.LENGTH_SHORT).show();
            res = false;
        }else if(rgAnswers7.getCheckedRadioButtonId() == -1){
            Toast.makeText(getApplicationContext(), "Please answer question 7", Toast.LENGTH_SHORT).show();
            res = false;
        }else if(rgAnswers8.getCheckedRadioButtonId() == -1){
            Toast.makeText(getApplicationContext(), "Please answer question 8", Toast.LENGTH_SHORT).show();
            res = false;
        }else if(rgAnswers9.getCheckedRadioButtonId() == -1){
            Toast.makeText(getApplicationContext(), "Please answer question 9", Toast.LENGTH_SHORT).show();
            res = false;
        }else if(rgAnswers10.getCheckedRadioButtonId() == -1){
            Toast.makeText(getApplicationContext(), "Please answer question 10", Toast.LENGTH_SHORT).show();
            res = false;
        }
        if(!cbAnswer11_1.isChecked() && !cbAnswer11_2.isChecked()
                && !cbAnswer11_3.isChecked() && !cbAnswer11_2.isChecked()
                && !cbAnswer11_5.isChecked() && !cbAnswer11_6.isChecked()
                && !cbAnswer11_7.isChecked() && !cbAnswer11_8.isChecked()){
            Toast.makeText(getApplicationContext(), "Please answer question 11", Toast.LENGTH_SHORT).show();
            res = false;
        }
        if(edAnswer12_1.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please answer question 12", Toast.LENGTH_SHORT).show();
            res = false;
        }

        return res;
    }
}
