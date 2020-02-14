/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fases;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author ubaldo
 */
public class GraficaCircular {

    public static void graficar(double fase1, double fase2) {
       DefaultPieDataset pieDataset = new DefaultPieDataset();
        
        pieDataset.setValue("Fase clara", fase1);
        pieDataset.setValue("Fase oscura", fase2);
        
        JFreeChart chart = ChartFactory.createPieChart(
                "Porcentaje de fases",
                pieDataset,
                true,
                true,
                true
        );
        //Mostramos la grafica en pantalla
        ChartFrame frame = new ChartFrame("Segunda fase", chart);
        frame.pack();
        frame.setVisible(true);
    }
            
}
