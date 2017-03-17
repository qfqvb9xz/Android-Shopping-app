package com.example.lzy3qy.shopping.utilities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductAPI {
    @GET("/Search")
    Call<ProductInfo> getProductFromApi(
            @Query("term") String productName,
            @Query("key") String keyId);
}
