package com.example.gerenciamentodeos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.gerenciamentodeos.bancodedados.DBhelper;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutMain;

    //troca de activity
    public void listagem_ordens(View v){
        Intent intent = new Intent(this, ListagemOrdens.class);
        startActivity(intent);
    }

    public void listagem_clientes(View v){
        Intent intent = new Intent(this, ListagemClientes.class);
        startActivity(intent);
    }

    //troca de activity
    public void adicionar_ordens(View v){
        Intent intent = new Intent(this, AdicionarOrdens.class);
        startActivity(intent);
    }

    public void adicionar_cliente(View v){
        Intent intent = new Intent(this, AdicionarCliente.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutMain = (LinearLayout)findViewById(R.id.main_layout);
    }

}
