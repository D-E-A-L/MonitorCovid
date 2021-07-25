package monitor;

import java.io.*;

public class DatosFase {

    //region private methods
    private String getPath(){
        File miDir = new File (".");
        String dir="", path, separador = System.getProperty("file.separator");
        try {
            dir= miDir.getCanonicalPath();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        boolean esDesarrollo = false;
        File file2 = new File(dir);
        String[] a = file2.list();

        assert a != null;
        for (String s : a) {
            if (s.equals("src")) {
                esDesarrollo = true;
                break;
            }
        }

        String rutArch = "fase.dat";
        if ( !esDesarrollo ){
            path = dir+separador+  rutArch;
        } else {
            path = dir+separador+"src"+separador+  rutArch;
        }
        return path;
    }

    private boolean existeDatosFase(){
        File f = new File(getPath());
        return f.exists();
    }
    //endregion

    //region public methods used in DiagnosticoPorFase, Monitor
    public Fase leerDatosFase() {
        Fase fase = null;
        try {
            if (existeDatosFase()){
                ObjectInputStream file = new ObjectInputStream(new FileInputStream(getPath()));
                fase = (Fase) file.readObject();
                file.close();
            } else {
                fase = new Fase("PrimeraFase");
            }
        } catch (ClassNotFoundException | IOException e){

            e.printStackTrace();
        }
        return fase;
    }

    public void guardarDatosFase(Fase fase){
        try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(getPath()));
            file.writeObject(fase);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

}
