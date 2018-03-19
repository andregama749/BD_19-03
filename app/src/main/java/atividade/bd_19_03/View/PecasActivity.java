package atividade.bd_19_03.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import Adapter.ClickRecyclerViewListener;
import Adapter.PecaAdapter;
import Model.Peca;
import atividade.bd_19_03.R;
import io.realm.Realm;

public class PecasActivity extends AppCompatActivity implements ClickRecyclerViewListener {
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pecas);


        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PecasActivity.this,PecaDetalheActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PecasActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<Peca> getPecas() {
        return (List)realm.where(Peca.class).findAll();

    }

    @Override
    public void onClick(Object object) {
        Peca peca = (Peca) object;
        Intent intent = new Intent(PecasActivity.this,PecaDetalheActivity.class);
        intent.putExtra("id",peca.getId());
        startActivity(intent);

    }

    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_pecas);

        recyclerView.setAdapter(new PecaAdapter(getPecas(),this,this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);


    }

    @Override
    public void finish(){
        realm.close();

    }
}
