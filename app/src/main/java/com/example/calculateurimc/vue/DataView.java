package com.example.calculateurimc.vue;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.calculateurimc.modele.MySQLiteOpenHelper;
import com.example.calculateurimc.R;

public class DataView extends AppCompatActivity {

//    private ListView listView;
    private Button btn;
    MySQLiteOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);

        db = new MySQLiteOpenHelper(this);
//        listView = (ListView) findViewById(R.id.lv_dataView);
        btn = (Button) findViewById(R.id.btn_dataLayout);

        viewAll();
    }

    private void viewAll() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = db.getAllDatas();
                if (data.getCount() == 0) {
                    showMessage("Erreur", "Pas de données trouvées.");
                } else {
                    StringBuilder buffer = new StringBuilder();
                    while (data.moveToNext()) {
                        buffer.append("id : ").append(data.getString(0)).append("\n");
                        buffer.append("Nom : ").append(data.getString(1)).append("\n");
                        buffer.append("Prenom : ").append(data.getString(2)).append("\n");
                        buffer.append("IMC : ").append(data.getString(3)).append("\n");
                    }
                    showMessage("Utilisateurs", buffer.toString());
                }
            }
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}