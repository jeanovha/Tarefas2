package br.edu.ifro.vilhena.ads.tarefas;

import android.content.Intent;



import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.edu.ifro.vilhena.ads.tarefas.DAO.AppDatabase;
import br.edu.ifro.vilhena.ads.tarefas.model.Tarefa;

public class AlterarTarefaActivity extends AppCompatActivity {
    private TextInputLayout tilAlterarDescricao;
    private TextView txtAlterarData;
    private TextView txtAlterarHora;
    private Switch swtRealizado;
    private Button btnAlterar;
    private Calendar dataHora = Calendar.getInstance();
    private Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_tarefa);

        tilAlterarDescricao = (TextInputLayout) findViewById(R.id.til_alterar_descricao);
        txtAlterarData = (TextView) findViewById(R.id.txt_alterar_data);
        txtAlterarHora = (TextView) findViewById(R.id.txt_alterar_hora);
        swtRealizado = (Switch) findViewById(R.id.swt_realizado);
        btnAlterar = (Button) findViewById(R.id.btn_alterar);



        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        int id = args.getInt("id_tarefa");
        tarefa = AppDatabase.getAppDatabase(this).tarefaDAO().listarUm(id);

        tilAlterarDescricao.getEditText().setText(tarefa.getDescricao());

        SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
        txtAlterarData.setText(formatarData.format(tarefa.getDataHora()));

        SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm");
        txtAlterarHora.setText(formatarHora.format(tarefa.getDataHora()));

        swtRealizado.setChecked(tarefa.isRealizado());



        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarefa.setDescricao(tilAlterarDescricao.getEditText().getText().toString().trim());
                tarefa.setDataHora(dataHora.getTimeInMillis());
                tarefa.setRealizado(swtRealizado.isChecked());

                AppDatabase.getAppDatabase(AlterarTarefaActivity.this).tarefaDAO().alterar(tarefa);

                Intent intent = new Intent();
                intent.putExtra("resposta", "OK");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
