package com.example.gerenciamentodeos.entidades;

import android.widget.EditText;

import java.io.Serializable;

public class OrdemServico implements Serializable {

    public String nome_do_cliente;
    public String placa_do_carro;
    public String ordem_de_servico;
    public String modelo_carro;
    public String data;
    public String descricao;

    public  OrdemServico() {

        ordem_de_servico = "";
    }
}
