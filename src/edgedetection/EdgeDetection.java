/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edgedetection;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
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
        Mat draw = new Mat();
        Mat wide = new Mat();
        
        Imgproc.cvtColor(color, gris, Imgproc.COLOR_BGR2GRAY);
        Imgproc.Canny(gris, wide, 50, 150,3,true);
        wide.convertTo(draw, CvType.CV_8U);
        
        if(Imgcodecs.imwrite("metal_bordes3.jpg", draw)){
            System.out.println("Detectando bordes...");
        }
    }
    
}
