package com.example.gerenciamentodeos.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gerenciamentodeos.AdicionarCliente;
import com.example.gerenciamentodeos.ListagemClientes;
import com.example.gerenciamentodeos.ListagemOrdens;
import com.example.gerenciamentodeos.R;
import com.example.gerenciamentodeos.entidades.Cliente;

import java.io.Serializable;
import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolderCliente> {

    List<Cliente> clienteList;

    public ClienteAdapter(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @NonNull
    @Override
    public ClienteAdapter.ViewHolderCliente onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        //"infla" uma view, dentro a view parent q estarei passando
        View view = layoutInflater.inflate(R.layout.viewholder_clientes, parent, false);

        //necesario retornar um ViewHolderCliente
        ViewHolderCliente holderCliente = new ViewHolderCliente(view, parent.getContext());

        return holderCliente;
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteAdapter.ViewHolderCliente holder, int position) {

        if ( (clienteList != null) && (clienteList.size() > 0) ){
            Cliente cliente = clienteList.get(position);
            holder.nome_cliente.setText(cliente.nome_do_cliente);
            holder.email.setText(cliente.email);
            holder.telefone.setText(cliente.telefone);
            holder.placa_carro.setText(cliente.placa_carro);
        }
    }

    @Override
    public int getItemCount() {
        return clienteList.size();
    }

    public class ViewHolderCliente extends RecyclerView.ViewHolder {

        public TextView nome_cliente;
        public TextView email;
        public TextView telefone;
        public TextView placa_carro;

        public ViewHolderCliente(@NonNull View itemView, final Context context) {
            super(itemView);

            nome_cliente = (TextView)itemView.findViewById(R.id.nome_cliente);
            email = (TextView)itemView.findViewById(R.id.email);
            telefone = (TextView)itemView.findViewById(R.id.telefone);
            placa_carro = (TextView)itemView.findViewById(R.id.placa_carro);
            ImageButton deleteBtn = itemView.findViewById(R.id.deleteCliente);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if ( clienteList.size() > 0 ){

                        Cliente clienteSelecionado = clienteList.get(getLayoutPosition());

                        Intent intent = new Intent(context, AdicionarCliente.class);
                        intent.putExtra("nome_cliente_selecionado", clienteSelecionado);

                        context.startActivity(intent);
                    }

                }

            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ListagemClientes listagemClientes = (ListagemClientes)context;

                    Cliente clienteSelecionado = clienteList.get(getLayoutPosition());

                    listagemClientes.validaDelete(clienteSelecionado);

                }
            });

        }

    }
}
