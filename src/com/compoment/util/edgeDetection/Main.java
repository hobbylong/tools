package com.compoment.util.edgeDetection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Main {
	public static void main(String[] args) throws IOException {
		File file = new File("C.png");
		BufferedImage bufferedImage = ImageIO.read(file);
		double[][] greyImage = Grey.toGrey(bufferedImage);
	    Draw.drawDouble("grey.jpg", greyImage);
		double[][] gaussianImage = Gaussian.gaussianFilter(greyImage);
		Draw.drawDouble("gaussian.jpg", gaussianImage);
		Gradient.calcGradient(greyImage);
		Draw.drawDouble("result.jpg", Gradient.result);
	}
}
