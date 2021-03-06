package com.rllayus.picaflor.iu;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.support.v4.widget.SearchViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rllayus.picaflor.R;
import com.rllayus.picaflor.iu.adapter.EmpresaAdapter;
import com.rllayus.picaflor.modelo.Empresa;
import com.rllayus.picaflor.service.DataService;
import com.rllayus.picaflor.service.ObjetResponse;
import com.rllayus.picaflor.utils.BundleKey;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SearchEmpresaActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private RecyclerView recycler;
    private Toolbar toolbar;
    private EmpresaAdapter empresaAdapter;
    private DataService mDataService;
    private List<Empresa> items;
    private SearchView searchView;
    private ProgressDialog progressDialog;
    private String textToSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_empresa);
        mDataService=new DataService(this);
        progressDialog=new ProgressDialog(this);
        recycler=(RecyclerView)findViewById(R.id.reciclador_emp);
        toolbar=(Toolbar)findViewById(R.id.toolbar_emp);
        recycler.setHasFixedSize(false);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(toolbar);
        if(savedInstanceState!=null){
            items=(ArrayList)savedInstanceState.getSerializable(BundleKey.KEY_LIST_EMPRESA);
            textToSearch=(String)savedInstanceState.getString(BundleKey.KEY_TEXT_TO_SEARCH);
            empresaAdapter=new EmpresaAdapter(items,this);
        }else{
            items=new ArrayList<>();
            textToSearch="";
            empresaAdapter=new EmpresaAdapter(items,this);
            listarEmpresa();
        }
        recycler.setAdapter(empresaAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_empresa, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getResources().getText(R.string.action_search_label));
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    /*
     * By RolyNet
     */
    private void buscarEmpresa(String textToSearch){
        this.textToSearch=textToSearch;
        progressDialog.setTitle("Espere ...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        mDataService.buscarEmpresas(textToSearch, new Callback<ObjetResponse<Empresa>>() {
            @Override
            public void success(ObjetResponse<Empresa> empresaObjetResponse, Response response) {
                items = empresaObjetResponse.getValues();
                empresaAdapter.setItems(items);
                empresaAdapter.notifyDataSetChanged();
                searchView.clearFocus();
                progressDialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                Snackbar.make(recycler, "Error al ejecutar la consulta", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    private void listarEmpresa(){
        progressDialog.setTitle("Espere ...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        mDataService.listarEmpresa(new Callback<ObjetResponse<Empresa>>() {
            @Override
            public void success(ObjetResponse<Empresa> empresaObjetResponse, Response response) {
                items=empresaObjetResponse.getValues();
                empresaAdapter.setItems(items);
                empresaAdapter.notifyDataSetChanged();
                searchView.clearFocus();
                progressDialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                Snackbar.make(recycler,"Error al ejecutar la consulta",Snackbar.LENGTH_LONG).show();
            }
        });
    }

    /**
     * By RolyNet
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        buscarEmpresa(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    /**
     * Save all appropriate fragment state.
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(BundleKey.KEY_TEXT_TO_SEARCH,textToSearch);
        outState.putSerializable(BundleKey.KEY_LIST_EMPRESA,(ArrayList)items);
        super.onSaveInstanceState(outState);
    }
}
