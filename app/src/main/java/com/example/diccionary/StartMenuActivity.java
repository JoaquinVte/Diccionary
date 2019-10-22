package com.example.diccionary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartMenuActivity extends AppCompatActivity {

    private static final int REQ_FOR_ADD_WORD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
    }

    public void playTheGameClick(View view) {
        // go to the DictionaryActivity
        Intent intent = new Intent(this, DictionaryActivity.class);
        startActivity(intent);
    }

    public void addANewWordClick(View view) {
        // go to the AddWordActivity
        Intent intent = new Intent(this, AddWordActivity.class);
        startActivityForResult(intent, REQ_FOR_ADD_WORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_FOR_ADD_WORD && resultCode == RESULT_OK)
            Toast.makeText(getApplicationContext(), "Añadida la palabra: " + data.getStringExtra("newword"), Toast.LENGTH_SHORT).show();
    }
}
