package com.example.sp.pruebaca;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Consultar extends AppCompatActivity {

    EditText cajaNombre, cajaMatricula, cajaEdad,cajaCarrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        cajaNombre = this.findViewById(R.id.cajaN2);
        cajaMatricula = this.findViewById(R.id.cajaM2);
        cajaCarrera = this.findViewById(R.id.cajaC2);
        cajaEdad = this.findViewById(R.id.cajaE2);
    }

    public void buscar(View view){
        try {
            AdminSQLiteOpenHelper obj =
                    new AdminSQLiteOpenHelper(this,
                            "alumno", null, 1);
            SQLiteDatabase db = obj.getReadableDatabase();
            Cursor resultado = db.rawQuery(
                    "SELECT * FROM alumno WHERE matricula='" +
                            cajaMatricula.getText().toString() + "'"
                    , null);
            if (resultado.moveToFirst()) {
                cajaNombre.setText(resultado.getString(1));
                cajaEdad.setText(resultado.getString(2));
                cajaCarrera.setText(resultado.getString(3));
            }
        }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }


}
