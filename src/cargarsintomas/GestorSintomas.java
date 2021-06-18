package cargarsintomas;

import monitor.Sintoma;
import monitor.Sintomas;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.List;

public class GestorSintomas {

    private Sintomas sintomas;

    private boolean guardado;

    private final String PAQUETE;
    private final String RUTA_ARCHIVO;

    public GestorSintomas(String ruta) {
        guardado = false;
        sintomas = new Sintomas();
        PAQUETE = "sintomas";
        RUTA_ARCHIVO = ruta;
        hayArchivo();
    }

    private void hayArchivo() {
        File archSintomas = new File(RUTA_ARCHIVO);
        if (!archSintomas.exists()) {
            try {
                archSintomas.createNewFile();
            } catch (IOException i) {
            }
        }
    }

    public boolean estaGuardado() {return guardado;}

    private Sintomas obtenerDatos (String ruta) {
        /*Sintomas sintomas = new Sintomas();
        for(Object obj: leerDat(ruta)) {
            Sintoma sintoma = (Sintoma) obj;
            sintomas.add(sintoma);
        } return sintomas;*/
        Sintomas sintomas = new Sintomas();
        ObjectInputStream file = null;

        try {
            file = new ObjectInputStream(new FileInputStream(ruta));
            sintomas = (Sintomas)file.readObject();
            file.close();
        } catch (Exception var4) {
        }

        return sintomas;
    }

    public Sintomas getSintomasArchivo(){
        //return sintomas;
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
            sintomas.add(sintoma);
            salida = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO));
            salida.writeObject(sintomas);
            salida.close();
            guardado = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
