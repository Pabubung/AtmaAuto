package p3l.atmaauto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mName;
    private EditText mPassword;
    private Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        setAtribut();
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRegister();
            }
        });
    }
    private void setAtribut(){
        mUsername=findViewById(R.id.usernametxt);
        mName=findViewById(R.id.nametxt);
        mPassword=findViewById(R.id.passwordtxt);
        mRegisterButton=findViewById(R.id.registerbtn);
    }

    private void startIntent(){
        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
    }
    private void onClickRegister(){
        if (mUsername.getText().toString().isEmpty() ||
        mPassword.getText().toString().isEmpty()){
            Toast.makeText(this,"Tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else {
            Retrofit.Builder builder=new Retrofit
                    .Builder()
                    .baseUrl("https://atmaauto.b-laundry.com/api/users")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit=builder.build();
            ApiClient apiClient=retrofit.create(ApiClient.class);
            Call<String> userDaoCall=apiClient.regUser(mUsername.getText().toString(),
                    mName.getText().toString(),
                    mPassword.getText().toString());
            userDaoCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(SignActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    startIntent();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(SignActivity.this,"Gagal",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
