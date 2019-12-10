package com.wallen.tool.util;

import java.io.*;

/**
 * html转pdf
 * html路径，可以是硬盘上的路径，也可以是网络路径
 * 需要先安装wkhtmltopdf
 * 原文链接：
 *		https://cloud.tencent.com/developer/article/1501876
 *		http://blog.csdn.net/ouyhong123/article/details/26401967
 *
 * @author Wallen
 * 2019/12/10 18:06
 */
public class HtmlToPdf {
	/**
	 * wkhtmltopdf在系统中的路径
	 */
	private static final String WKHTMLTOPDF_PATH = "D:\\Program Files\\wkhtmltopdf\\bin\\wkhtmltopdf.exe";

	public static void main(String[] args) {
		convert("https://www.baidu.com/", "E:\\test.pdf");
	}

	/**
	 * html转pdf
	 *
	 * @param srcPath  html路径，可以是硬盘上的路径，也可以是网络路径
	 * @param destPath pdf保存路径
	 * @return 转换成功返回true
	 */
	public static boolean convert(String srcPath, String destPath) {
		File file = new File(destPath);
		File parent = file.getParentFile();
		//如果pdf保存路径不存在，则创建路径
		if (!parent.exists()) {
			parent.mkdirs();
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(WKHTMLTOPDF_PATH).append(" ").append(srcPath).append(" ").append(destPath);

		boolean result = true;
		try {
			Process process = Runtime.getRuntime().exec(stringBuilder.toString());
			HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(process.getErrorStream());
			HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(process.getInputStream());
			error.start();
			output.start();
			process.waitFor();
			process.destroy();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
}

class HtmlToPdfInterceptor extends Thread {
	private InputStream is;

	public HtmlToPdfInterceptor(InputStream is) {
		this.is = is;
	}

	@Override
	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				//打印到控制台
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
