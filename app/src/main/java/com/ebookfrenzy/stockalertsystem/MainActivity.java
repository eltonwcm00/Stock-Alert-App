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

        switch (button_text) {
            case "Add":
                Intent stockAdd = new Intent(this, AddStock.class);
                startActivity(stockAdd);
                break;

            case "Search":
                Intent stockSearch = new Intent(this, SearchStock.class);
                startActivity(stockSearch);
                break;

            case "Update":
                Intent stockUpdate = new Intent(this, UpdateStock.class);
                startActivity(stockUpdate);
                break;

            case "Delete":
                Intent stockDel = new Intent(this, DeleteStock.class);
                startActivity(stockDel);
                break;
        }
    }
}