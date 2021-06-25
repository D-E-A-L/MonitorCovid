package cargarregistros;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintomas;

import java.io.*;
import java.util.Date;
import java.util.List;

import static cargarregistros.guiregistros.Convertidor.devolverSints;

public class GestorRegistros {

    private final Registros REGISTROS;

    private final String RUTA_REG;

    public GestorRegistros(String rutaReg){
        RUTA_REG = rutaReg;
        REGISTROS = obtenerRegistros();
        hayArchReg();
    }

    private void hayArchReg() {
        File areg = new File(RUTA_REG);
        if (!areg.exists()) {
            try {
                areg.createNewFile();
            } catch (IOException ignored) {
            }
        }
    }

    private Registros obtenerRegistros(){
        Registros registros = new Registros();
        ObjectInputStream archivo;
        try {
            archivo = new ObjectInputStream(new FileInputStream(RUTA_REG));
            registros = (Registros)archivo.readObject();
            archivo.close();
        } catch (Exception ignored) {
        }

        return registros;
    }

    public void guardarRegistro(List<List<String>> lSintomas) {
        Sintomas sintomas = devolverSints("sintomas",lSintomas);
        Registro registro = null;
        if(sintomas != null) {
            registro = new Registro(new Date(), sintomas);
        } else {
            registro = new Registro(new Date(), new Sintomas());
        }
        Registros registros = this.getRegistrosArchivo();
        ObjectOutputStream file;
        try {
            registros.push(registro);
            file = new ObjectOutputStream(new FileOutputStream(RUTA_REG));
            file.writeObject(registros);
            file.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public Registro obtUltRegistro() {
        Registro ultreg = null;
        ObjectInputStream archivo;
        try {
            archivo = new ObjectInputStream(new FileInputStream(RUTA_REG));
            Registros registros = (Registros)archivo.readObject();
            ultreg = registros.peek();
            archivo.close();
        } catch (Exception ignored) {
        }
        return ultreg;
    }

    public Registros getRegistrosArchivo() {
        return obtenerRegistros();
    }

}
