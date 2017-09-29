/*
 * Obtener informacion de cabeceras MP3 ID3V2
 * (Aproximación, todavía no correcto)
 */
package mp3id3v2;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public class MP3ID3V2 {

    public static void main(String[] args) {
        int buffer = 1;
        String name = "11.mp3";
        ArrayList<String> header = new ArrayList<>();
        StringBuilder dato = new StringBuilder();
        boolean seguirBuscando = true;
        
        //20 Digito control final cabecera
        byte control = 20;

        try{
            InputStream ficheroEntrada = new FileInputStream(new File(name));
            byte[] buf = new byte[buffer];
            int cantidadLeida;
            int i=0;
            while((cantidadLeida = ficheroEntrada.read(buf,0,buffer)) > 0
                   && seguirBuscando){
                
                if(buf[0] == control) seguirBuscando = false;
                if(buf[0] != 00) {
                    dato.append((char)buf[0]);
                }
                else{
                    header.add(dato.toString());
                    dato = new StringBuilder();
                }
            }
        }catch(IOException e){
            System.out.println("Error al leer");

        }
        
        String titulo = "";
        String artista = "";
        String album = "";
        String cancion = "";
        String year = "";
        String compositor = "";
        for(int i=0;i<header.size();i++){
            
            
            switch(header.get(i)){
                case "TP1":
                    titulo = header.get(i-1);
                    break;
                case "TP2":
                    artista = header.get(i-1);
                    break;
                case "TAL":
                    compositor = header.get(i-1);
                    break;
                case "TRK":
                    album = header.get(i-1);
                    break;
                case "TPA":
                    cancion = header.get(i-1);
                    break;
                case "TCO":
                    year = header.get(i-1);
                    break;
                default:
                    if(header.get(i).contains("TPE1")) titulo = header.get(i).replace("TPE1", "");
                    else if(header.get(i).contains("TYER")) titulo = header.get(i).replace("TYER", "");
                    else if(header.get(i).contains("TALB")) artista = header.get(i).replace("TALB", "");
                    else if(header.get(i).contains("TCON")) album = header.get(i).replace("TCON", "");
                    break;
            }
        }
        
        System.out.println("Titulo: " + titulo
                         + "\nArtista: " + artista
                         + "\nCompositor: " + compositor
                         + "\nAlbum: " + album
                         + "\nCanción: " + cancion
                         + "\nAño: "+ year);
    }
    
}
