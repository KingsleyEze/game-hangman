package com.sybios.sygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSinglePlayerGame(View view){
        Intent intent =  new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void startMultiPlayerGame(View view){
        Intent intent =  new Intent(this, MultiplayerActivity.class);
        startActivity(intent);
    }

    public void openScores(View view){
        Intent intent = new Intent(this,ScoresActivity.class);
        startActivity(intent);
    }
}
