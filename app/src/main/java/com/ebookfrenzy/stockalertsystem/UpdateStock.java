package com.ebookfrenzy.stockalertsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ebookfrenzy.stockalertsystem.ui.main.UpdateStockFragment;

public class UpdateStock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_stock_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, UpdateStockFragment.newInstance())
                    .commitNow();
        }
    }
}