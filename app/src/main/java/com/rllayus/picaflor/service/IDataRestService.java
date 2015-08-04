package com.rllayus.picaflor.service;

import com.rllayus.picaflor.modelo.ProductItem;
import retrofit.Callback;
import retrofit.http.*;

import java.util.List;

public interface IDataRestService {

    @GET("/test")
    public String test();

    @FormUrlEncoded
    @POST("/categories")
    public void getProducto(@Field("fecha") String fecha, Callback<ObjetResponse<ProductItem>> cb);

	
}
