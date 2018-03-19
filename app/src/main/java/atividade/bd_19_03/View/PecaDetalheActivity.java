package atividade.bd_19_03.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.Peca;
import atividade.bd_19_03.R;
import io.realm.Realm;

public class PecaDetalheActivity extends AppCompatActivity {
    EditText nome, descricao;
    Button btsalvar, btalterar, btdeletar;
    int id;
    Peca peca;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peca_detalhe);

        nome = (EditText) findViewById(R.id.ed_nome_peca);
        descricao = (EditText) findViewById(R.id.ed_descricao_peca);

        btsalvar = (Button) findViewById(R.id.bt_salvar_peca);
        btalterar = (Button) findViewById(R.id.bt_alterar_peca);
        btdeletar = (Button) findViewById(R.id.bt_deletar_peca);

        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if (id != 0) {
            btsalvar.setEnabled(false);
            btsalvar.setClickable(false);
            btsalvar.setVisibility(View.INVISIBLE);

            peca = realm.where(Peca.class).equalTo("id", id).findFirst();

            nome.setText(peca.getNome());
            descricao.setText(peca.getDescricao());

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
        peca.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Peça deletada!", Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void salvar() {


        int proximoID = 1;
        if (realm.where(Peca.class).max("id") != null)
            proximoID = realm.where(Peca.class).max("id").intValue() + 1;

        realm.beginTransaction();
        Peca peca = new Peca();
        peca.setId(proximoID);
        peca.setNome(nome.getText().toString());
        peca.setDescricao(descricao.getText().toString());

        realm.copyToRealm(peca);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Peça Cadastrada!", Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void alterar() {

        realm.beginTransaction();

        peca.setNome(nome.getText().toString());
        peca.setDescricao(descricao.getText().toString());

        realm.copyToRealm(peca);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Peça Alterada!", Toast.LENGTH_LONG).show();
        this.finish();

    }
}
