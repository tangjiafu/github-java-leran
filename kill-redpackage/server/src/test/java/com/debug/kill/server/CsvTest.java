package com.debug.kill.server;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CsvTest {
	public static void main(String[] args) throws IOException{
		System.out.println("=====读取csv文件=====");
		String path = "/home/laglangyue/AAgit/vue/spring-kill/kill-redpackage/actdoc/data.csv";
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
		BufferedWriter bufferedWriter = new BufferedWriter(writer, 1024);
		for(int i = 0; i < 1024; i++){
			bufferedWriter.write(String.valueOf(i + 10000));
			bufferedWriter.write(",");
			bufferedWriter.newLine();
			bufferedWriter.flush();
		}
		bufferedWriter.close();
	}
}
