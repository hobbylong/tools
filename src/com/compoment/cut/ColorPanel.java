package com.compoment.cut;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.compoment.workflow.CompomentDialog2;


/**
 * 获得截取图片的色值
 * */
public class ColorPanel extends JPanel {
	BufferedImage image = null;
	int x1, y1, x2, y2;

	JFrame frame;
	private static Dimension theSize;

	/**
	 * 获得截取图片的色值
	 * */
	public ColorPanel(JFrame mframe, Image img, final CompomentDialog2 dialog) {
		super();
		this.frame = mframe;
		int width = img.getWidth(mframe);
		int height = img.getHeight(mframe);
		theSize = new Dimension(width, height);

		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				x1 = e.getX();
				y1 = e.getY();

				int width = image.getWidth();
				int height = image.getHeight();
				if (x1 > 0 && x1 < width && y1 > 0 && y1 < height) {
					int rgb[] = getrgb(x1, y1);
					String rgb16 = getColorInHexFromRGB(rgb[0], rgb[1], rgb[2]);

					if (e.isMetaDown()) {// 鼠标右键单击

						dialog.setBgRgb(rgb16);

					} else {// 左键单击

						dialog.setRgb(rgb16);

					}

				} else {
					JOptionPane.showMessageDialog(frame, "取色值点超出图片区域，请重新取色值",
							"", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// JOptionPane.showMessageDialog(ColorValue.this,
				// rgb16, "图片已保存",
				// JOptionPane.INFORMATION_MESSAGE);

			}

			public void mouseReleased(MouseEvent e) {

			}
		});

		image = toBufferedImage(img);
		image = zoomInImage(image, 6);

	}

	public Image getImage() {
		return image;
	}

	public int[] getrgb(int x, int y) {
		int rgb[] = new int[3];
		int pixel = image.getRGB(x, y);

		rgb[0] = (pixel & 0xff0000) >> 16;
		rgb[1] = (pixel & 0xff00) >> 8;
		rgb[2] = (pixel & 0xff);

		return rgb;
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
		// g.setColor(Color.blue);
		// g.drawRect(x, y, w, h);
	}

	public static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();

		// Determine if the image has transparent pixels; for this method's
		// implementation, see e661 Determining If an Image Has Transparent
		// Pixels
		// boolean hasAlpha = hasAlpha(image);

		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			/*
			 * if (hasAlpha) { transparency = Transparency.BITMASK; }
			 */

			// Create the buffered image
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null),
					image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}

		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			// int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
			/*
			 * if (hasAlpha) { type = BufferedImage.TYPE_INT_ARGB; }
			 */
			bimage = new BufferedImage(image.getWidth(null),
					image.getHeight(null), type);
		}

		// Copy image to buffered image
		Graphics g = bimage.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return bimage;
	}

	/**
	 * 通过RGB颜色得到十六进制的颜色
	 * 
	 * @param r
	 *            0-255
	 * @param g
	 *            0-255
	 * @param b
	 *            0-255
	 * @return 255,0,253返回FF00FD
	 */
	public static String getColorInHexFromRGB(int r, int g, int b) {
		return vali(getHexNum(r)) + vali(getHexNum(g)) + vali(getHexNum(b));
	}

	private static String vali(String s) {
		if (s.length() < 2) {
			s = "0" + s;
		}
		return s;
	}

	private static String getHexNum(int num) {
		int result = num / 16;
		int mod = num % 16;
		StringBuilder s = new StringBuilder();
		hexHelp(result, mod, s);
		return s.toString();
	}

	private static void hexHelp(int result, int mod, StringBuilder s) {
		char[] H = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		if (result > 0) {
			hexHelp(result / 16, result % 16, s);
		}
		s.append(H[mod]);
	}

	/**
	 * 
	 * 对图片进行放大
	 * 
	 * @param originalImage
	 *            原始图片
	 * 
	 * @param times
	 *            放大倍数
	 * 
	 * @return
	 */

	public static BufferedImage zoomInImage(BufferedImage originalImage,
			Integer times) {

		int width = originalImage.getWidth() * times;

		int height = originalImage.getHeight() * times;
		theSize = new Dimension(width, height);
		
		BufferedImage newImage = new BufferedImage(width, height,
				originalImage.getType());

		Graphics g = newImage.getGraphics();

		g.drawImage(originalImage, 0, 0, width, height, null);

		g.dispose();

		return newImage;

	}

	public Dimension getPreferredSize() {
		return this.theSize;
	}

}