package com.sunyzc.ssh.service;

import java.io.File;

public class ModifyFileName {
	public static void main(String[] args) {
		File[] baseDir = new File("M:/Storage/视频/Java/传智播客/张孝祥2010年贺岁视频：Java高新技术/").listFiles();
		for (File dir : baseDir) {
			if (dir.isDirectory()) {
				String dirName = dir.getName();
				for (File file : dir.listFiles()) {
					if (file.getName().endsWith(".avi")) {
						System.out.println("M:/Storage/视频/Java/传智播客/张孝祥2010年贺岁视频：Java高新技术/" + dirName + ".avi");
						file.renameTo(new File("M:/Storage/视频/Java/传智播客/张孝祥2010年贺岁视频：Java高新技术/" + dirName + ".avi"));
					}
				}
			}
		}
	}
}
