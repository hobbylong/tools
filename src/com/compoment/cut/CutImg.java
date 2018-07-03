package com.compoment.cut;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import com.compoment.workflow.PageFrame2;


/**
 * 显示图片，提供截取功能
 * */
public class CutImg extends JPanel {
	BufferedImage image = null;
	int x1, y1, x2, y2;

	CutImgCallBack implementInterfaceFrame;
	PageFrame2 parentView;
	/**
	 * 显示图片，提供截取功能
	 * */
	public CutImg(final CutImgCallBack implementInterfaceFrame,File file) {
		super();
		this.implementInterfaceFrame=implementInterfaceFrame;
		parentView=(PageFrame2)implementInterfaceFrame;
		
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				x1 = e.getX();
				y1 = e.getY();
				
			
			}

			public void mouseReleased(MouseEvent e) {
				x2 = e.getX();
				y2 = e.getY();
				int x = x1 < x2 ? x1 : x2;
				int y = y1 < y2 ? y1 : y2;
				int w = (x1 > x2 ? x1 : x2) - x;
				int h = (y1 > y2 ? y1 : y2) - y;
				
				Image image = CutImg.this.getImageByClip(x, y, w, h);
				//setClipboardImage2(image);
				
				implementInterfaceFrame.cutImgCallBack(image, x, y, w, h);
				
				x1 = y1 = x2 = y2 = 0;
				
//				JOptionPane.showMessageDialog(DivImageByMouse.this,
//						"图片已保存到系统粘贴板！", "图片已保存",
//						JOptionPane.INFORMATION_MESSAGE);
				//DivImg.this.repaint();
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				x2 = e.getX();
				y2 = e.getY();
				CutImg.this.repaint();
			}
		});
		try {
			image = ImageIO.read(file);
            Image img=image.getScaledInstance(320, 568, Image.SCALE_DEFAULT);//按照指定宽度和高度缩放以后的Image实例
			 image=toBufferedImage(img);
			
		} catch (IOException e) {
			System.out.println("输入文件不是一个图片文件！");
		}
	}

	public Image getImage() {
		return image;
	}

	public Image getImageByClip(int x, int y, int w, int h) {
		int rgbs[] = new int[w * h];
		rgbs = image.getRGB(x, y, w, h, rgbs, 0, w);
		BufferedImage tmpImage = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB);
		tmpImage.setRGB(0, 0, w, h, rgbs, 0, w);
		return tmpImage;
	}
	
	
	

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, this);
		//System.out.println("(" + x1 + "," + y1 + ")(" + x2 + "," + y2 + ")");
		if (x1 == 0 && y1 == 0 && x2 == 0 && y2 == 0)
			return;
		
		int x = x1 < x2 ? x1 : x2;
		int y = y1 < y2 ? y1 : y2;
		int w = (x1 > x2 ? x1 : x2) - x;
		int h = (y1 > y2 ? y1 : y2) - y;
		g.setColor(Color.blue);
		g.drawRect(x, y, w, h);
		
		for(CompomentBean bean:parentView.beans)
		{
		
		g.drawRect(bean.x, bean.y, bean.w, bean.h);
		}
	}

	protected static void setClipboardImage2(final Image image) {
		Transferable trans = new Transferable() {
			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[] { DataFlavor.imageFlavor };
			}

			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return DataFlavor.imageFlavor.equals(flavor);
			}

			public Object getTransferData(DataFlavor flavor)
					throws UnsupportedFlavorException, IOException {
				if (isDataFlavorSupported(flavor))
					return image;
				throw new UnsupportedFlavorException(flavor);
			}
		};
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(trans, null);
	}
	
    public Dimension getPreferredSize() {
      
        		Dimension theSize = new Dimension(image.getWidth(), image.getHeight());
        		  return theSize;
}

	public static void main(String args[]) {
//		JFrame jf = new JFrame("");
//		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
//		jf.setBounds(50, 50, 1024, 768);
//		
//		String courseFile=null;
//		File directory = new File("");//参数为空 
//		try {
//			 courseFile = directory.getCanonicalPath() ;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//		String path=courseFile+"/1.jpg";
//		jf.add(new DivImg(jf,new File(path)));
//		jf.add(new JButton(""), "North");
//		jf.setVisible(true);
	}
	
	
	
	/**
	 * 获得截取图片的相关属性（x,y,w,h,Image）
	 * */
	public interface CutImgCallBack
	{
	
	public void cutImgCallBack(final Image image, final int x, final int y,
			final int w, final int h) ;
	
	}
	
	
	
	public static BufferedImage toBufferedImage(Image image) {
	    if (image instanceof BufferedImage) {
	        return (BufferedImage)image;
	     }
	 
	    // This code ensures that all the pixels in the image are loaded
	     image = new ImageIcon(image).getImage();
	 
	    // Determine if the image has transparent pixels; for this method's
	    // implementation, see e661 Determining If an Image Has Transparent Pixels
	    //boolean hasAlpha = hasAlpha(image);
	 
	    // Create a buffered image with a format that's compatible with the screen
	     BufferedImage bimage = null;
	     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    try {
	        // Determine the type of transparency of the new buffered image
	        int transparency = Transparency.OPAQUE;
	       /* if (hasAlpha) {
	         transparency = Transparency.BITMASK;
	         }*/
	 
	        // Create the buffered image
	         GraphicsDevice gs = ge.getDefaultScreenDevice();
	         GraphicsConfiguration gc = gs.getDefaultConfiguration();
	         bimage = gc.createCompatibleImage(
	         image.getWidth(null), image.getHeight(null), transparency);
	     } catch (HeadlessException e) {
	        // The system does not have a screen
	     }
	 
	    if (bimage == null) {
	        // Create a buffered image using the default color model
	        int type = BufferedImage.TYPE_INT_RGB;
	        //int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
	        /*if (hasAlpha) {
	         type = BufferedImage.TYPE_INT_ARGB;
	         }*/
	         bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
	     }
	 
	    // Copy image to buffered image
	     Graphics g = bimage.createGraphics();
	 
	    // Paint the image onto the buffered image
	     g.drawImage(image, 0, 0, null);
	     g.dispose();
	 
	    return bimage;
	}
}
