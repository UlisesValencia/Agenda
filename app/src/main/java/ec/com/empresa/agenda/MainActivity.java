package ec.com.empresa.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Contactos contactos;
    EditText  txtNombre, txtTelefono, txtDireccion, txtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactos = new Contactos(this, "empresa.db", 1);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtEmail = findViewById(R.id.txtEmail);
        Bundle bundle =getIntent().getExtras();
        String nombre;
        String telefono;
        String direccion;
        String email;
        try{
           nombre=bundle.getString("nombre");
           telefono=bundle.getString("telefono");
           direccion=bundle.getString("direccion");
           email=bundle.getString("email");
            if (nombre != null && telefono != null && direccion != null && email != null) {
                // Si todas las variables no son nulas, ejecuta este bloque de código
                nombre = nombre.replaceFirst("\\s+$", "");
                txtNombre.setText(nombre);
                txtTelefono.setText(telefono);
                txtDireccion.setText(direccion);
                txtEmail.setText(email);
            }
        }catch (Exception e) {}
    }

    public void cmdCreate_onClick(View v)
    {
        Contacto p = contactos.Create(
                txtNombre.getText().toString(),
                txtTelefono.getText().toString(),
                txtDireccion.getText().toString(),
                txtEmail.getText().toString()
        );
        if (p != null) {
            Toast.makeText(this, "Contacto Agregado", Toast.LENGTH_SHORT).show();
            //lanzar un segundo activity
            //1.Instanciamos un objeto de la clase Intent
            Intent i = new Intent(this, Activity2.class);
            //lanzamos el activity
            startActivity(i);
        }
        else
            Toast.makeText(this, "ERROR: Contacto no Agregado !", Toast.LENGTH_SHORT).show();
    }

    public void cmdUpdate_onClick(View v)
    {

        boolean resultado = contactos.Update(
                txtNombre.getText().toString(),
                txtTelefono.getText().toString(),
                txtDireccion.getText().toString(),
                txtEmail.getText().toString()
        );
        if (resultado == true){
            Toast.makeText(this, "Contacto Actualizado", Toast.LENGTH_SHORT).show();
            //lanzar un segundo activity
            //1.Instanciamos un objeto de la clase Intent
            Intent i = new Intent(this, Activity2.class);
            //lanzamos el activity
            startActivity(i);
        }
        else
            Toast.makeText(this, "ERROR: Contacto no Actualizado!", Toast.LENGTH_SHORT).show();
    }

    public void cmdDelete_onClick(View v)
    {
        //lanzar un segundo activity
        //1.Instanciamos un objeto de la clase Intent
        Intent confirmacion = new Intent(this, confirmacion.class);
        //Pasar datos al 2do activity
        confirmacion.putExtra("nombre",txtNombre.getText().toString());
        //lanzamos el activity
        startActivity(confirmacion);

    }

    public  void cmdActivity2_onClick(View v)
    {
        //lanzar un segundo activity
        //1.Instanciamos un objeto de la clase Intent
        Intent i = new Intent(this, Activity2.class);
        //lanzamos el activity
        startActivity(i);
    }



}