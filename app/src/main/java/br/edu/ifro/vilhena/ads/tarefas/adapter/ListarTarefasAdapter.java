package br.edu.ifro.vilhena.ads.tarefas.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.edu.ifro.vilhena.ads.tarefas.R;
import br.edu.ifro.vilhena.ads.tarefas.model.Tarefa;

public class ListarTarefasAdapter extends BaseAdapter {

    private final List<Tarefa> tarefas;
    private final Activity activity;

    public ListarTarefasAdapter(List<Tarefa> tarefas, Activity activity) {
        this.tarefas = tarefas;
        this.activity = activity;
    }
    @Override
    public int getCount() {
        return tarefas.size();
    }

    @Override
    public Object getItem(int position) {
        return tarefas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tarefas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.adapter_listar_tarefas, parent, false);
        Tarefa tarefa = tarefas.get(position);
        TextView txtItemDescricao = (TextView) view.findViewById(R.id.txt_item_descricao);
        TextView txtItemDataHora = (TextView) view.findViewById(R.id.txt_item_data_hora);
        txtItemDescricao.setText(tarefa.getDescricao());
        SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        txtItemDataHora.setText(formatacao.format(tarefa.getDataHora()));

        if(tarefa.isRealizado()){
            txtItemDescricao.setTextColor(Color.RED);
            txtItemDescricao.setPaintFlags(txtItemDescricao.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }
        return view;




    }

}
