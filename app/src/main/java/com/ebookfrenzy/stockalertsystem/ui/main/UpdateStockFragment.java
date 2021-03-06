package com.ebookfrenzy.stockalertsystem.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ebookfrenzy.stockalertsystem.MainActivity;
import com.ebookfrenzy.stockalertsystem.Product;
import com.ebookfrenzy.stockalertsystem.R;

import java.util.List;
import java.util.Locale;

public class UpdateStockFragment extends Fragment {

    private MainViewModel mViewModel;
    private EditText productId;
    private EditText productName;
    private EditText productQuantity;
    private EditText productPrice;
    private TextView msg;

    public static UpdateStockFragment newInstance() {
        return new UpdateStockFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment_update_stock, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

        productId = getView().findViewById(R.id.updateproduct_id_textfield);
        productName = getView().findViewById(R.id.updateproduct_name_textfield);
        productQuantity = getView().findViewById(R.id.updateproduct_qty_textfield);
        productPrice = getView().findViewById(R.id.updateproduct_price_textfield);
        msg = getView().findViewById(R.id.message);

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
        Button findButton = getView().findViewById(R.id.update_search);
        Button updateButton = getView().findViewById(R.id.updateproduct_update_btn);
        Button cancelButton = getView().findViewById(R.id.cancelButton);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Stock is found!",Toast.LENGTH_SHORT).show();
                mViewModel.findProduct(productName.getText().toString());
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.updateProduct( productName.getText().toString(),
                                         Integer.parseInt(productQuantity.getText().toString()),
                                         Double.parseDouble(productPrice.getText().toString()),
                                         Integer.parseInt(productId.getText().toString()) );
                clearFields();


            }
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

        mViewModel.getSearchResults().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
                    @Override
                    public void onChanged(@Nullable final List<Product> products) {

                        if (products.size() > 0) {
                            msg.setText("");
                            productId.setText(String.format(Locale.US, "%d", products.get(0).getId()));
                            productName.setText(products.get(0).getName());
                            productQuantity.setText(String.format(Locale.US, "%d", products.get(0).getQuantity()));
                            productPrice.setText(String.format(Locale.US, "%.2f", products.get(0).getPrice()));

                        } else {
                            clearFields();
                            msg.setText("No such Product!");
                        }
                    }
                });
    }
}