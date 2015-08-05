package com.rllayus.picaflor.iu.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rllayus.picaflor.R;
import com.rllayus.picaflor.iu.adapter.ProductAdapter;
import com.rllayus.picaflor.modelo.ProductItem;

import java.util.ArrayList;

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
    public ArrayList<ProductItem> getItems(){
        ProductItem productItem;
        ArrayList<ProductItem> lista=new ArrayList<>();
        for (int i=0;i<50;i++){
            productItem=new ProductItem();
            productItem.setId(i);
            productItem.setName("Producto "+i);
            productItem.setDescription("hasdadandkadpadasndalkdnakpdnadkasdnasdlkanskdnaskdas;dmakdmkadasdasbdauosdasdkadaksdjad");
            productItem.setPrice(50);
            productItem.setImage("http://1drv.ms/1hhZkD4");
            lista.add(productItem);
        }
        return lista;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_produtc, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.reciclador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productAdapter=new ProductAdapter(getItems(),getActivity());
        recyclerView.setAdapter(productAdapter);
        recyclerView.setHasFixedSize(true);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
