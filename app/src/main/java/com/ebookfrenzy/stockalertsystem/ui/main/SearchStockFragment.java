package com.ebookfrenzy.stockalertsystem.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ebookfrenzy.stockalertsystem.MainActivity;
import com.ebookfrenzy.stockalertsystem.Product;
import androidx.lifecycle.Observer;
import java.util.List;
import java.util.Locale;


import com.ebookfrenzy.stockalertsystem.R;

public class SearchStockFragment extends Fragment {

    private MainViewModel mViewModel;
    private ProductListAdapter adapter;
    private EditText productId;
    private EditText productName;
    private EditText productQuantity;
    private EditText productPrice;
    private TextView msg;

    public static SearchStockFragment newInstance() {
        return new SearchStockFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment_search_stock, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

        productId = getView().findViewById(R.id.searchproduct_productid);
        productName = getView().findViewById(R.id.searchproduct_productname);
        productQuantity = getView().findViewById(R.id.searchproduct_quantity);
        productPrice = getView().findViewById(R.id.searchproduct_price);
        msg = getView().findViewById(R.id.notMatchMsg);

        listenerSetup();
        observerSetup();
    }

    private void clearFields() {
        productId.setText("");
        productName.setText("");
        productQuantity.setText("");
        productPrice.setText("");
    }

    private void listenerSetup() {
        Button findButton = getView().findViewById(R.id.searchproduct_searchbtn);
        Button cancelButton = getView().findViewById(R.id.searchproduct_cancelbtn);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.findProduct(productName.getText().toString());
            }
        });
    }

    private void observerSetup() {
        mViewModel.getSearchResults().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
                    @Override
                    public void onChanged(@Nullable final List<Product> products) {
                        if (products.size() > 0) {
                            msg.setText("");
                            productId.setText(String.format(Locale.US, "%d", products.get(0).getId()));
                            productName.setText(products.get(0).getName());
                            productQuantity.setText(String.format(Locale.US, "%d", products.get(0).getQuantity()));
                            productPrice.setText(String.format(Locale.US, "RM%.2f", products.get(0).getPrice()));

                            Toast.makeText(getActivity(),"Stock is found!",Toast.LENGTH_SHORT).show();
                        } else {
                            msg.setText("No such Product!");
                        }
                    }
                });
    }

}