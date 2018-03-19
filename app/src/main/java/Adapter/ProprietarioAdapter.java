package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import Model.Peca;
import Model.Proprietario;
import atividade.bd_19_03.R;


public class ProprietarioAdapter extends RecyclerView.Adapter {

    private List<Proprietario> p;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public ProprietarioAdapter(List<Proprietario> p, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {

        this.p = p;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.itens_pro, parent, false);
        ProViewHolder proViewHolder = new ProViewHolder(view);
        return proViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
                                 int position) {

        ProViewHolder holder = (ProViewHolder) viewHolder;

        Proprietario pr  = p.get(position) ;

        holder.nomePro.setText(pr.getNome());
        holder.end.setText(pr.getEndereco());
    }

    @Override
    public int getItemCount() {

        return p.size();
    }

    public class ProViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomePro;
        private final TextView end;


        public ProViewHolder(View itemView) {
            super(itemView);
            nomePro = (TextView) itemView.findViewById(R.id.nomePro);
            end = (TextView) itemView.findViewById(R.id.endereco);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(p.get(getLayoutPosition()));

                }
            });


        }
    }
}