package com.example.diccionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.PrintStream;

public class AddWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
    }

    public void addThisWordClick(View view) {

        String newWord = ((EditText)findViewById(R.id.the_new_word)).getText().toString();
        String newDefn = ((EditText)findViewById(R.id.the_new_definition)).getText().toString();

        PrintStream printStream = null;

        try {

            printStream = new PrintStream(openFileOutput("added_words.txt", MODE_PRIVATE | MODE_APPEND));
            printStream.println(newWord + "\t" + newDefn);
            System.out.println("Saved: " + newWord + " ---> " + newDefn);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(printStream != null)
                printStream.close();
        }

        Intent goBack = new Intent();
        goBack.putExtra("newword",newWord);
        goBack.putExtra("newdefn",newDefn);
        setResult(RESULT_OK,goBack);
        finish();
    }
}
