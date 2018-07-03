package com.compoment.addfunction.android;

public class FileBean {
	public String name;
	public String type;
	public String sourcePath;
	public String destinationPath;

	public FileBean(String sourcePath, String destinationPath, String name,
			String type) {
		this.sourcePath = sourcePath;
		if (destinationPath == null) {
			this.destinationPath = sourcePath;
		} else {
			this.destinationPath = destinationPath;
		}
		this.name = name;
		this.type = type;
	}
}
