package com.example.calculateurimc.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calculateurimc.R;

public class ResultActivity extends AppCompatActivity {

    ImageView imageView;
    String surname;
    String name;
    Float imc;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        imageView = (ImageView) findViewById(R.id.imageView);
        btn = (Button) findViewById(R.id.btn_dataView);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("surname")) {
                surname = intent.getStringExtra("surname");
            }
            if (intent.hasExtra("name")) {
                name = intent.getStringExtra("name");
            }
            if (intent.hasExtra("imc")) {
                imc = intent.getFloatExtra("imc", 0);
            }
        }

        setResultatNames(surname, name);
        setResultat_imc(imc);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultActivity.this, DataView.class);
                startActivity(i);
            }
        });
    }

    private void setResultatNames(String surname,String name) {
        TextView resultat;
        resultat = (TextView)findViewById(R.id.tv_resultat);
        resultat.setText("Bienvenue " + surname + " " + name);
    }

    private void setResultat_imc(Float imc) {
        TextView resultat_imc;
        resultat_imc = (TextView) findViewById(R.id.tv_resultat_imc);

        if (imc < 18.5) {
            showImageMaigreur();
            resultat_imc.setText("Votre IMC est de " + imc);
        } else if (imc >=18.5 && imc < 25) {
            showImagePoidsNormal();
            resultat_imc.setText("Votre IMC est de " + imc);
        } else if (imc >= 25 && imc < 30) {
            showImageSurpoids();
            resultat_imc.setText("Votre IMC est de " + imc);
        } else if (imc >= 30 && imc < 35) {
            showImageObesite();
            resultat_imc.setText("Votre IMC est de " + imc);
        } else if (imc >= 35 && imc < 40) {
            showImageObesiteSevere();
            resultat_imc.setText("Votre IMC est de " + imc);
        } else {
            showImageObesiteMorbide();
            resultat_imc.setText("Votre IMC est de " + imc);
        }
    }

    private void showImageMaigreur() {
        this.imageView.setImageResource(R.drawable.maigreur);
    }

    private void showImagePoidsNormal() {
        this.imageView.setImageResource(R.drawable.poids_normal);
    }

    private void showImageSurpoids() {
        this.imageView.setImageResource(R.drawable.surpoids);
    }

    private void showImageObesite() {
        this.imageView.setImageResource(R.drawable.obesite);
    }

    private void showImageObesiteSevere() {
        this.imageView.setImageResource(R.drawable.obesite_severe);
    }

    private void showImageObesiteMorbide() {
        this.imageView.setImageResource(R.drawable.obesite_morbide);
    }
}