package p3l.atmaauto;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import p3l.atmaauto.Controller.Supplier;
import p3l.atmaauto.Controller.SupplierList;

public class RecycleAdapterSupplier extends RecyclerView.Adapter<RecycleAdapterSupplier.MyViewHolder> {

    private Context context;

    private List<Supplier> result;




    public RecycleAdapterSupplier(Context context,List<Supplier> result){
        this.context=context;
        this.result=result;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycle_adapter_supplier, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i){
        Supplier supplier = result.get(i);
        myViewHolder.mID.setText(supplier.getIdSupplier());
        myViewHolder.mNama.setText(supplier.getSupplierName());
        myViewHolder.mAlamat.setText(supplier.getSupplierAddress());
        myViewHolder.mNomorTelepon.setText(supplier.getSupplierPhoneNumber());
    }

    @Override
    public int getItemCount(){
        return result.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mNama;
        private TextView mID;
        private TextView mAlamat;
        private TextView mNomorTelepon;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

        }


        @Override
        public  void onClick(View view){
            Toast.makeText(context, "Hey, you clicked on me", Toast.LENGTH_SHORT).show();
        }
    }

}
