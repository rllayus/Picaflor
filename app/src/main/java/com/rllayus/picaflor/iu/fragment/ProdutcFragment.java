package com.rllayus.picaflor.iu.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rllayus.picaflor.R;
import com.rllayus.picaflor.iu.adapter.ProductAdapter;
import com.rllayus.picaflor.modelo.Empresa;
import com.rllayus.picaflor.modelo.ProductItem;
import com.rllayus.picaflor.service.DataService;
import com.rllayus.picaflor.service.ObjetResponse;
import com.rllayus.picaflor.utils.BundleKey;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProdutcFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProdutcFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProdutcFragment extends android.support.v4.app.Fragment implements SearchView.OnQueryTextListener{


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private OnFragmentInteractionListener mListener;
    private SearchView searchView;
    private ProgressDialog progressDialog;
    private String textToSearch;
    private DataService mDataService;
    private List<ProductItem> items;
    private Empresa currentEmpresa;


    // TODO: Rename and change types and number of parameters
    public static ProdutcFragment newInstance(Empresa empresa) {
        ProdutcFragment fragment = new ProdutcFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, empresa);
        fragment.setArguments(args);
        return fragment;
    }

    public ProdutcFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentEmpresa =(Empresa) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(getTag(),"onCreateView");
        if(getArguments()!=null){
            currentEmpresa=(Empresa)getArguments().getSerializable(ARG_PARAM1);
        }
        View view =inflater.inflate(R.layout.fragment_produtc, container, false);
        progressDialog=new ProgressDialog(getActivity());
        mDataService=new DataService();
        recyclerView=(RecyclerView)view.findViewById(R.id.reciclador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setHasOptionsMenu(true);
        return  view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(getTag(),"onActivityCreated");
        if(savedInstanceState!=null){
            Log.i(getTag(),"savedInstanceState not null");
            items=(ArrayList)savedInstanceState.getSerializable(BundleKey.KEY_LIST_PRODUCTO);
            textToSearch=(String)savedInstanceState.getString(BundleKey.KEY_TEXT_TO_SEARCH);
            currentEmpresa=(Empresa)savedInstanceState.getSerializable(BundleKey.KEY_EMPRESA_CURRENT);
            productAdapter=new ProductAdapter(items,getActivity());
        }else{
            items=new ArrayList<>();
            textToSearch="";
            productAdapter=new ProductAdapter(items,getActivity());
            listarProducto();
        }
        recyclerView.setAdapter(productAdapter);
        recyclerView.setHasFixedSize(false);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search_product,menu);
        MenuItem searchItem=menu.findItem(R.id.action_search);
        searchView=(SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getResources().getString(R.string.action_search_label));
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(BundleKey.KEY_LIST_PRODUCTO,(ArrayList)items);
        outState.putString(BundleKey.KEY_TEXT_TO_SEARCH, textToSearch);
        outState.putSerializable(BundleKey.KEY_EMPRESA_CURRENT,currentEmpresa);
        Log.i(getTag(),"savedInstanceState");
    }
    
    public void listarProducto(){
        progressDialog.setTitle("Espere ...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        mDataService.listarProducto(currentEmpresa.getId(),new Callback<ObjetResponse<ProductItem>>() {
            @Override
            public void success(ObjetResponse<ProductItem> productItemObjetResponse, Response response) {
                items=productItemObjetResponse.getValues();
                productAdapter.setItems(items);
                productAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                Snackbar.make(recyclerView, "Error al ejecutar la consulta", Snackbar.LENGTH_LONG).show();
            }
        });
    }
    public void buscarProducto(String buscar){
        this.textToSearch=buscar;
        progressDialog.setTitle("Espere ...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        System.out.println("idEmpresa: "+currentEmpresa.getId());
        mDataService.buscarProducto(currentEmpresa.getId(),buscar,new Callback<ObjetResponse<ProductItem>>() {
            @Override
            public void success(ObjetResponse<ProductItem> productItemObjetResponse, Response response) {
                items=productItemObjetResponse.getValues();
                productAdapter.setItems(items);
                productAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
                searchView.clearFocus();
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                Snackbar.make(recyclerView, "Error al ejecutar la consulta", Snackbar.LENGTH_LONG).show();
            }
        });
    }
    /**
     * Called when the user submits the query. This could be due to a key press on the
     * keyboard or due to pressing a submit button.
     * The listener can override the standard behavior by returning true
     * to indicate that it has handled the submit request. Otherwise return false to
     * let the SearchView handle the submission by launching any associated intent.
     *
     * @param query the query text that is to be submitted
     * @return true if the query has been handled by the listener, false to let the
     * SearchView perform the default action.
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        buscarProducto(query);
        return true;
    }

    /**
     * Called when the query text is changed by the user.
     *
     * @param newText the new content of the query text field.
     * @return false if the SearchView should perform the default action of showing any
     * suggestions if available, true if the action was handled by the listener.
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
