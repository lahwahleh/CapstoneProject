package com.android.evp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button switchact = (Button)findViewById(R.id.btn1);
        switchact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent act2 = new Intent(view.getContext(), CreateActivity.class);
                startActivity(act2);
            }
        });

        final Button switchact2 = (Button)findViewById(R.id.btn2);
        switchact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent act3 = new Intent(view.getContext(), CreateActivity2.class);
                startActivity(act3);
            }
        }); 
    }
}
