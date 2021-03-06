package com.ebookfrenzy.stockalertsystem.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.ebookfrenzy.stockalertsystem.MainActivity;
import com.ebookfrenzy.stockalertsystem.Product;
import androidx.lifecycle.Observer;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ebookfrenzy.stockalertsystem.R;

public class AddStockFragment extends Fragment {

    private MainViewModel mViewModel;
    private ProductListAdapter adapter;
    private TextView productId;
    private EditText productName;
    private EditText productQuantity;
    private EditText productPrice;

    public static AddStockFragment newInstance() {
        return new AddStockFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment_add_stock, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

        productId = getView().findViewById(R.id.productID);
        productName = getView().findViewById(R.id.productName);
        productQuantity = getView().findViewById(R.id.productQuantity);
        productPrice = getView().findViewById(R.id.productPrice);

        listenerSetup();
        observerSetup();
        recyclerSetup();
    }

    private void clearFields() {
        productId.setText("");
        productName.setText("");
        productQuantity.setText("");
        productPrice.setText("");
    }

    private void listenerSetup() {
        Button addButton = getView().findViewById(R.id.addButton);
        Button cancelButton = getView().findViewById(R.id.btnCancel);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = productName.getText().toString();
                String quantity = productQuantity.getText().toString();
                String price = productPrice.getText().toString();

                if (!name.equals("") && !quantity.equals("")) {
                    Product product = new Product(name, Integer.parseInt(quantity),Double.parseDouble(price));
                    mViewModel.insertProduct(product);
                    clearFields();

                    Toast.makeText(getActivity(),"Stock is added!",Toast.LENGTH_SHORT).show();

                } else {
                    productId.setText("Incomplete information");
                }}
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void observerSetup() {
        mViewModel.getAllProducts().observe(getViewLifecycleOwner(),
                new Observer<List<Product>>() {
                    public void onChanged(@Nullable final List<Product> products) {
                        adapter.setProductList(products);
                    }
                });
    }

    private void recyclerSetup() {

        RecyclerView recyclerView;
        adapter = new ProductListAdapter(R.layout.product_list_item);
        recyclerView = getView().findViewById(R.id.product_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}