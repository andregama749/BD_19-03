package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import Model.Peca;
import atividade.bd_19_03.R;


public class PecaAdapter extends RecyclerView.Adapter {

    private List<Peca> pecas;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public PecaAdapter(List<Peca> pecas, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {

        this.pecas = pecas;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.itens_pecas, parent, false);
        PecaViewHolder pecaViewHolder = new PecaViewHolder(view);
        return pecaViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
                                 int position) {

        PecaViewHolder holder = (PecaViewHolder) viewHolder;

        Peca peca  = pecas.get(position) ;

        holder.nomePeca.setText(peca.getNome());
        holder.descricao.setText(peca.getDescricao());
    }

    @Override
    public int getItemCount() {

        return pecas.size();
    }

    public class PecaViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomePeca;
        private final TextView descricao;


        public PecaViewHolder(View itemView) {
            super(itemView);
            nomePeca = (TextView) itemView.findViewById(R.id.nomePeca);
            descricao = (TextView) itemView.findViewById(R.id.descricao);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(pecas.get(getLayoutPosition()));

                }
            });


        }
    }
}