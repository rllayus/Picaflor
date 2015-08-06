package com.rllayus.picaflor.service;

import com.rllayus.picaflor.modelo.Empresa;
import com.rllayus.picaflor.modelo.ProductItem;
import retrofit.Callback;
import retrofit.http.*;

import java.util.List;

public interface IDataRestService {

    @GET("/test")
    public String test();

    @GET("/empresa/listar")
    public void listarEmpresa(Callback<ObjetResponse<Empresa>> cb);

    @FormUrlEncoded
    @POST("/empresa/buscarEmpresas")
	public void buscarEmpresa(@Field("buscar") String buscar,Callback<ObjetResponse<Empresa>> cb);

    @FormUrlEncoded
    @POST("/producto/listar")
    public void listarProducto(@Field("idempresa")int idempresa, Callback<ObjetResponse<ProductItem>> cb);

    @FormUrlEncoded
    @POST("/producto/buscarProductos")
    public void buscarProducto(@Field("idempresa") Integer idempresa,@Field("buscar") String buscar, Callback<ObjetResponse<ProductItem>> cb);
}
