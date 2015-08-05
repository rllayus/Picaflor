package com.rllayus.picaflor.service;

import com.rllayus.picaflor.modelo.Empresa;
import com.rllayus.picaflor.modelo.ProductItem;
import retrofit.Callback;
import retrofit.http.*;

import java.util.List;

public interface IDataRestService {

    @GET("/test")
    public String test();
    @FormUrlEncoded
    @POST("/empresa/buscarEmpresas")
	public void buscarEmpresa(@Field("buscar") String buscar,Callback<ObjetResponse<Empresa>> cb);
    @FormUrlEncoded
    @POST("/producto/listarProductos")
    public void listarProducto(Callback<ObjetResponse<Empresa>> cb);
}
