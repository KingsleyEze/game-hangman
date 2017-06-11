package com.sybios.sygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MultiplayerActivity extends AppCompatActivity {

    String mWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
    }

    public void startMultiplayerGame(View view){

        TextView textView = (TextView) findViewById(R.id.EditTextWord);
        String wordToGuess = textView.getText().toString();

        textView.setText("");

        Intent intent =  new Intent(this, GameMultiActivity.class);
        intent.putExtra("WORD_IDENTIFIER", wordToGuess);
        startActivity(intent);
    }
}
