package com.example.calculateurimc.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.calculateurimc.modele.MySQLiteOpenHelper;
import com.example.calculateurimc.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected EditText surname;
    protected EditText name;
    protected Button btn_logo;
    protected Spinner poids;
    protected Spinner taille;
    MySQLiteOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MySQLiteOpenHelper(this);

        getUIViews();
        addItemsOnSpinnerPoids();
        addItemsOnSpinnerTaille();

        btn_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On récupère les valeurs de type String des champs.
                String _surname = surname.getText().toString();
                String _name = name.getText().toString();
                String _poids = String.valueOf(poids.getSelectedItem());
                String _taille = String.valueOf(taille.getSelectedItem());

                // On vérifie que les champs ne sont pas vides.
                if (!(_surname.isEmpty()) && !(_name.isEmpty()) && !(_poids.isEmpty()) && !(_taille.isEmpty())) {
                    float poids2 = (float)Float.parseFloat(_poids); // On parse la valeur string en float
                    float taille2 = (float)Integer.parseInt(_taille); // On parse la valeur string en int

                    taille2 = taille2 / 100; // On convertie la taille donnée en cm en m.
                    float calcul_imc = poids2 / (taille2 * taille2); // On calcule l'IMC

                    boolean isInserted = db.insertData(_name, _surname, calcul_imc);
                    if (isInserted) {
                        Toast.makeText(MainActivity.this, "Données insérées correctement.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, ResultActivity.class);
                        i.putExtra("surname", _surname);
                        i.putExtra("name", _name);
                        i.putExtra("imc", calcul_imc);
                        startActivity(i); // On redirige vers l'activité ResultActivity.class
                    } else {
                        Toast.makeText(MainActivity.this, "Données non enregistrées.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Veillez à remplir tous les champs.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getUIViews() {
        surname = (EditText)findViewById(R.id.etSurname);
        name = (EditText)findViewById(R.id.etName);
        btn_logo = (Button)findViewById(R.id.btnValidation);
        poids = (Spinner) findViewById(R.id.s_poids);
        taille = (Spinner) findViewById(R.id.s_taille);
    }

    public void addItemsOnSpinnerPoids() {
        poids = (Spinner) findViewById(R.id.s_poids); // récupération du spinner cible sur poids
        List<Integer> list_poids = new ArrayList<Integer>(); // création d'une nouvelle liste
        for (int i = 35; i < 200; i++) {
            list_poids.add(i); // On ajoute les éléments de 25 à 199 à la liste
        }
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, list_poids);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // affichage du dropdown selon un modèle
        poids.setAdapter(dataAdapter); // Attribution du dropdown au spinner poids
    }

    public void addItemsOnSpinnerTaille() {
        taille = (Spinner) findViewById(R.id.s_taille);
        List<Integer> list_taille = new ArrayList<Integer>();
        for (int i = 100; i < 220; i++) {
            list_taille.add(i);
        }
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, list_taille);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taille.setAdapter(dataAdapter);
    }

}