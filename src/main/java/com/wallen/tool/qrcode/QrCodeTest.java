package com.wallen.tool.qrcode;

/**
 * 生成二维码方法（插入个性logo）
 *
 * @author Wallen
 * 2020/3/10 14:26
 */
public class QrCodeTest {
	public static void main(String[] args) throws Exception {
		// 存放在二维码中的内容
		String text = "https://www.baidu.com/";
		// 嵌入二维码的logo路径
		String imgPath = "C:\\Users\\Wallen\\Pictures\\002.png";
		// 生成的二维码的路径及名称
		String destPath = "C:\\Users\\Wallen\\Pictures\\code.jpg";
		//生成二维码
		QRCodeUtil.encode(text, imgPath, destPath, true);
		// 解析二维码
		String str = QRCodeUtil.decode(destPath);
		// 打印出解析出的内容:text
		System.out.println(str);
	}
}
