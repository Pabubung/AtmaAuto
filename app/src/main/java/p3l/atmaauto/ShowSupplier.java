package p3l.atmaauto;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import p3l.atmaauto.Controller.ApiClient;
import p3l.atmaauto.Controller.Supplier;
import p3l.atmaauto.Controller.SupplierList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowSupplier extends AppCompatActivity {

    private SupplierList supplierList;
    private List<Supplier> mListSupplier=new ArrayList<>();
    private RecyclerView recyclerView;
    public RecycleAdapterSupplier recycleAdapterSupplier;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_supplier);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recycleAdapterSupplier=new RecycleAdapterSupplier(this,mListSupplier);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycleAdapterSupplier);
        setRecycleView();

    }

    public void setRecycleView(){
        //mListStudent.clear();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://atmaauto.b-laundry.com/api")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ApiClient apiClient = retrofit.create(ApiClient.class);
        Call<SupplierList> supplierCall = apiClient.getSuppliers();

        supplierCall.enqueue(new Callback<SupplierList>() {
            @Override
            public void onResponse(Call<SupplierList> call, Response<SupplierList> response) {
                recycleAdapterSupplier.notifyDataSetChanged();
                recycleAdapterSupplier = new RecycleAdapterSupplier(ShowSupplier.this,response.body().getData());
                recyclerView.setAdapter(recycleAdapterSupplier);
                Toast.makeText(ShowSupplier.this, "Welcome", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SupplierList> call, Throwable t) {
                Toast.makeText(ShowSupplier.this, "Network Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
