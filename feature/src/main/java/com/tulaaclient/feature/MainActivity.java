package com.tulaaclient.feature;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tulaaclient.feature.pojo.PascalPojo;
import com.tulaaclient.feature.service.APIClient;
import com.tulaaclient.feature.service.APIInterface;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView num;
    Button get_results;
    TextView results;
    APIInterface apiInterface;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = findViewById(R.id.kthval);
        get_results = findViewById(R.id.verify);
        results = findViewById(R.id.results);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        get_results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Field is Required", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(num.getText().toString()) < 0) {
                    Toast.makeText(getApplicationContext(), "-Ve Value field not Supported", Toast.LENGTH_SHORT).show();
                } else {
                    progress = ProgressDialog.show(MainActivity.this, null, "loading...", true);
                    Call<PascalPojo> call2 = apiInterface.doGetResults(num.getText().toString());
                    call2.enqueue(new Callback<PascalPojo>() {
                        @Override
                        public void onResponse(Call<PascalPojo> call, Response<PascalPojo> response) {
                            progress.dismiss();
                            PascalPojo userList = response.body();
                            results.setText(Arrays.asList(userList.getData()).toString());

                        }

                        @Override
                        public void onFailure(Call<PascalPojo> call, Throwable t) {
                            call.cancel();
                            results.setText(t.getMessage());
                            progress.dismiss();
                        }
                    });
                }

            }
        });
    }
}
