package ec.com.empresa.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class confirmacion extends AppCompatActivity {


    Contactos contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        contactos = new Contactos(this, "empresa.db", 1);


    }

    public  void cmdMainActivity_onClick(View v)
    {
        //lanzar un segundo activity
        //1.Instanciamos un objeto de la clase Intent
        Intent i = new Intent(this, MainActivity.class);
        //lanzamos el activity
        startActivity(i);
    }

    public void cmdDeleteConfirmación_onClick(View v)
    {
        Bundle bundle =getIntent().getExtras();
        System.out.println(bundle.getString("nombre"));
        boolean resultado = contactos.Delete( bundle.getString("nombre"));
        if (resultado == true) {
            Toast.makeText(this, "Contacto Borrado ", Toast.LENGTH_SHORT).show();
            //lanzar un segundo activity
            //1.Instanciamos un objeto de la clase Intent
            Intent i = new Intent(this, MainActivity.class);
            //lanzamos el activity
            startActivity(i);
        }
        else
            Toast.makeText(this, "ERROR: Contacto Inexistente !", Toast.LENGTH_SHORT).show();
    }
}