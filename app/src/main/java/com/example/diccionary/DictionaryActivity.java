package com.example.diccionary;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class DictionaryActivity extends AppCompatActivity {

    private Map<String, String> diccionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        diccionario = new HashMap<>();

        readFileData();

        changeWord();
    }

    private void changeWord() {
        List<String> words = new ArrayList<>(diccionario.keySet());
        List<String> defin = new ArrayList<>(diccionario.values());

        Random randy = new Random();
        int randomIndex = randy.nextInt(diccionario.keySet().size());

        String wordSelected = words.get(randomIndex);
        String defnSelected = diccionario.get(wordSelected);
        defin.remove(defnSelected);

        Collections.shuffle(defin);
        defin = defin.subList(0, 4);
        defin.add(defnSelected);
        Collections.shuffle(defin);

        final TextView textView = findViewById(R.id.word);
        textView.setText(wordSelected);

        ListView listView = findViewById(R.id.list_words);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,                                       // Activity
                android.R.layout.simple_list_item_1,        // Layout
                defin // Array
        );
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                String word = textView.getText().toString();

                String difinitionSelected = parent.getItemAtPosition(position).toString();
                String definition = diccionario.get(word);

                if (difinitionSelected == definition)
                    Toast.makeText(getApplicationContext(), "Congratulations !!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Ups!", Toast.LENGTH_SHORT).show();

                changeWord();
            }
        });

    }

    private void readFileData() {

        Scanner sc = new Scanner(getResources().openRawResource(R.raw.grewords));
        readFileDataHelper(sc);

        try {
            Scanner sc2 = new Scanner(openFileInput("added_words.txt"));
            readFileDataHelper(sc2);
            System.out.println(getFileStreamPath("added_words.txt").getAbsolutePath());
        } catch (Exception e) {
            // do nothing
            System.out.println("No existe el fichero added_words.txt");
        }

    }

    private void readFileDataHelper(Scanner sc) {
        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            String[] words = linea.split("\t");
            if (words.length < 2) continue;
            diccionario.put(words[0], words[1]);
        }
    }
}
