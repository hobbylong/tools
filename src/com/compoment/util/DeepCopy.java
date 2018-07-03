package com.compoment.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 深度拷贝对象
 */
public class DeepCopy {

	
	/**
     * 深层拷贝对象
     * 
     * @param src
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
   
    public static List deepCopy(List src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
 
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        List dest = (List) in.readObject();
        return dest;
    }
    
    
	// 深拷贝2:序列化|反序列化方法
	public static List copyBySerialize(List src) throws IOException,
			ClassNotFoundException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);

		ByteArrayInputStream byteIn = new ByteArrayInputStream(
				byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		List dest = (List) in.readObject();
		return dest;
	}

	// 深拷贝1：递归方法
	public static void copy(List src, List dest) {
		for (int i = 0; i < src.size(); i++) {
			Object obj = src.get(i);
			if (obj instanceof List) {
				dest.add(new ArrayList());
				copy((List) obj, (List) ((List) dest).get(i));
			} else {
				dest.add(obj);
			}
		}

	}

}
