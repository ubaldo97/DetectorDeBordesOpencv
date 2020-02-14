/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fases;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author ubaldo
 */
public class ProcesadorImagen { 
    
    //Método para calcular el histograma de una imagen
    public static int[] histograma(int color, BufferedImage imagen){
       int frecuencias[] = new int[256];

        for( int i = 0; i < imagen.getWidth(); i++ ){
            for( int j = 0; j < imagen.getHeight(); j++ ){
                //Obtenemos color del píxel actual
               Color pixel = new Color(imagen.getRGB(i, j));
                if(color == 1){
                    frecuencias[pixel.getRed()]++;
                }
                if(color == 2){
                    frecuencias[pixel.getGreen()]++;
                }
                if(color == 3){
                    frecuencias[pixel.getBlue()]++;
                }
                if(color == 4){
                   frecuencias[calcularMedia(pixel)]++;
                }
                
            }
        }
        return frecuencias;
    }
    
    /*Método para calcular la media del rgb de la imagen */
     private static int calcularMedia(Color color){
        int mediaColor;
        mediaColor=(int)((color.getRed()+color.getGreen()+color.getBlue())/3);
        return mediaColor;
    }
     
     public static Image generaEscalaGrises(Image original){
    
        BufferedImage aux = ManejadorImagen.toBufferedImage(original);
        // recorremos la imagen
        for (int x=0; x< aux.getWidth();x++)
            for(int y=0; y< aux.getHeight();y++){
              // obtener el color
              Color pixel = new Color(aux.getRGB(x, y));
              // en base a la clase color definimos un solo tono 
              // para los 3 canales RGB
              // con la premisa de reducir de 2exp24 a 2exp8
              int nuevo = (pixel.getRed()+pixel.getGreen()+pixel.getBlue())/3;
              pixel  = new Color(nuevo, nuevo, nuevo);
              aux.setRGB(x, y, pixel.getRGB());
            
            
            }
    
      return ManejadorImagen.toImage(aux);
    }
     
     
    /*Método para calcular el umbral por el metodo de Noboyuki otsu */
    public static int Otsu(int [] histograma ) {
        
        int umbralInicial,umbralOptimo;  // k = the current threshold; kStar = optimal threshold
        double N1, N;    // N1 = # points with intensity <=k; N = total number of points
        double BCV, BCVmax; // The current Between Class Variance and maximum BCV
        double num, denom;  // temporary bookeeping
        double Sk;  // The total intensity for all histogram points <=k
        double S, L=256; // The total intensity of the image

       
        S = N = 0;
        for (umbralInicial=0; umbralInicial<L; umbralInicial++){
            S += (double)umbralInicial * histograma[umbralInicial];   // Total histogram intensity
            N += histograma[umbralInicial];       // Total number of data points
        }

        Sk = 0;
        N1 = histograma[0]; 
        BCV = 0;
        BCVmax=0;
        umbralOptimo = 0;

        for (umbralInicial=1; umbralInicial<L-1; umbralInicial++) { 
            Sk += (double)umbralInicial * histograma[umbralInicial];
            N1 += histograma[umbralInicial];

            denom = (double)( N1) * (N - N1); 

            if (denom != 0 ){
                num = ( (double)N1 / N ) * S - Sk;  
                BCV = (num * num) / denom;
            }
            else
                BCV = 0;

            if (BCV >= BCVmax){ 
                BCVmax = BCV;
                umbralOptimo = umbralInicial;
            }
        }
        return umbralOptimo;
    }
    
      public static int metodoIterativo(int[] histograma){
    
        int ui = calcularUmbralInicial(histograma);
        int uNuevo=0;
        System.out.println(ui);
        do{
        uNuevo = ui;
        ui = reajustarUmbral(ui,histograma);
        System.out.println(ui);
        }while(uNuevo!=ui);
        
        return ui;
        
    }

    private static int calcularUmbralInicial(int[] histograma) {
        int numPixels = 0;
        int suma = 0;
        for(int x=0;x<histograma.length;x++){
        numPixels+=histograma[x];
        suma+=histograma[x]*x;
        }
        return (int)(suma/numPixels);
    }

    private static int reajustarUmbral(int ui, int[] histograma) {
       int u1,u2;
       int a1=0,a2=0,n1=0,n2=0;
       a1+=histograma[0];
       for(int x=1;x<ui;x++){
        a1+=histograma[x]*x;
        n1+=histograma[x];
       }
       
        for(int y=ui;y<=255;y++){
        a2+=histograma[y]*y;
        n2+=histograma[y];
       }
        if (n1==0 || n2==0) return 0;
        u1 = a1/n1;
        u2 = a2/n2;
       return (int)((u1+u2)/2);
    }
    
}
