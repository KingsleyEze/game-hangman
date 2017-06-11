package com.sybios.sygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    String mWord;
    int mFailedCounter = 0;
    int mGuessedLetter = 0;
    int mPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRandomWord();
    }

    /**
     * Captures letter from the view
     * @param view (button clicked)
     */
    public void introduceLetter(View view){

        EditText editText = (EditText) findViewById(R.id.editTextLetter);
        String letter =  editText.getText().toString();

        editText.setText("");
        Log.d("introduceLetter", "The letter is : " + letter);

        if(letter.length() == 1){

            checkLetter(letter.toUpperCase());
        }else {

            Toast.makeText(this, "Please enter a letter", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * checks letter entered by user has a match
     * @param letter entered by the user
     */
    public void checkLetter(String letter)
    {
        char charLetters = letter.charAt(0);
        boolean letterGuessed = false;

        for (int i = 0; i < mWord.length(); i++)
        {
            char charWords = mWord.charAt(i);

            if(charWords == charLetters)
            {
                //TODO: Show Entered Letter
                Log.d("checkLetter", "There was a match");

                letterGuessed = true;

                showLetterAtIndex(i, charLetters);

                mGuessedLetter++;

            }
        }

        if(letterGuessed == false)
        {
            letterFailed(letter);
        }

        if(mGuessedLetter ==  mWord.length())
        {
            mPoints++;
            clearScreen();
            setRandomWord();
        }
    }

    /**
     * Generate random words for user to guess
     */
    public void setRandomWord()
    {
        String words = "XEBEC XEBECS XENARTHRAL XENIA XENIAL XENIAS XENIC XENOBLAST XENOBLASTS XYSTOS XYSTS XYSTUS";

        String[] arrayWords = words.split(" ");

        int randomNumber = (int) (Math.random() * arrayWords.length);

        String randomWord = arrayWords[randomNumber];

        mWord = randomWord;
    }

    /**
     * clears/reset the game
     */
    public void clearScreen(){

        //Word Reset
        TextView failedGuesses = (TextView) findViewById(R.id.failedGuesses);
        failedGuesses.setText("");

        //Counter reset
        mFailedCounter = 0;
        mGuessedLetter = 0;

        //Correct letter field clear
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutLetters);

        for(int i = 0; i < linearLayout.getChildCount(); i++)
        {
            TextView currentTextView = (TextView) linearLayout.getChildAt(i);
            currentTextView.setText("_");
        }

        //Image Reset
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid_0);
    }

    /**
     * builds strings of wrong words entered by a user.
     * @param letterFailed inserted by the user.
     */
    public void letterFailed(String letterFailed)
    {
        TextView failedGuesses = (TextView) findViewById(R.id.failedGuesses);
        String previousFail = failedGuesses.getText().toString();
        failedGuesses.setText(previousFail + letterFailed);
        mFailedCounter++;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        switch (mFailedCounter)
        {
            case 1:
                imageView.setImageResource(R.drawable.hangdroid_1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.hangdroid_2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.hangdroid_3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.hangdroid_4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.hangdroid_5);
                break;
            case 6:
                //imageView.setImageResource(R.drawable.game_over);
                //Toast.makeText(this, "GAME OVER!", Toast.LENGTH_LONG).show();
                Intent gameOverIntent = new Intent(this, GameOverActivity.class);
                gameOverIntent.putExtra("POINTS_IDENTIFIER", mPoints);
                startActivity(gameOverIntent);
                finish();
                break;
        }
    }

    /**
     * A letter guessed by the user
     * @param position of the letter
     * @param letterGuessed is the letter entered by the user
     */
    public void showLetterAtIndex(int position, char letterGuessed)
    {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutLetters);
        TextView textView = (TextView) linearLayout.getChildAt(position);
        textView.setText(Character.toString(letterGuessed));
    }
}
