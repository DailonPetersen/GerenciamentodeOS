package com.example.gerenciamentodeos.entidades;

import android.text.Editable;
import android.widget.EditText;

import java.io.Serializable;

public class Cliente implements Serializable {

    public int id_cliente;
    public String nome_do_cliente;
    public String email;
    public String telefone;
    public String placa_carro;

    public  Cliente() {
        id_cliente = 0;
    }

}
