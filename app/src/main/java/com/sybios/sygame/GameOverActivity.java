package com.sybios.sygame;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    int mPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        int points = getIntent().getIntExtra("POINTS_IDENTIFIER",0);
        mPoint = points;

        TextView textView = (TextView) findViewById(R.id.textViewPoints);
        textView.setText(String.valueOf(points));

    }

    public void saveScore(View view)
    {
        SharedPreferences preferences = getSharedPreferences("FIREBASE", Context.MODE_PRIVATE);

        EditText editPlayerName = (EditText) findViewById(R.id.editPlayerName);
        String playerName = editPlayerName.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();

        String previousScores = preferences.getString("SCORES", "");
        editor.putString("SCORES", playerName +" "+ mPoint + " POINTS\n" + previousScores);
        editor.commit();

        finish();

    }
}
