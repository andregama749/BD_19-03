package atividade.bd_19_03.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.Servico;
import atividade.bd_19_03.R;
import io.realm.Realm;

public class SerDetalheActivity extends AppCompatActivity {
    EditText nome, horas, mec;
    Button btsalvar, btalterar, btdeletar;
    int id;
    Servico s;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ser_detalhe);

        nome = (EditText) findViewById(R.id.ed_nome_ser);
        horas = (EditText) findViewById(R.id.ed_horas);
        mec = (EditText) findViewById(R.id.ed_mec);

        btsalvar = (Button) findViewById(R.id.bt_salvar_ser);
        btalterar = (Button) findViewById(R.id.bt_alterar_ser);
        btdeletar = (Button) findViewById(R.id.bt_deletar_ser);

        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if (id != 0) {
            btsalvar.setEnabled(false);
            btsalvar.setClickable(false);
            btsalvar.setVisibility(View.INVISIBLE);

            s = realm.where(Servico.class).equalTo("id", id).findFirst();

            nome.setText(s.getNome());
            horas.setText(s.getHoras());
            mec.setText(s.getMecanico());

        } else {
            btalterar.setEnabled(false);
            btalterar.setClickable(false);
            btalterar.setVisibility(View.INVISIBLE);
            btdeletar.setEnabled(false);
            btdeletar.setClickable(false);
            btdeletar.setVisibility(View.INVISIBLE);

        }


        btsalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                salvar();
            }
        });
        btalterar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                alterar();
            }
        });
        btdeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletar();
            }
        });


    }

    public void deletar() {
        realm.beginTransaction();
        s.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Serviço deletado!", Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void salvar() {


        int proximoID = 1;
        if (realm.where(Servico.class).max("id") != null)
            proximoID = realm.where(Servico.class).max("id").intValue() + 1;

        realm.beginTransaction();
        Servico se = new Servico();
        se.setId(proximoID);
        se.setNome(nome.getText().toString());
        se.setHoras(horas.getText().toString());
        se.setMecanico(mec.getText().toString());

        realm.copyToRealm(se);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Serviço Cadastrado!", Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void alterar() {

        realm.beginTransaction();

        s.setNome(nome.getText().toString());
        s.setHoras(horas.getText().toString());
        s.setMecanico(mec.getText().toString());

        realm.copyToRealm(s);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Serviço Alterado!", Toast.LENGTH_LONG).show();
        this.finish();

    }
}
