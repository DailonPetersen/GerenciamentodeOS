package com.example.gerenciamentodeos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.gerenciamentodeos.adapters.OrdemAdapter;
import com.example.gerenciamentodeos.bancodedados.DBhelper;
import com.example.gerenciamentodeos.entidades.Cliente;
import com.example.gerenciamentodeos.entidades.OrdemServico;
import com.example.gerenciamentodeos.repositorio.OrdemServicoRepositorio;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.List;

public class ListagemOrdens extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private DBhelper dbHelper;
    private LinearLayout layoutListOrdens;
    private RecyclerView recyclerView;
    private OrdemAdapter ordemAdapter;

    private OrdemServico os;
    private OrdemServicoRepositorio osRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_ordens);

        recyclerView = (RecyclerView)findViewById(R.id.lista_ordens);
        layoutListOrdens = (LinearLayout)findViewById(R.id.layout_listagem_ordens);
        recyclerView.setHasFixedSize(true);

        conectaDB();
        LinearLayoutManager llManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llManager);
        osRepositorio = new OrdemServicoRepositorio(conexao);

    }

    private void conectaDB() {
        try {
            dbHelper = new DBhelper(this);
            conexao = dbHelper.getWritableDatabase();
            osRepositorio = new OrdemServicoRepositorio(conexao);
            Snackbar.make(layoutListOrdens, "Conectou com o banco de dados!", Snackbar.LENGTH_SHORT).show();

        } catch (SQLException ex) {

            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setTitle("Erro");
            msg.setMessage(ex.getMessage());
            msg.setNeutralButton("OK", null);
        }
    }

    public void validaDelete(OrdemServico ordemSelecionada){
        osRepositorio.excluirOrdem(ordemSelecionada.ordem_de_servico);
        atualizagrid();
    }

    @Override
    protected void onStart() {
        super.onStart();
        atualizagrid();
    }


    public void atualizagrid(){
        List<OrdemServico> osList = osRepositorio.listaOrdens();
        ordemAdapter = new OrdemAdapter(osList);
        recyclerView.setAdapter(ordemAdapter);
    }

}
