package com.example.sp.pruebaca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

EditText cajaNombre,cajaEdad,cajaCarrera,cajaMatricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cajaNombre = this.findViewById(R.id.cajaN2);
        cajaCarrera = this.findViewById(R.id.cajaC2);
        cajaMatricula = this.findViewById(R.id.cajaM2);
        cajaEdad = this.findViewById(R.id.cajaE2);
    }

    public void alta(View view){
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this,"alumno", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("nombre",cajaNombre.getText().toString());
        registro.put("matricula",Integer.valueOf(cajaMatricula.getText().toString()));
        registro.put("edad",Integer.valueOf(cajaEdad.getText().toString()));
        registro.put("carrera",cajaCarrera.getText().toString());
        bd.insert("alumno",null,registro);

        Cursor resultado = bd.rawQuery(
                "SELECT COUNT(*) FROM alumno"
                , null);
        if (resultado.moveToFirst()) {
            Toast.makeText(this,
                    resultado.getInt(0),
                    Toast.LENGTH_LONG).show();
        }
        bd.close();
        Toast.makeText(this, "Alumno registrado", Toast.LENGTH_LONG).show();
    }

    public void cambiar(View view){
        Intent obj = new Intent(this,Consultar.class);
        this.startActivity(obj);
    }
}
