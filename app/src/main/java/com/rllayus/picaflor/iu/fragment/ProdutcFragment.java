package com.rllayus.picaflor.iu.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
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
public class ProdutcFragment extends android.support.v4.app.Fragment {
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProdutcFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProdutcFragment newInstance(String param1, String param2) {
        ProdutcFragment fragment = new ProdutcFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_produtc, container, false);
        if(savedInstanceState!=null){
            items=(ArrayList)savedInstanceState.getSerializable(BundleKey.KEY_LIST_EMPRESA);
            textToSearch=(String)savedInstanceState.getString(BundleKey.KEY_TEXT_TO_SEARCH);
        }else{
            items=new ArrayList<>();
            textToSearch="";
        }
        progressDialog=new ProgressDialog(getActivity());
        mDataService=new DataService();
        recyclerView=(RecyclerView)view.findViewById(R.id.reciclador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productAdapter=new ProductAdapter(cargarProductos(),getActivity());
        recyclerView.setAdapter(productAdapter);
        recyclerView.setHasFixedSize(true);
        loadProducto();
        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        outState.putSerializable(BundleKey.KEY_LIST_PRODUCTO,(ArrayList)items);
        outState.putString(BundleKey.KEY_TEXT_TO_SEARCH, textToSearch);
        super.onSaveInstanceState(outState);
    }
    public List<ProductItem> cargarProductos(){
        List<ProductItem> auxList=new ArrayList<>();
        ProductItem producto;
        for (int i=0;i<100;i++){
            producto=new ProductItem();
            producto.setNombre("Coca cola ");
            producto.setBarcode("32423432423423423423");
            producto.setUrilogo( "http://jhjhj/jkhjh/jkhjk");
            producto.setDescripcion("caskjasdas;dkasdasjdasjhasdas;dalsd asdkasld asd asdasdl;asdkasl;dkasld asdjashdasdasndlasdklasjdasdasd,a'dasdkasdnasdnjashduadhwqro[weriwerwekrlnwefkcnslkcnskd");
            auxList.add(producto);
        }
        return auxList;
    }
    public void loadProducto(){
        progressDialog.setTitle("Espere ...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        mDataService.listarProducto(new Callback<ObjetResponse<ProductItem>>() {
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
