package com.example.c4;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;

import java.text.BreakIterator;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    int player=0;
    int[] state = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
    boolean gameEnded = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float density = displayMetrics.density;
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        int screenWidthInDp = (int) Math.ceil((screenWidth/density)-0.5f);
        int screenHeightInDp = (int) Math.ceil((screenHeight/density)-0.5f);
        if(screenWidthInDp==393 || screenHeightInDp==393){
            setContentView(R.layout.activity_main393);
        }
        else if(screenWidthInDp==731 || screenHeightInDp==731){
            setContentView(R.layout.activity_main731h);
        }
        else{
            setContentView(R.layout.activity_main);
        }
    }

    public void dropin(View view){
        ImageView counter = (ImageView) view;
        int chosen = Integer.parseInt(counter.getTag().toString());

        if (state[chosen] == 2 && isMoveLegal(chosen) && gameEnded==false)
        {
            TextView turnText = (TextView) findViewById(R.id.turnText);
            if (player == 0){turnText.setText("Current turn: \nBlack");}
            else{turnText.setText("Current turn: \nBlue");}
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
            if(notTie()) {
                if (checkforwin()) {
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    gameEnded = true;
                    TextView winText = (TextView) findViewById(R.id.winText);
                    if (player == 1) {
                        winText.setText("Blue has won!");
                    } else {
                        winText.setText("Black has won!");
                    }
                }
            }
            else{
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);
                gameEnded = true;
                TextView winText = (TextView) findViewById(R.id.winText);
                winText.setText("Tie!");
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

    public void reset(View view){
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        player = 0;
        for(int i = 0;i< state.length;i++){
            state[i]=2;
        }
        GridLayout gridlayout = (GridLayout)findViewById(R.id.grid);
        for(int i = 0;i<gridlayout.getChildCount();i++) {
            ((ImageView) gridlayout.getChildAt(i)).setImageResource(0);
        }
        TextView turnText = (TextView) findViewById(R.id.turnText);
        turnText.setText("Current turn: \nBlue");
        gameEnded = false;
    }
    public boolean notTie(){
        for(int i=0;i<42;i++){
            if(state[i]==2){return true;}
        }
        return false;
    }
}



