package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import Model.Servico;
import atividade.bd_19_03.R;


public class ServicoAdapter extends RecyclerView.Adapter {

    private List<Servico> s;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public ServicoAdapter(List<Servico> s, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {

        this.s = s;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.itens_ser, parent, false);
        SerViewHolder serViewHolder = new SerViewHolder(view);
        return serViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        SerViewHolder holder = (SerViewHolder) viewHolder;

        Servico se  = s.get(position) ;

        holder.nomeSer.setText(se.getNome());
        holder.horas.setText(se.getHoras());
        holder.mec.setText(se.getMecanico());
    }

    @Override
    public int getItemCount() {

        return s.size();
    }

    public class SerViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeSer;
        private final TextView horas;
        private final TextView mec;


        public SerViewHolder(View itemView) {
            super(itemView);
            nomeSer = (TextView) itemView.findViewById(R.id.nomeSer);
            horas = (TextView) itemView.findViewById(R.id.horas);
            mec = (TextView) itemView.findViewById(R.id.mec);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(s.get(getLayoutPosition()));

                }
            });


        }
    }
}