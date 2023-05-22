package ec.com.empresa.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Contactos {
    private DBHellper dbHelper;
    private SQLiteDatabase db;

    public Contactos(Context contexto, String dbName, int version){
        dbHelper = new DBHellper(contexto, dbName, null, version);
    }

    public Contacto Create( String nombre, String telefono, String direccion, String email )
    {
        db = dbHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("nombre", nombre);
        row.put("telefono", telefono);
        row.put("direccion", direccion);
        row.put("email", email);

        long qty = db.insert("agenda", null, row);
        if (qty > 0)
        {
            Contacto data = new Contacto();
            data.Nombre = nombre;
            data.Telefono = telefono;
            data.Direccion = direccion;
            data.Email = email;
            return  data;
        }
        else
        {
            return  null;
        }
    }

    public  Contacto Read_One( String nombre)
    {
        db = dbHelper.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT nombre, telefono, direccion, email FROM agenda WHERE nombre = '" + nombre + "' ", null);
        if (cr.getCount()>0)
        {
            Contacto cont = new Contacto();
            cr.moveToNext();
            cont.Nombre = cr.getString(0);
            cont.Telefono = cr.getString(1);
            cont.Direccion = cr.getString(2);
            cont.Email = cr.getString(3);

            return cont;
        }
        else {
            return  null;
        }
    }

    public  Contacto[] Read_All()
    {
        db = dbHelper.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT nombre, telefono, direccion, email FROM agenda ORDER BY nombre", null);
        if (cr.getCount()>0)
        {
            Contacto[] datos = new Contacto[cr.getCount()];
            Contacto cont;
            int i = 0;

            while (cr.moveToNext())
            {
                cont = new Contacto();
                cont.Nombre = cr.getString(0);
                cont.Telefono = cr.getString(1);
                cont.Direccion = cr.getString(2);
                cont.Email = cr.getString(3);
                datos[i++]= cont;
            }
            return  datos;
        }
        else {
            return  null;
        }
    }

    public  Contacto[] Read_ByNombre(String find)
    {
        db = dbHelper.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT nombre, telefono, direccion, email FROM agenda WHERE nombre LIKE '%" + find + "%' ORDER BY nombre", null);
        if (cr.getCount()>0)
        {
            Contacto[] datos = new Contacto[cr.getCount()];
            Contacto cont;
            int i = 0;

            while (cr.moveToNext())
            {
                cont = new Contacto();
                cont.Nombre = cr.getString(0);
                cont.Telefono = cr.getString(1);
                cont.Direccion = cr.getString(2);
                cont.Email = cr.getString(3);
                datos[i++]= cont;
            }
            return  datos;
        }
        else {
            return  null;
        }
    }

    public boolean Update( String nombre, String telefono,String direccion ,String email)
    {
        db = dbHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("nombre", nombre);
        row.put("telefono", telefono);
        row.put("direccion", direccion);
        row.put("email", email);

        int qty = db.update("agenda", row,"nombre='"+nombre+"'", null);
        return qty>0;
    }

    public boolean Delete( String nombre)
    {
        db = dbHelper.getWritableDatabase();
        int qty = db.delete("agenda", "nombre='"+nombre+"'", null);
        return qty>0;
    }

}


