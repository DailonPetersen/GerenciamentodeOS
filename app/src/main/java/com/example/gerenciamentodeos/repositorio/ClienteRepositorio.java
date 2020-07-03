package com.example.gerenciamentodeos.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import com.example.gerenciamentodeos.entidades.Cliente;

import java.util.ArrayList;
import java.util.List;

/*classe para abstrair a interação com o banco de dados.
A insercao/listagem/atualizacao/delete podem ser feitos diretamente na classe respectiva
a cada activity. ex: activity_adicionar_cliente - AdicionarCliente*/
public class ClienteRepositorio {

    public SQLiteDatabase conexao;

    public ClienteRepositorio(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }

    public void inserirCliente(Cliente cliente){

        ContentValues conteudo = new ContentValues();
        conteudo.put("nome_cliente", String.valueOf(cliente.nome_do_cliente));
        conteudo.put("email", String.valueOf(cliente.email));
        conteudo.put("telefone", String.valueOf(cliente.telefone));
        conteudo.put("placa_carro", String.valueOf(cliente.placa_carro));

        conexao.insertOrThrow("cliente", null, conteudo);
    }

    public void excluirCliente(Integer id_cliente){

        //array de parametros que sao utilizados na query, neste caso só um.
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id_cliente);

        conexao.delete("cliente","id_cliente = ?", parametros);
    }

    public void alterarCliente( Cliente cliente ){
        ContentValues conteudo = new ContentValues();
        conteudo.put("nome_cliente", String.valueOf(cliente.nome_do_cliente));
        conteudo.put("email", String.valueOf(cliente.email));
        conteudo.put("telefone", String.valueOf(cliente.telefone));
        conteudo.put("placa_carro", String.valueOf(cliente.placa_carro));

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(cliente.id_cliente);

        conexao.update("cliente", conteudo, "id_cliente = ?", parametros);
    }

    public List<Cliente> buscarClientes(){

        List<Cliente> ListaClientes = new ArrayList<Cliente>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id_cliente, nome_cliente, email, telefone, id_cliente, placa_carro FROM cliente");

        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if ( resultado.getCount() > 0 ) {

            resultado.moveToFirst();

            do {
                Cliente cliente = new Cliente();

                cliente.id_cliente = resultado.getInt( resultado.getColumnIndex("id_cliente") );
                cliente.nome_do_cliente = resultado.getString( resultado.getColumnIndex("nome_cliente") );
                cliente.email = resultado.getString( resultado.getColumnIndex("email") );
                cliente.telefone = resultado.getString( resultado.getColumnIndex("telefone") );
                cliente.placa_carro = resultado.getString( resultado.getColumnIndex("placa_carro") );

                ListaClientes.add(cliente);

            //enquanto ele ainda tiver um próximo
            } while (resultado.moveToNext());

        }

        return ListaClientes;

    }

    public Cliente buscarClientePorNome( String nome_cliente ){

        Cliente cliente = new Cliente();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT id_cliente, nome_cliente, email, telefone, id_cliente, placa_carro ");
        sql.append(" FROM cliente");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(nome_cliente);

        //rawQuery retorna um Cursor
        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

        if ( resultado.getCount() > 0 ) {

            resultado.moveToFirst();

            cliente.id_cliente = resultado.getInt( resultado.getColumnIndex("id_cliente") );
            cliente.nome_do_cliente = resultado.getString( resultado.getColumnIndex("nome_cliente") );
            cliente.email = resultado.getString( resultado.getColumnIndex("email") );
            cliente.telefone = resultado.getString( resultado.getColumnIndex("telefone") );
            cliente.placa_carro = resultado.getString( resultado.getColumnIndex("placa_carro") );

            return cliente;
        }

        return null;

    }
}
