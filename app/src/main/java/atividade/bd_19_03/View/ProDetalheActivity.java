package atividade.bd_19_03.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.Proprietario;
import atividade.bd_19_03.R;
import io.realm.Realm;

public class ProDetalheActivity extends AppCompatActivity {
    EditText nome, end;
    Button btsalvar, btalterar, btdeletar;
    int id;
    Proprietario p;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_detalhe);

        nome = (EditText) findViewById(R.id.ed_nome_pro);
        end = (EditText) findViewById(R.id.ed_end);

        btsalvar = (Button) findViewById(R.id.bt_salvar_pro);
        btalterar = (Button) findViewById(R.id.bt_alterar_pro);
        btdeletar = (Button) findViewById(R.id.bt_deletar_pro);

        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if (id != 0) {
            btsalvar.setEnabled(false);
            btsalvar.setClickable(false);
            btsalvar.setVisibility(View.INVISIBLE);

            p = realm.where(Proprietario.class).equalTo("id", id).findFirst();

            nome.setText(p.getNome());
            end.setText(p.getEndereco());

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
        p.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Proprietario deletado!", Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void salvar() {


        int proximoID = 1;
        if (realm.where(Proprietario.class).max("id") != null)
            proximoID = realm.where(Proprietario.class).max("id").intValue() + 1;

        realm.beginTransaction();
        Proprietario pr = new Proprietario();
        pr.setId(proximoID);
        pr.setNome(nome.getText().toString());
        pr.setEndereco(end.getText().toString());

        realm.copyToRealm(pr);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Proprietario Cadastrado!", Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void alterar() {

        realm.beginTransaction();

        p.setNome(nome.getText().toString());
        p.setEndereco(end.getText().toString());

        realm.copyToRealm(p);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Proprietario Alterado!", Toast.LENGTH_LONG).show();
        this.finish();

    }
}
