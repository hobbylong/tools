package com.compoment.cut;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * 显示图片，提供截取功能
 * */
public class CutCompomentsTypeImg extends JPanel {
	//BufferedImage image = null;
	int x1, y1, x2, y2;
	List<CompomentBean> beans;
	CutCompomentsTypeImgCallBack frame;
	/**
	 * 显示图片，提供截取功能
	 * */
	public CutCompomentsTypeImg(final CutCompomentsTypeImgCallBack frame,List<CompomentBean> beans) {
		super();
		this.frame=frame;
		this.beans=beans;
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
				frame.cutCompomentsTypeImgCallBack(x,y,w,h);
				
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
				CutCompomentsTypeImg.this.repaint();
			}
		});

	}


  boolean isFirst=true;
	public void paint(Graphics g) {
		super.paint(g);
		//g.drawImage(image, 0, 0, this);
		
		
			
	    List<CompomentBean> temp=new ArrayList();
		for(CompomentBean bean:beans)
		{
			if(bean.type.contains("Layout"))
			{
				temp.add(bean);
				
				if(!isFirst)
				{
					g.setColor(Color.blue);
					g.drawRect(bean.x, bean.y, bean.w, bean.h);
				}
			}else
			{
			g.setColor(Color.black);
			g.drawRect(bean.x, bean.y, bean.w, bean.h);
			}
		}
		if(isFirst)
		{
		for(CompomentBean bean:temp)
		{
			beans.remove(bean);
		}
		isFirst=false;
		}
	
		
		if (x1 == 0 && y1 == 0 && x2 == 0 && y2 == 0)
			return;
		
		int x = x1 < x2 ? x1 : x2;
		int y = y1 < y2 ? y1 : y2;
		int w = (x1 > x2 ? x1 : x2) - x;
		int h = (y1 > y2 ? y1 : y2) - y;
		g.setColor(Color.blue);
		g.drawRect(x, y, w, h);
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

	public static void main(String args[]) {

	}
	
	public interface CutCompomentsTypeImgCallBack
	{
		public void cutCompomentsTypeImgCallBack(final int x, final int y,
				final int w, final int h);
	}
}