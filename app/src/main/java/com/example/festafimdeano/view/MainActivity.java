package com.example.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.festafimdeano.R;
import com.example.festafimdeano.constant.FimDeAnoConstants;
import com.example.festafimdeano.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private SecurityPreferences mSecurityPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.buttonConfirmacao = findViewById(R.id.button_confirmacao);
        this.mViewHolder.textDataHoje = findViewById(R.id.text_data_hoje);
        this.mViewHolder.textDiasRestante = findViewById(R.id.text_dias_restante);

        this.mViewHolder.buttonConfirmacao.setOnClickListener(this);

        //pegar data de hoje
        this.mViewHolder.textDataHoje.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        String daysLeft = String.format("%s %s", String.valueOf(this.getDayLeft()), getString(R.string.dias));
        this.mViewHolder.textDiasRestante.setText(daysLeft);




    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.button_confirmacao) {
            String presence = this.mSecurityPreferences.getStoreString(FimDeAnoConstants.PRESENCE_KEY);

            //navegação de activity
            Intent intent = new Intent(this, DetalhesActivity.class);
            intent.putExtra(FimDeAnoConstants.PRESENCE_KEY, presence);
            startActivity(intent);
        }

    }

    private void verifyPresence() {
        String presence = this.mSecurityPreferences.getStoreString(FimDeAnoConstants.PRESENCE_KEY);
        if(presence.equals("")){
            this.mViewHolder.buttonConfirmacao.setText(getString(R.string.nao_confirmado));
        }else if(presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
            this.mViewHolder.buttonConfirmacao.setText(getString(R.string.sim));
        }else{
            this.mViewHolder.buttonConfirmacao.setText(getString(R.string.nao));
        }

    }


    private int getDayLeft() {
        //data de hoje
        Calendar calenderToday = Calendar.getInstance();
        int today = calenderToday.get(Calendar.DAY_OF_YEAR);//pegar o dia atual

        //dia maximo do ano
        Calendar calenderTodayLast = Calendar.getInstance();
        int dayMax = calenderTodayLast.getActualMaximum(Calendar.DAY_OF_YEAR);//pega a quantidade de dias do ano

        return dayMax - today;
    }

    private static class ViewHolder{
        TextView textDataHoje;
        TextView textDiasRestante;
        Button buttonConfirmacao;
    }
}
