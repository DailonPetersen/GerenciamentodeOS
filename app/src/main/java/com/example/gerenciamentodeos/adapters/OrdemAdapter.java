package com.example.gerenciamentodeos.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gerenciamentodeos.AdicionarOrdens;
import com.example.gerenciamentodeos.ListagemClientes;
import com.example.gerenciamentodeos.ListagemOrdens;
import com.example.gerenciamentodeos.R;
import com.example.gerenciamentodeos.entidades.Cliente;
import com.example.gerenciamentodeos.entidades.OrdemServico;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

public class OrdemAdapter extends RecyclerView.Adapter<OrdemAdapter.ViewHolderOrdem> {

    List<OrdemServico> osList;

    public OrdemAdapter(List<OrdemServico> osList) {
        this.osList = osList;
    }

    @NonNull
    @Override
    public OrdemAdapter.ViewHolderOrdem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.viewholder_ordens, parent, false);

        ViewHolderOrdem holderOrdem = new ViewHolderOrdem(view, parent.getContext());

        return holderOrdem;
    }

    @Override
    public void onBindViewHolder(@NonNull OrdemAdapter.ViewHolderOrdem holder, int position) {

        if( (osList != null) && (osList.size() > 0) ) {
            OrdemServico osItem = osList.get(position);

            holder.nome_cliente.setText(osItem.nome_do_cliente);
            holder.data.setText(osItem.data);
            holder.modelo.setText(osItem.modelo_carro);
            holder.placa_carro.setText(osItem.placa_do_carro);
            holder.descricao.setText(osItem.descricao);
            holder.os.setText(osItem.ordem_de_servico);

        }
    }

    @Override
    public int getItemCount() {
        return osList.size();
    }

    public class ViewHolderOrdem extends RecyclerView.ViewHolder{

        public TextView nome_cliente;
        public TextView placa_carro;
        public TextView os;
        public TextView modelo;
        public TextView descricao;
        public TextView data;


        public ViewHolderOrdem(@NonNull View itemView, final Context context) {
            super(itemView);

            nome_cliente = (TextView)itemView.findViewById(R.id.nome_cliente);
            data = (TextView)itemView.findViewById(R.id.data_os);
            placa_carro = (TextView)itemView.findViewById(R.id.placa_carro);
            os = (TextView)itemView.findViewById(R.id.ordem_servico);
            modelo = (TextView)itemView.findViewById(R.id.modelo);
            descricao = (TextView)itemView.findViewById(R.id.desc);
            ImageButton deleteBtn = itemView.findViewById(R.id.deleteOS);

            itemView.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v){

                    if(osList.size() > 0){

                        OrdemServico osSelecionada = osList.get(getLayoutPosition());

                        Intent intent = new Intent(context, AdicionarOrdens.class);
                        intent.putExtra("os_selecionada", (Serializable) osSelecionada);
                        context.startActivity(intent);
                    }

                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ListagemOrdens listagemOrdens = (ListagemOrdens)context;

                    OrdemServico ordemSelecionada = osList.get(getLayoutPosition());

                    listagemOrdens.validaDelete(ordemSelecionada);

                }
            });

        }
    }
}
