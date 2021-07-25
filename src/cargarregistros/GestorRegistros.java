package cargarregistros;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintomas;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static cargarregistros.utilitariosreg.ConvertidorReg.devolverSints;

public class GestorRegistros {

    private final String RUTA_REG;

    public GestorRegistros(String rutaReg){
        RUTA_REG = rutaReg;
        hayArchReg();
    }

    //region private methods
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
    //endregion

    //region public methods used in ConsolaRegistros
    public void guardarRegistro(List<List<String>> lSintomas) {
        Sintomas sintomas = devolverSints("sintomas",lSintomas);
        Registro registro;
        registro = new Registro(new Date(), Objects.requireNonNullElseGet(sintomas, Sintomas::new));
        Registros registros = getRegistrosArchivo();
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

    public Registros getRegistrosArchivo() {
        return obtenerRegistros();
    }
    //endregion

}
