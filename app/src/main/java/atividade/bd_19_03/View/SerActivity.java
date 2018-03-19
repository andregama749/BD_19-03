package atividade.bd_19_03.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import Adapter.ClickRecyclerViewListener;
import Adapter.ServicoAdapter;
import Model.Servico;
import atividade.bd_19_03.R;
import io.realm.Realm;

public class SerActivity extends AppCompatActivity implements ClickRecyclerViewListener {
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ser);


        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab5);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SerActivity.this,SerDetalheActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab6);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<Servico> getSer() {
        return (List)realm.where(Servico.class).findAll();

    }

    @Override
    public void onClick(Object object) {
        Servico s = (Servico) object;
        Intent intent = new Intent(SerActivity.this,SerDetalheActivity.class);
        intent.putExtra("id",s.getId());
        startActivity(intent);

    }

    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_ser);

        recyclerView.setAdapter(new ServicoAdapter(getSer(),this,this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);


    }

    @Override
    public void finish(){
        realm.close();

    }
}
