package com.example.gerenciamentodeos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gerenciamentodeos.bancodedados.DBhelper;
import com.example.gerenciamentodeos.entidades.Cliente;
import com.example.gerenciamentodeos.entidades.OrdemServico;
import com.example.gerenciamentodeos.repositorio.OrdemServicoRepositorio;
import com.example.gerenciamentodeos.utils.MaskEditUtil;
import com.google.android.material.snackbar.Snackbar;

public class AdicionarOrdens extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private DBhelper dbHelper;
    private LinearLayout layoutAddOrdens;

    private OrdemServicoRepositorio osRepositorio;
    private OrdemServico os;

    EditText nomeCliente;
    EditText placaCarro;
    EditText ordemServico;
    EditText modeloCarro;
    EditText descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_ordens);
        layoutAddOrdens = (LinearLayout)findViewById(R.id.layout_ordens);

        nomeCliente = findViewById(R.id.nome_cliente);
        placaCarro = findViewById(R.id.placa_carro);
        ordemServico = findViewById(R.id.ordem_servico);
        modeloCarro = findViewById(R.id.modelo_input);
        descricao= findViewById(R.id.descricaoInput);

        conectaDB();
        os = new OrdemServico();
        validaOrdemRecebida();
    }

    private void validaOrdemRecebida(){
        Bundle bundle = getIntent().getExtras();

        if( (bundle != null) && (bundle.containsKey("os_selecionada")) ){

            os = (OrdemServico) bundle.getSerializable("os_selecionada");
            nomeCliente.setText(os.nome_do_cliente);
            placaCarro.setText(os.modelo_carro);
            modeloCarro.setText(os.modelo_carro);
            descricao.setText(os.descricao);
            ordemServico.setText(os.ordem_de_servico);
        }
    }

    private void conectaDB() {
        try {
            dbHelper = new DBhelper(this);
            conexao = dbHelper.getWritableDatabase();
            osRepositorio = new OrdemServicoRepositorio(conexao);
            Snackbar.make(layoutAddOrdens, "Conectou com o banco de dados!", Snackbar.LENGTH_SHORT).show();

        } catch (SQLException ex) {
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setTitle("Erro");
            msg.setMessage(ex.getMessage());
            msg.setNeutralButton("OK", null);
        }
    }

    public void inserindoOrdem(View v){

        if (os.ordem_de_servico == "") {

            os.nome_do_cliente = nomeCliente.getText().toString();
            os.placa_do_carro = placaCarro.getText().toString();
            os.ordem_de_servico = ordemServico.getText().toString();
            os.modelo_carro = modeloCarro.getText().toString();
            os.descricao = descricao.getText().toString();

            try {
                osRepositorio.inserirOrdem(os);
                Toast.makeText(getApplicationContext(), "Inseriu", Toast.LENGTH_LONG).show();
                finish();
            } catch (SQLException ex) {
                AlertDialog.Builder msg = new AlertDialog.Builder(this);
                msg.setTitle("Erro");
                msg.setMessage(ex.getMessage());
                msg.setNeutralButton("OK", null);
            }

        } else {
            try {
                osRepositorio.alterarOrdem(os);
                Toast.makeText(getApplicationContext(), "Alterou", Toast.LENGTH_LONG).show();
                finish();
            } catch (SQLException ex) {
                AlertDialog.Builder msg = new AlertDialog.Builder(this);
                msg.setTitle("Erro");
                msg.setMessage(ex.getMessage());
                msg.setNeutralButton("OK", null);
            }
        }

        Toast t = Toast.makeText(getApplicationContext(), "Chegou no inserir", Toast.LENGTH_SHORT);
        t.show();

    }


}
