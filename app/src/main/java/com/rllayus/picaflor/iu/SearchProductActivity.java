package com.rllayus.picaflor.iu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.rllayus.picaflor.R;
import com.rllayus.picaflor.iu.fragment.ProdutcFragment;
import com.rllayus.picaflor.modelo.Empresa;
import com.rllayus.picaflor.modelo.ProductItem;
import com.rllayus.picaflor.utils.BundleKey;

public class SearchProductActivity extends AppCompatActivity implements ProdutcFragment.OnFragmentInteractionListener{
    private static final String EXTRA_NAME = "ProductName";
    private Empresa currentEmpresa;
    private ProdutcFragment productFragment;
    public static void createInstance(Activity activity, Empresa title) {
        Intent intent = getLaunchIntent(activity, title);
        activity.startActivity(intent);
    }
    public static Intent getLaunchIntent(Context context, Empresa empresa) {
        Intent intent = new Intent(context, SearchProductActivity.class);
        intent.putExtra(EXTRA_NAME, empresa.getNombre());
        intent.putExtra(BundleKey.KEY_EMPRESA_CURRENT,empresa);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        if(getIntent().getExtras()!=null){
            currentEmpresa=(Empresa)getIntent().getExtras().getSerializable(BundleKey.KEY_EMPRESA_CURRENT);
        }
        setUpToolbar();
        if (savedInstanceState == null) {
            productFragment=ProdutcFragment.newInstance(currentEmpresa);
            getSupportFragmentManager().beginTransaction().replace(R.id.container,productFragment,"Fragment" )
                    .commit();
        }else
        productFragment=(ProdutcFragment)getSupportFragmentManager().findFragmentByTag("Fragment");
    }

    /**
     * Save all appropriate fragment state.
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    private void setUpToolbar() {
        // Añadir la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
