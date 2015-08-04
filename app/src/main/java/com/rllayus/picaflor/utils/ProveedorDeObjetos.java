package com.rllayus.picaflor.utils;
import android.app.Application;
public class ProveedorDeObjetos extends Application{
	public static Application Proveedor;
	public final void onCreate(){
		super.onCreate();
		Proveedor=this;
	}
	public static ProveedorDeObjetos getInstance(){
		return (ProveedorDeObjetos) Proveedor;
	}


}
