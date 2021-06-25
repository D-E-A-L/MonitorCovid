package cargarsintomas;

import monitor.Sintoma;
import monitor.Sintomas;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.List;

public class GestorSintomas {

    private final Sintomas SINTOMAS;

    private final String PAQUETE;
    private final String RUTA_ARCHIVO;

    public GestorSintomas(String ruta, String rutah) {
        PAQUETE = rutah;
        RUTA_ARCHIVO = ruta;
        SINTOMAS = obtenerDatos(RUTA_ARCHIVO);
        hayArchivo();
    }

    private void hayArchivo() {
        File archSintomas = new File(RUTA_ARCHIVO);
        if (!archSintomas.exists()) {
            try {
                archSintomas.createNewFile();
            } catch (IOException ignored) {
            }
        }
    }

    private Sintomas obtenerDatos (String ruta) {
        Sintomas sintomas = new Sintomas();
        ObjectInputStream arch;
        try {
            arch = new ObjectInputStream(new FileInputStream(ruta));
            sintomas = (Sintomas)arch.readObject();
            arch.close();
        } catch (Exception ignored) {
        }
        return sintomas;
    }

    public Sintomas getSintomasArchivo(){
        return obtenerDatos(RUTA_ARCHIVO);
    }

    public void escribir(List<List<String>> datos) {
        for(List<String> ncad : datos) {
            if(ncad.size() > 0) {
                guardar(ncad.get(0),ncad.get(1));
            }
        }
    }

    private void guardar(String tipo, String nombreSintoma) {
        Sintoma sintoma;
        ObjectOutputStream salida;
        try {
            Class<?> cl = Class.forName(PAQUETE + "." + tipo);
            Constructor<?> constructor = cl.getConstructor(String.class);
            sintoma = (Sintoma)constructor.newInstance(nombreSintoma);
            SINTOMAS.add(sintoma);
            salida = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO));
            salida.writeObject(SINTOMAS);
            salida.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
