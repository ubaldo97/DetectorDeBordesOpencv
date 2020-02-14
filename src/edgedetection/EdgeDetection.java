/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edgedetection;
import org.opencv.core.Core;
import static org.opencv.core.Core.addWeighted;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
/**
 *
 * @author ruben
 */
public class EdgeDetection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat color = Imgcodecs.imread("Muestra_4.jpg");
        Mat gris = new Mat();
        Mat blur = new Mat();
        Mat dst = new Mat();
        Mat draw = new Mat();
        Mat wide = new Mat();
        Imgproc.cvtColor(color, gris, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(gris, blur,new Size(0,0), 3);
        Imgcodecs.imwrite("Muestra_4_gris.jpg",gris);
        Core.addWeighted(gris,1.5,blur,-0.5,0,dst);
        Imgcodecs.imwrite("Muestra_4_Sharpen.jpg",dst);
        Imgproc.Canny(dst, wide, 69,208,3,true);
        double[] rgb;	
        int cont = 0;
        boolean b = false;
        wide.convertTo(draw, CvType.CV_8U);
        for(int i=0;i<150;i++){
            rgb = draw.get(50, 50+i);
            draw.put(50, 50+i, 200);
            if((rgb[0]==255)&& (b==false)){
                cont++;
                b = true;
            }else{
                b=false;
            }
        }
        double tam=0.0;
        double l = cont/2;
        tam = -6.64*Math.log10(l/1000)-3.288;
        System.out.println(cont/2);
        System.out.println("El tamaño ASTM es: "+tam);
        if(Imgcodecs.imwrite("Muestra_4_ajustada.jpg", draw)){
            System.out.println("Detectando bordes...");
            
        }
    }
 
 
    
}
