/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fases;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
/**
 *
 * @author ubaldo
 */
public class ImagePreview extends JPanel implements PropertyChangeListener{

	private int width;
	private int height;
	private Image imagen;
	private ImageIcon icono;
	private static final int size=155;
	private Color color;

	public ImagePreview() {
		setPreferredSize(new Dimension(size,-1));
		color = getBackground();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if(propertyName.equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)){
			File file = (File) evt.getNewValue();
			
			String name;
			
			if(file != null){
				name = file.getAbsolutePath();
				
				if(name != null && name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".png")){ 
					icono = new ImageIcon(name);
					imagen = icono.getImage();
					scaleImage();
					repaint();
				}
			}
		}
	}

	private void scaleImage() {
		width = imagen.getWidth(this);
		height = imagen.getHeight(this);
		double ratio = 1.0;
		if (width >= height) {
            ratio = (double)(size-5) / width;
            width = size-5;
            height = (int)(height * ratio);
		}
        else {
            if (getHeight() > 150) {
                ratio = (double)(size-5) / height;
                height = size-5;
                width = (int)(width * ratio);
            }
            else {
                ratio = (double)getHeight() / height;
                height = getHeight();
                width = (int)(width * ratio);
            }
        }
                
        imagen = imagen.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	}
	
	 public void paintComponent(Graphics g) {
        g.setColor(color);
        
        /*
         * If we don't do this, we will end up with garbage from previous
         * images if they have larger sizes than the one we are currently
         * drawing. Also, it seems that the file list can paint outside
         * of its rectangle, and will cause odd behavior if we don't clear
         * or fill the rectangle for the accessory before drawing. This might
         * be a bug in JFileChooser.
         */
        g.fillRect(0, 0, size, getHeight());
        g.drawImage(imagen, getWidth() / 2 - width / 2 + 5,
                getHeight() / 2 - height / 2, this);
    }
}
