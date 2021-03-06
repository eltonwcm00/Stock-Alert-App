package com.ebookfrenzy.stockalertsystem.ui.main;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.ebookfrenzy.stockalertsystem.Product;
import com.ebookfrenzy.stockalertsystem.ProductRepository;
import java.util.List;

//import androidx.lifecycle.ViewModel;

public class MainViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private ProductRepository repository;
    private LiveData<List<Product>> allProducts;
    private MutableLiveData<List<Product>> searchResults;
    public MainViewModel (Application application) {
        super(application);
        repository = new ProductRepository(application);
        allProducts = repository.getAllProducts();
        searchResults = repository.getSearchResults();
    }

    MutableLiveData<List<Product>> getSearchResults() {
        return searchResults;
    }
    LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    public void insertProduct(Product product) {
        repository.insertProduct(product);
    }
    public void findProduct(String name) {
        repository.findProduct(name);
    }
    public void deleteProduct(String name) {
        repository.deleteProduct(name);
    }
    public void updateProduct(String name, int quantity, Double price, int id) {
        repository.updateProduct(name, quantity, price, id);
    }

}