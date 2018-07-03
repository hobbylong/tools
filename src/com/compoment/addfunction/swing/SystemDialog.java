package com.compoment.addfunction.swing;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.compoment.cut.CutCompomentsTypeImg;

public class SystemDialog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	  
		 String s = JOptionPane.showInputDialog(null,"msg","title",  JOptionPane.INFORMATION_MESSAGE);
	
		
	}
	
	public void msg()
	{
//		  1.1 showMessageDialog
//	       显示消息消息对话框，可以设置消息内容、标题、消息的样式、图标
		String m="";
		m+="JOptionPane.showMessageDialog(frame,\"msg\", \"title\", JOptionPane.INFORMATION_MESSAGE);\n";
		System.out.println(m);
	}
	
	public void  inputText()
	{
//		 1.2 showInputDialog      
//	       显示输入对话框，可以设置消息内容、标题、消息的样式、图标、可选择的输入值、初始输入值。
//	        当“可选择的输入值”设置存在时显示下来列表，否则显示JText输入框。
		String m="";
		m+=" String s = JOptionPane.showInputDialog(null,\"msg\",\"title\",  JOptionPane.INFORMATION_MESSAGE);\n";
		System.out.println(m);
		
	}
	
	public void  inputListSelect()
	{
//		 1.2 showInputDialog      
//	       显示输入对话框，可以设置消息内容、标题、消息的样式、图标、可选择的输入值、初始输入值。
//	        当“可选择的输入值”设置存在时显示下来列表，否则显示JText输入框。
		String m="";
		m+="Object[] possibilities ={\"\",\"\",\"\"};\n";
		m+="String s = (String)JOptionPane.showInputDialog(null,\"msg\",\"title\",JOptionPane.PLAIN_MESSAGE,null,possibilities,possibilities[0]);\n";
		
		System.out.println(m);
		
	}
	
	
	
	public void confirm()
	{
//		  1.3 showConfirmDialog    
//	       显示确认对话框, 可以设置消息内容、标题、option样式、消息的样式、图标
		String m="";
		m+=" int option=JOptionPane.showConfirmDialog(null,\"msg\",\"title\",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);\n";
	    m+=" if(option==JOptionPane.YES_OPTION)\n";
	    m+=" {}\n";
	    m+=" else if(option==JOptionPane.NO_OPTION)\n";
	    m+=" {}\n";
	 	System.out.println(m);
	}
	
	
	public void option()
	{
		String m="";
		m+="Object[] options = { \"添加\", \"恢复\" };\n";
		m+="int response = JOptionPane.showOptionDialog(null, \"msg\", \"title\",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);\n";
		m+="if (response == 0) {\n";
		m+="}else if(response == 1)\n";
		m+="{\n";	
		m+="}\n";
	}

}
