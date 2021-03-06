package com.ebookfrenzy.stockalertsystem.ui.main;

import androidx.lifecycle.ViewModelProvider;

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
                    //Make Toast!!!!!!!!!

                } else {
                    productId.setText("Incomplete information");
                }}
        });
    }

    private void observerSetup() {
        mViewModel.getAllProducts().observe(getViewLifecycleOwner(),
                new Observer<List<Product>>() {
                    public void onChanged(@Nullable final List<Product> products) {
                        adapter.setProductList(products);
                    }
                });
//        mViewModel.getSearchResults().observe(getViewLifecycleOwner(),
//                new Observer<List<Product>>() {
//                    @Override
//                    public void onChanged(@Nullable final List<Product> products) {
//                        if (products.size() > 0) {
//                            productId.setText(String.format(Locale.US, "%d", products.get(0).getId()));
//                            productName.setText(products.get(0).getName());
//                            productQuantity.setText(String.format(Locale.US, "%d", products.get(0).getQuantity()));
//                            productPrice.setText(String.format(Locale.US, "RM%.2f", products.get(0).getPrice()));
//                        } else {
//                            productId.setText("No Match");
//                        }
//                    }
//                });
    }

    private void recyclerSetup() {

        RecyclerView recyclerView;
        adapter = new ProductListAdapter(R.layout.product_list_item);
        recyclerView = getView().findViewById(R.id.product_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}