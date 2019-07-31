package com.example.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.festafimdeano.R;
import com.example.festafimdeano.constant.FimDeAnoConstants;
import com.example.festafimdeano.data.SecurityPreferences;

public class DetalhesActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkBoxParticipar = findViewById(R.id.checkbox_participar);

        this.mViewHolder.checkBoxParticipar.setOnClickListener(this);

        this.loadDataFromActivity();


    }



    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.checkbox_participar){

            if(this.mViewHolder.checkBoxParticipar.isChecked()){
                //salvar a presença
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_YES);
            }else{
                //salva a ausencia
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_NO);
            }
        }
    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String presence = extras.getString(FimDeAnoConstants.PRESENCE_KEY);
            if(presence != null && presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
                this.mViewHolder.checkBoxParticipar.setChecked(true);
            }else{
                this.mViewHolder.checkBoxParticipar.setChecked(false);
            }
        }

    }


    private static class ViewHolder{
        CheckBox checkBoxParticipar;
    }
}
