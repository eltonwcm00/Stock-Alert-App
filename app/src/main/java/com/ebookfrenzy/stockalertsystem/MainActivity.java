package com.ebookfrenzy.stockalertsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow();
//        }
    }
    public void btn(View View) {

        String button_text;

        button_text = ((Button) View).getText().toString();

        if (button_text.equals("Add")) {
            Intent stockAdd = new Intent(this, AddStock.class);
            startActivity(stockAdd);

        } else if(button_text.equals("Search")) {
            Intent stockSearch = new Intent(this, SearchStock.class);
            startActivity(stockSearch);

        } else if(button_text.equals("Update")) {
            Intent stockUpdate = new Intent(this, UpdateStock.class);
            startActivity(stockUpdate);

        } else if(button_text.equals("Delete")) {
            Intent stockDel = new Intent(this, DeleteStock.class);
            startActivity(stockDel);
        }
    }
}