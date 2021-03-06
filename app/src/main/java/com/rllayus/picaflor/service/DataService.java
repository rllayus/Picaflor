package com.rllayus.picaflor.service;

import android.content.Context;

import com.rllayus.picaflor.modelo.Empresa;
import com.rllayus.picaflor.modelo.ProductItem;

import retrofit.Callback;

public class DataService {

    public static final int RESOURCE_LOCAL =0;
    public static final int REST_SERVICE =1;

    private IDataRestService mIDataRestService;
    private Integer dataSource;

    public DataService(Context context) {
        mIDataRestService= RestServiceConnector.getDataRestService();

    }

    public DataService() {
        mIDataRestService= RestServiceConnector.getDataRestService();
    }
    public void buscarEmpresas(String nombreEmpresa, Callback<ObjetResponse<Empresa>> cb){
        mIDataRestService.buscarEmpresa(nombreEmpresa,cb);
    }
    public void listarProducto(int idEmpresa,Callback<ObjetResponse<ProductItem>> cb){
        mIDataRestService.listarProducto(idEmpresa,cb);
    }
    public void listarEmpresa(Callback<ObjetResponse<Empresa>> cb){
        mIDataRestService.listarEmpresa(cb);
    }
    public void buscarProducto(int idEmpresa,String buscar,Callback<ObjetResponse<ProductItem>> cb){
        mIDataRestService.buscarProducto(idEmpresa,buscar,cb);
    }
}