package com.example.vikrant.cnnct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int active = 0;
    boolean gameActive = true;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {
        ImageView star = (ImageView) view;

        int tappedstar = Integer.parseInt(star.getTag().toString());
        if (gamestate[tappedstar] == 2 && gameActive) {
            star.setTranslationX(-1000f);
            gamestate[tappedstar] = active;
            if (active == 0) {
                star.setImageResource(R.drawable.red);
                active = 1;
            } else {
                star.setImageResource(R.drawable.blue);
                active = 0;
            }
            star.animate().translationXBy(1000f).setDuration(300);
            for (int[] winningPos : winningPositions) {
                if (gamestate[winningPos[0]] == gamestate[winningPos[1]] &&
                        gamestate[winningPos[1]] == gamestate[winningPos[2]] &&
                        gamestate[winningPos[0]] != 2) {
                    gameActive = false;
                    String winner = "Blue";
                    if (gamestate[winningPos[0]] == 0) {
                        winner = "Red";
                    }
                    TextView winnerMsg = (TextView) findViewById(R.id.winnerMsg);
                    winnerMsg.setText(winner+" has won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameisOver = true;
                    for (int i: gamestate){
                        if (i==2){
                            gameisOver= false;
                            break;
                        }
                    }
                    if (gameisOver){
                        TextView winnerMsg2 = (TextView) findViewById(R.id.winnerMsg);
                        winnerMsg2.setText( " Draw" );
                        LinearLayout layout2 = (LinearLayout) findViewById(R.id.layout);
                        layout2.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view) {
        LinearLayout layout3 = (LinearLayout) findViewById(R.id.layout);
        layout3.setVisibility(View.INVISIBLE);
        active = 0;
        for (int i = 0; i < gamestate.length; i++) {
            gamestate[i] = 2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        gameActive = true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
