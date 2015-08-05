package com.rllayus.picaflor.service;

import android.content.Context;

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



    /**
     * Este metodo es hibrido en el sentido de que realiza un consulta a la base de datos local y tambien
     * al servidor en busca de actualizaciones.
     * @param fecha
     * @param cb
     * @return un lista de los datos que esten almacenado en la base de datos
     */
    public void getCategories(String fecha, Callback<ObjetResponse<ProductItem>> cb){
        mIDataRestService.getProducto(fecha, cb);
    }

    public void getEmpresas(String nombreEmpresa,Callback<ObjetResponse<ProductItem>> cb){
        
    }
}
