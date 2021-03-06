package com.ebookfrenzy.stockalertsystem;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
@Dao

public interface ProductDao {

    @Insert
    void insertProduct(Product product);

    @Query("SELECT * FROM products WHERE productName = :name")
    List<Product> findProduct(String name);

    @Query("DELETE FROM products WHERE productName = :name")
    void deleteProduct(String name);

    @Query("SELECT * FROM products")
    LiveData<List<Product>> getAllProducts();

    @Query("UPDATE products SET productName=:name, productQuantity=:quantity, productPrice=:price WHERE productId=:id")
    void updateProduct(String name, int quantity, Double price, int id);
}
