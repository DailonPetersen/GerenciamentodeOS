package com.example.gerenciamentodeos;


import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gerenciamentodeos.adapters.ClienteAdapter;
import com.example.gerenciamentodeos.bancodedados.DBhelper;
import com.example.gerenciamentodeos.entidades.Cliente;
import com.example.gerenciamentodeos.repositorio.ClienteRepositorio;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ListagemClientes extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private DBhelper dbHelper;
    private LinearLayout layoutListCliente;
    private RecyclerView recyclerView;
    private ClienteAdapter clienteAdapter;

    private Cliente cliente;
    private ClienteRepositorio clienteRepositorio;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_clientes);

        //class RecyclerView recebe o componente de layout recyclerview
        recyclerView = (RecyclerView)findViewById(R.id.lista_clientes);

        //recebe o layout parent
        layoutListCliente = (LinearLayout) findViewById(R.id.layout_listagem_cliente);

        recyclerView.setHasFixedSize(true);
        conectaDB();
        LinearLayoutManager llManager = new LinearLayoutManager(this);
        //seta o layoutManager do recyclerview
        recyclerView.setLayoutManager(llManager);

        clienteRepositorio = new ClienteRepositorio(conexao);

    }

    public void validaDelete(Cliente clienteSeleciona){
        clienteRepositorio.excluirCliente(clienteSeleciona.id_cliente);
        atualizagrid();
    }

    private void conectaDB() {
        try {
            dbHelper = new DBhelper(this);
            conexao = dbHelper.getWritableDatabase();
            clienteRepositorio = new ClienteRepositorio(conexao);
            Snackbar.make(layoutListCliente, "Conectou com o banco de dados!", Snackbar.LENGTH_SHORT).show();

        } catch (SQLException ex) {

            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setTitle("Erro");
            msg.setMessage(ex.getMessage());
            msg.setNeutralButton("OK", null);
        }
    }

    protected void onStart() {
        super.onStart();
        atualizagrid();
    }

    public void atualizagrid(){
        List<Cliente> clienteList = clienteRepositorio.buscarClientes();
        //adapter recebe o conteudo e depois eh vinculado ao recyclerView
        clienteAdapter = new ClienteAdapter(clienteList);
        recyclerView.setAdapter(clienteAdapter);
    }
}
