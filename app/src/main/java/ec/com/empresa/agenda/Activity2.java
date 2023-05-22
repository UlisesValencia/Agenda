package ec.com.empresa.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Activity2 extends AppCompatActivity {

    Contactos contactos;
    TableLayout tableLayout;
    EditText txtBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        tableLayout = findViewById(R.id.agenda);
        txtBuscar = findViewById(R.id.txtBuscar);

        contactos = new Contactos(this, "empresa.db", 1);

        //Cabecera
        CabeceraTableLayout();


        try {
            //Filas
            FilasTableLayout();
        } catch (Exception e) {

            e.printStackTrace();
        }



    }



    public  void cmdActivity_main_onClick(View v)
    {
        //lanzar un segundo activity
        //1.Instanciamos un objeto de la clase Intent
        Intent i = new Intent(this, MainActivity.class);
        //lanzamos el activity
        startActivity(i);
    }

    public void cmdBuscarContacto(View v){

        if (txtBuscar == null || txtBuscar.getText().toString().trim().isEmpty()) {
            tableLayout.removeAllViews();
            // La variable txtBuscar es null o su contenido está vacío (sólo espacios)
            //Cabecera
            CabeceraTableLayout();
            //Filas
            FilasTableLayout();
        } else {

            // La variable txtBuscar no es null y contiene texto válido
                Contacto cont=contactos.Read_One(txtBuscar.getText().toString());
                if(cont!=null){
                    tableLayout.removeAllViews();
                    //Cabecera
                    CabeceraTableLayout();
                    TableRow tableRow = new TableRow(this);
                    tableRow.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                // Crear y agregar las TextViews a la fila
                TextView nombreTextView = new TextView(this);
                nombreTextView.setText(cont.Nombre+"\t");
                tableRow.addView(nombreTextView);

                TextView telefonoTextView = new TextView(this);
                telefonoTextView.setText(cont.Telefono+"\t");
                tableRow.addView(telefonoTextView);

                TextView direccionTextView = new TextView(this);
                direccionTextView.setText(cont.Direccion+"\t");
                tableRow.addView(direccionTextView);

                TextView emailTextView = new TextView(this);
                emailTextView.setText(cont.Email+"\t");
                tableRow.addView(emailTextView);

                    tableRow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TableRow row = (TableRow) v;
                            int childCount = row.getChildCount();
                            String[] selectedRowData = new String[childCount];

                            for (int i = 0; i < childCount; i++) {
                                TextView textView = (TextView) row.getChildAt(i);
                                selectedRowData[i] = textView.getText().toString();
                            }

                            // Aquí puedes guardar o procesar los datos de la fila seleccionada
                            processSelectedRowData(selectedRowData);
                        }
                    });

                // Agregar la fila al TableLayout
                tableLayout.addView(tableRow);
                }else{
                    Toast.makeText(this, "ERROR: Contacto no Valido!", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void CabeceraTableLayout(){
        //Cabecera
        TableRow cabeceraRow = new TableRow(this);
        cabeceraRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        // Crear y agregar las TextViews a la fila
        TextView nombrecabeceraTextView = new TextView(this);
        nombrecabeceraTextView.setText("NOMBRE"+"\t");
        cabeceraRow.addView(nombrecabeceraTextView);
        TextView telefonocabeceraTextView = new TextView(this);
        telefonocabeceraTextView.setText("TELEFONO"+"\t");
        cabeceraRow.addView(telefonocabeceraTextView);
        TextView direccioncabeceraTextView = new TextView(this);
        direccioncabeceraTextView.setText("DIRECCIÓN"+"\t");
        cabeceraRow.addView(direccioncabeceraTextView);
        TextView emailcabeceraTextView = new TextView(this);
        emailcabeceraTextView.setText("EMAIL"+"\t");
        cabeceraRow.addView(emailcabeceraTextView);
        tableLayout.addView(cabeceraRow);
    }

    public  void FilasTableLayout(){
        for (Contacto contacto :  contactos.Read_All()) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            // Crear y agregar las TextViews a la fila
            TextView nombreTextView = new TextView(this);
            nombreTextView.setText(contacto.Nombre+"\t");
            tableRow.addView(nombreTextView);

            TextView telefonoTextView = new TextView(this);
            telefonoTextView.setText(contacto.Telefono+"\t");
            tableRow.addView(telefonoTextView);

            TextView direccionTextView = new TextView(this);
            direccionTextView.setText(contacto.Direccion+"\t");
            tableRow.addView(direccionTextView);

            TextView emailTextView = new TextView(this);
            emailTextView.setText(contacto.Email+"\t");
            tableRow.addView(emailTextView);

            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TableRow row = (TableRow) v;
                    int childCount = row.getChildCount();
                    String[] selectedRowData = new String[childCount];

                    for (int i = 0; i < childCount; i++) {
                        TextView textView = (TextView) row.getChildAt(i);
                        selectedRowData[i] = textView.getText().toString();
                    }

                    // Aquí puedes guardar o procesar los datos de la fila seleccionada
                    processSelectedRowData(selectedRowData);
                }
            });

            // Agregar la fila al TableLayout
            tableLayout.addView(tableRow);
        }
    }

    private void processSelectedRowData(String[] rowData) {
        // Aquí puedes guardar o procesar los datos de la fila seleccionada
        Log.d("SelectedRow", Arrays.toString(rowData));
        //lanzar un segundo activity
        //1.Instanciamos un objeto de la clase Intent
        Intent confirmacion = new Intent(this, MainActivity.class);
        //Pasar datos al 2do activity
        confirmacion.putExtra("nombre",rowData[0]);
        confirmacion.putExtra("telefono",rowData[1]);
        confirmacion.putExtra("direccion",rowData[2]);
        confirmacion.putExtra("email",rowData[3]);
        //lanzamos el activity
        startActivity(confirmacion);
    }


}