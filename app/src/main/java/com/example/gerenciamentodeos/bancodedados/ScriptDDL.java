package com.example.gerenciamentodeos.bancodedados;

public class ScriptDDL {

    public static String createTableCliente(){
        StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE IF NOT EXISTS cliente ( ");
        sql.append(" nome_cliente VARCHAR (150) NOT NULL, ");
        sql.append(" email VARCHAR (100), ");
        sql.append(" telefone VARCHAR (11)  NOT NULL, ");
        sql.append(" id_cliente INTEGER PRIMARY KEY AUTOINCREMENT,");
        sql.append(" placa_carro VARCHAR (7) UNIQUE NOT NULL )");

        return sql.toString();
    }

    public static String createTableOrdemServico(){
        StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE IF NOT EXISTS ordem_de_servico ( ");
        sql.append(" nome_cliente VARCHAR (150) NOT NULL, ");
        sql.append(" os VARCHAR(10) PRIMARY KEY NOT NULL, ");
        sql.append(" data DATE DEFAULT (CURRENT_TIMESTAMP), ");
        sql.append(" descricao TEXT NOT NULL, ");
        sql.append(" modelo VARCHAR (300) NOT NULL, ");
        sql.append(" placa_carro VARCHAR (7) UNIQUE NOT NULL )");

        return sql.toString();

    }
}
