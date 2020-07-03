package com.example.gerenciamentodeos.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gerenciamentodeos.entidades.Cliente;
import com.example.gerenciamentodeos.entidades.OrdemServico;

import java.util.ArrayList;
import java.util.List;

public class OrdemServicoRepositorio {

    public SQLiteDatabase conexao;

    public OrdemServicoRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void inserirOrdem(OrdemServico os){

        ContentValues conteudo = new ContentValues();
        conteudo.put("nome_cliente", String.valueOf(os.nome_do_cliente));
        conteudo.put("os", String.valueOf(os.ordem_de_servico));
        conteudo.put("modelo", String.valueOf(os.modelo_carro));
        conteudo.put("descricao", String.valueOf(os.descricao));
        conteudo.put("placa_carro", String.valueOf(os.placa_do_carro));

        conexao.insertOrThrow("ordem_de_servico", null, conteudo);
    }

    public List<OrdemServico> listaOrdens() {

        List<OrdemServico> OrdensServico = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT nome_cliente, os, modelo, placa_carro, data, descricao " );
        sql.append("FROM ordem_de_servico");

        Cursor retorno = conexao.rawQuery(sql.toString(), null);

        if( retorno.getCount() > 0) {
            retorno.moveToFirst();

            do {
                OrdemServico os = new OrdemServico();

                os.nome_do_cliente = retorno.getString(retorno.getColumnIndex("nome_cliente"));
                os.data = retorno.getString(retorno.getColumnIndex("data"));
                os.ordem_de_servico = retorno.getString(retorno.getColumnIndex("os"));
                os.modelo_carro = retorno.getString(retorno.getColumnIndex("modelo"));
                os.placa_do_carro = retorno.getString(retorno.getColumnIndex("placa_carro"));
                os.descricao = retorno.getString(retorno.getColumnIndex("descricao"));

                OrdensServico.add(os);

            } while (retorno.moveToNext());

        }

        return OrdensServico;
    }

    public void excluirOrdem(String os){
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(os);

        conexao.delete("ordem_de_servico","os = ?", parametros);
    }

    public void alterarOrdem( OrdemServico os ){
        ContentValues conteudo = new ContentValues();
        conteudo.put("nome_cliente", String.valueOf(os.nome_do_cliente));
        conteudo.put("descricao", String.valueOf(os.descricao));

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(os.ordem_de_servico);

        conexao.update("ordem_de_servico", conteudo, "os = ?", parametros);
    }

    public OrdemServico buscaOrdem(String os){

        OrdemServico ordemServico = new OrdemServico();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT nome_cliente, os, data, modelo, placa_carro, descricao ");
        sql.append(" FROM ordem_de_servico");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(os);

        //rawQuery retorna um Cursor
        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

        if ( resultado.getCount() > 0 ) {

            resultado.moveToFirst();

            ordemServico.ordem_de_servico = resultado.getString( resultado.getColumnIndex("os") );
            ordemServico.nome_do_cliente = resultado.getString( resultado.getColumnIndex("nome_cliente") );
            ordemServico.data = resultado.getString( resultado.getColumnIndex("data") );
            ordemServico.modelo_carro = resultado.getString( resultado.getColumnIndex("modelo") );
            ordemServico.placa_do_carro = resultado.getString( resultado.getColumnIndex("placa_carro") );
            ordemServico.descricao = resultado.getString(resultado.getColumnIndex("descricao"));

            return ordemServico;
        }

        return null;
    }
}
