package com.rllayus.picaflor.iu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TextView;

import com.rllayus.picaflor.R;
import com.rllayus.picaflor.modelo.ProductItem;
import com.rllayus.picaflor.utils.BundleKey;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private static final String EXTRA_NAME = "ProductName";
    private Toolbar toolBar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView nombre,descripcion,precio;
    private ImageView logo;
    private ProductItem producto;
    public static void createInstance(Activity activity, ProductItem title) {
        Intent intent = getLaunchIntent(activity, title);
        activity.startActivity(intent);
    }
    public static Intent getLaunchIntent(Context context, ProductItem product) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_NAME, product.getNombre());
        intent.putExtra(BundleKey.KEY_PRODUCTO_CURRENT,product);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
           producto=(ProductItem)bundle.getSerializable(BundleKey.KEY_PRODUCTO_CURRENT);
        }
        toolBar=(Toolbar)findViewById(R.id.toolbar_emp);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        logo=(ImageView)findViewById(R.id.iv_image_paralax);
        Picasso.with(this).load(producto.getUrilogo()).into(logo);
        descripcion=(TextView)findViewById(R.id.tv_descripcion_producto);
        descripcion.setText(producto.getDescripcion());
        precio=(TextView)findViewById(R.id.tv_precio_producto);
        precio=(TextView)findViewById(R.id.tv_precio_producto);
        precio.setText(producto.getPrecio()+"$");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            return rootView;
        }
    }
}
