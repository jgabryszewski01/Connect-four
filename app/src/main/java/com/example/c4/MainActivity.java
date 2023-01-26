package com.example.c4;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int player=0;
    int[] state = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("SetTextI18n")
    public void dropin(View view){
        ImageView counter = (ImageView) view;
        int chosen = Integer.parseInt(counter.getTag().toString());

        if (state[chosen] == 2 && isMoveLegal(chosen) )
        {
                state[chosen] = player;

                counter.setTranslationY(-1000f);
                if (player == 0) {
                    counter.setImageResource(R.drawable.blue);
                    player = 1;
                } else {
                    counter.setImageResource(R.drawable.black);
                    player = 0;
                }
                counter.animate().translationYBy(1000f).setDuration(400);
                if(checkforwin()){
                    TextView wintext = (TextView) findViewById(R.id.winText);
                    wintext.setText("Player " + player + " has won!");
                }

        }
    }
    public boolean checkforwin(){
        for (int row = 0; row < 6; row++)
            for (int col = 0; col < 7 - 3; col++) {
                if (state[row * 7 + col] != 2 &&
                        state[row * 7 + col] == state[row * 7 + col + 1] &&
                        state[row * 7 + col] == state[row * 7 + col + 2] &&
                        state[row * 7 + col] == state[row * 7 + col + 3]) {
                    return true;
                }
            }
        for (int row = 0; row < 6 - 3; row++) {
            for (int col = 0; col < 7; col++) {
                if (state[row * 7 + col] != 2 &&
                        state[row * 7 + col] == state[(row+1) * 7 + col] &&
                        state[row * 7 + col] == state[(row+2) * 7 + col] &&
                        state[row * 7 + col] == state[(row+3) * 7 + col]) {
                    return true;
                }
            }
        }
        for (int row = 0; row < 6 - 3; row++) {
            for (int col = 0; col < 7 - 3; col++) {
                if (state[row * 7 + col] != 2 &&
                        state[row * 7 + col] == state[(row+1) * 7 + col + 1] &&
                        state[row * 7 + col] == state[(row+2) * 7 + col + 2] &&
                        state[row * 7 + col] == state[(row+3) * 7 + col + 3]) {
                    return true;
                }
            }
        }

        for (int row = 0; row < 6 - 3; row++) {
            for (int col = 3; col < 7; col++) {
                if (state[row * 7 + col] != 2 &&
                        state[row * 7 + col] == state[(row+1) * 7 + col - 1] &&
                        state[row * 7 + col] == state[(row+2) * 7 + col - 2] &&
                        state[row * 7 + col] == state[(row+3) * 7 + col - 3]) {
                    return true;
                }
            }
        }

        return false;
    }
    public boolean isMoveLegal(int chosen) {
        if (chosen+7 < 42) if (state[chosen + 7] == 2) {
            return false;
        }
        return true;
    }
}



