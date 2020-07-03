package com.example.gerenciamentodeos;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gerenciamentodeos.bancodedados.DBhelper;
import com.example.gerenciamentodeos.entidades.Cliente;
import com.example.gerenciamentodeos.repositorio.ClienteRepositorio;
import com.example.gerenciamentodeos.utils.MaskEditUtil;
import com.google.android.material.snackbar.Snackbar;

public class AdicionarCliente extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private DBhelper dbHelper;
    private LinearLayout layoutAddCliente;

    private ClienteRepositorio cRepositorio;
    private Cliente cliente;

    EditText nomeCliente;
    EditText placaCarro;
    EditText telefone;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_cliente);

        layoutAddCliente = (LinearLayout)findViewById(R.id.layout_clientes);

        nomeCliente = (EditText)findViewById(R.id.nome_cliente);
        placaCarro = findViewById(R.id.placa_carro);
        telefone = findViewById(R.id.telefone);
        email = findViewById(R.id.email);

        //connecta com o banco e exibe Snackbar
        conectaDB();
        cliente = new Cliente();
        validaClienteRecebido();

        //adiciona mascara aos campos
        telefone.addTextChangedListener(MaskEditUtil.mask(telefone, MaskEditUtil.FORMAT_FONE));
    }

    private void validaClienteRecebido(){

        Bundle bundle = getIntent().getExtras();

        if( (bundle != null) && (bundle.containsKey("nome_cliente_selecionado")) ){

            cliente = (Cliente)bundle.getSerializable("nome_cliente_selecionado");
            nomeCliente.setText(cliente.nome_do_cliente);
            placaCarro.setText(cliente.placa_carro);
            telefone.setText(cliente.telefone);
            email.setText(cliente.email);

        }
    }

    private void conectaDB() {
        try {
            dbHelper = new DBhelper(this);
            conexao = dbHelper.getWritableDatabase();
            cRepositorio = new ClienteRepositorio(conexao);
            Snackbar.make(layoutAddCliente, "Conectou com o banco de dados!", Snackbar.LENGTH_SHORT).show();

        } catch (SQLException ex) {

            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setTitle("Erro");
            msg.setMessage(ex.getMessage());
            msg.setNeutralButton("OK", null);
        }
    }

    public void inserindoCliente(View v){
        //instancia de base de dados

        if (cliente.id_cliente == 0){
            cliente.nome_do_cliente = nomeCliente.getText().toString();
            cliente.placa_carro = placaCarro.getText().toString();
            cliente.telefone = telefone.getText().toString();
            cliente.email = email.getText().toString();
            try {
                cRepositorio.inserirCliente(cliente);
                Toast.makeText(getApplicationContext(), "Inseriu", Toast.LENGTH_LONG).show();
                finish();
            } catch (SQLException ex) {
                AlertDialog.Builder msg = new AlertDialog.Builder(this);
                msg.setTitle("Erro");
                msg.setMessage(ex.getMessage());
                msg.setNeutralButton("OK", null);
            }

        } else {
            cliente.nome_do_cliente = nomeCliente.getText().toString();
            cliente.placa_carro = placaCarro.getText().toString();
            cliente.telefone = telefone.getText().toString();
            cliente.email = email.getText().toString();
            try {
                cRepositorio.alterarCliente(cliente);
                Toast.makeText(getApplicationContext(), "Alterou", Toast.LENGTH_LONG).show();
                finish();
            } catch (SQLException ex) {
                AlertDialog.Builder msg = new AlertDialog.Builder(this);
                msg.setTitle("Erro");
                msg.setMessage(ex.getMessage());
                msg.setNeutralButton("OK", null);
            }
        }






    }

}
