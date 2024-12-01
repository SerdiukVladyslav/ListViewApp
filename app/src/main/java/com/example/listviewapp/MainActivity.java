package com.example.listviewapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button buttonAdd;
    private ListView listView;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        buttonAdd = findViewById(R.id.buttonAdd);
        listView = findViewById(R.id.listView);

        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        // Додавання нового елемента
        buttonAdd.setOnClickListener(v -> {
            String text = editText.getText().toString().trim();
            if (!text.isEmpty()) {
                list.add(text);
                adapter.notifyDataSetChanged();
                editText.setText("");
            } else {
                Toast.makeText(MainActivity.this, "Please enter text", Toast.LENGTH_SHORT).show();
            }
        });

        // Виведення тексту у Toast при короткому натисканні
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String itemText = list.get(position);
            Toast.makeText(MainActivity.this, itemText, Toast.LENGTH_SHORT).show();
        });

        // Видалення елемента при довгому натисканні
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            list.remove((int) id);
            adapter.notifyDataSetChanged();
            return true;
        });
    }
}
