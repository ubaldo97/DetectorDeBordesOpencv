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
        Mat color = Imgcodecs.imread("metal.jpg");
        Mat gris = new Mat();
        Mat blur = new Mat();
        Mat dst = new Mat();
        Mat draw = new Mat();
        Mat wide = new Mat();
        Imgproc.cvtColor(color, gris, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(gris, blur,new Size(0,0), 3);
        Imgcodecs.imwrite("metal_gris.jpg",gris);
        Core.addWeighted(gris,1.5,blur,-0.5,0,dst);
        Imgcodecs.imwrite("metal_Sharpen.jpg",dst);
        Imgproc.Canny(dst, wide, 80,240,3,true);
        double[] rgb;	
        int cont = 0;
        boolean b = false;
        wide.convertTo(draw, CvType.CV_8U);
        for(int i=0;i<100;i++){
            rgb = draw.get(200, 10+i);
            draw.put(200, 10+i, 100);
            if((rgb[0]==255)&& (b==false)){
                cont++;
                b = true;
            }else{
                b=false;
            }
        }
        
        System.out.println(cont/2);
        if(Imgcodecs.imwrite("metal_ajustada.jpg", draw)){
            System.out.println("Detectando bordes...");
            
        }
    }
 
 
    
}
