package com.example.ishtaloy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity{

    ImageView ivHangman;
    TextView Displayedword;
    String stringToDisplay;
    String wordtoguess;

    String[] myListOfWords={"elephant","dolphin","chicken","lion","duck","goose","zebra","cow"};
    LinearLayout main;
    int index=0;
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivHangman = findViewById(R.id.imgHangman);
        Displayedword=(TextView)findViewById(R.id.DisplayedWord);
        EditText etLetter = (EditText)findViewById(R.id.Entered);



        final int random = new Random().nextInt(8);
        wordtoguess=myListOfWords[random];
        char[] stringToDisplayArr=new char[wordtoguess.length()];
        for(int i=0; i<wordtoguess.length();i++)
        {
            stringToDisplayArr[i]='_';
        }
        stringToDisplay=String.valueOf(stringToDisplayArr);
        Displayedword.setText(stringToDisplay);

        etLetter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    if(checkIfLetterisinWord(charSequence.charAt(counter),wordtoguess)==true)
                    {
                        for(int f=0;f<wordtoguess.length();f++)
                        {
                            if(wordtoguess.charAt(f)==charSequence.charAt(counter))
                            {
                                stringToDisplayArr[f]= charSequence.charAt(counter);
                            }

                        }
                        stringToDisplay=String.valueOf(stringToDisplayArr);
                        Displayedword.setText(stringToDisplay);
                        if(checkIfLetterisinWord('_',stringToDisplay)==false) {
                            Toast.makeText(MainActivity.this,"Youv'e won!",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        if (index<6) {
                            index++;
                            int imageKey = getResources().getIdentifier("hangman" + index, "drawable", getPackageName());
                            ivHangman.setImageResource(imageKey);
                        }
                        if(index==
                                6) Toast.makeText(MainActivity.this,"Youv'e lost",Toast.LENGTH_SHORT).show();
                    }

                counter++;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
    boolean checkIfLetterisinWord(char letter, String Word){
        for(int i=0;i<Word.length();i++)
        {
            if (Word.charAt(i)==letter)
                return true;
        }
        return false;
    }
    
    
    
    
}