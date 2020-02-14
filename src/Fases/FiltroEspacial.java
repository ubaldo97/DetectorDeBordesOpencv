/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fases;


import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import org.jfree.chart.ChartColor;


/**
 *
 * @author Roberto Cruz Leija
 */
public class FiltroEspacial {
    
   
    public static Image umbralizacionSimple(int umbral, Image imagenOriginal){
      
        BufferedImage aux = ManejadorImagen.toBufferedImage(imagenOriginal);
        // recorremos la imagen
        for (int x=0; x< aux.getWidth();x++)
            for(int y=0; y< aux.getHeight();y++){
                Color pixel = new Color(aux.getRGB(x, y));
                int r = pixel.getRed();
                int g = pixel.getGreen();
                int b = pixel.getBlue();
                // modificamos el rojo 
                if(pixel.getRed()>umbral){
                    r = 255;
                }else{
                    r = 0;
                }
                if(pixel.getGreen()>umbral){
                    g = 255;
                }else{
                    g = 0;
                }
                if(pixel.getBlue()>umbral){
                    b = 255;
                }else{
                    b = 0;
                }
                pixel = new Color(r, g, b);
                aux.setRGB(x, y, pixel.getRGB());
            
            }
        return ManejadorImagen.toImage(aux);
    }
    
    public static Image umbralizacionSimple(int umbral,int umbral2 ,Image imagenOriginal){
    
        
        BufferedImage aux = ManejadorImagen.toBufferedImage(imagenOriginal);
        // recorremos la imagen
        for (int x=0; x< aux.getWidth();x++)
            for(int y=0; y< aux.getHeight();y++){
                Color pixel = new Color(aux.getRGB(x, y));
                int r = pixel.getRed();
                int g = pixel.getGreen();
                int b = pixel.getBlue();
                // modificamos el rojo 
                if(pixel.getRed()<umbral||pixel.getRed()>umbral2)
                    r = 0;
                if(pixel.getGreen()<umbral||pixel.getGreen()>umbral2)
                    g = 0;
                if(pixel.getBlue()<umbral||pixel.getBlue()>umbral2)
                    b = 0;
                pixel = new Color(r, g, b);
                aux.setRGB(x, y, pixel.getRGB());
            
            }
        return ManejadorImagen.toImage(aux);
    
    }
    
    public static Image generaEscalaGrises(Image original){
    
        BufferedImage aux = ManejadorImagen.toBufferedImage(original);
        
        // recorremos la imagen
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

    public static Image iluminarImagen(Image original, int valor){
        BufferedImage aux = ManejadorImagen.toBufferedImage(original);
        
        // recorremos la imagen
        // recorremos la imagen
        for (int x=0; x< aux.getWidth();x++)
            for(int y=0; y< aux.getHeight();y++){
              // obtener el color
              Color pixel = new Color(aux.getRGB(x, y));
              int r = pixel.getRed()+valor;
              int g = pixel.getGreen()+valor;
              int b = pixel.getBlue()+valor;
            // validamos 
            if(r<0)r=0;
            if(r>255)r=255;
            if(g<0)g=0;
            if(g>255)g=255;
            if(b<0)b=0;
            if(b>255)b=255;
            
            pixel = new Color(r, g, b);
            aux.setRGB(x, y, pixel.getRGB());
            }
    
      return ManejadorImagen.toImage(aux);
    }



}
