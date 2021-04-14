package com.example.museumtickets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String museum;
    String[] listItem;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.museums);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Museum of Modern Art");
        arrayList.add("Metropolitan Museum of Art");
        arrayList.add("Museum of Natural History");
        arrayList.add("Soloman R. Guggenheim Museum");
        //listItem= getResources().getStringArray(R.array.museum_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent=new Intent(MainActivity.this, Activity2.class);
                String clickedItem=(String) list.getItemAtPosition(position);
                if(clickedItem.equals("Museum of Modern Art")){
                    intent.putExtra("MUSEUM", "MOMA");
                }
                else if(clickedItem.equals("Metropolitan Museum of Art")){
                    intent.putExtra("MUSEUM", "MET");
                    //startActivity(intent);
                }
                else if(clickedItem.equals("Museum of Natural History")){
                    intent.putExtra("MUSEUM", "NatHistory");
                    //startActivity(intent);
                }
                else if(clickedItem.equals("Soloman R. Guggenheim Museum")){
                    intent.putExtra("MUSEUM", "Gugg");
                    //startActivity(intent);
                }
                startActivity(intent);
            }
        });

    }
}