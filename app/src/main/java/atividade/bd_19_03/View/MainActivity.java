package atividade.bd_19_03.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import atividade.bd_19_03.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button peca = (Button) findViewById(R.id.id_peca);
        Button pro = (Button) findViewById(R.id.id_proprietario);
        Button ser = (Button) findViewById(R.id.id_servico);

        peca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PecasActivity.class);
                startActivity(intent);
            }
        });

        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProActivity.class);
                startActivity(intent);
            }
        });

        ser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SerActivity.class);
                startActivity(intent);
            }
        });

    }
    private Context getContext(){
        return this;
    }
}
