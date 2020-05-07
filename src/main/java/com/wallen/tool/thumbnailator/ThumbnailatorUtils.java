package com.wallen.tool.thumbnailator;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * google开源工具Thumbnailator实现图片压缩
 * https://blog.csdn.net/qq_38296054/article/details/76216254
 *
 * @author Wallen
 * 2020/5/7 17:03
 */
public class ThumbnailatorUtils {
	/**
	 * 指定大小进行缩放
	 * 若图片横比width小，高比height小，不变 若图片横比width小，高比height大，高缩小到height，图片比例不变
	 * 若图片横比width大，高比height小，横缩小到width，图片比例不变
	 * 若图片横比width大，高比height大，图片按比例缩小，横为width或高为height
	 *
	 * @param source 输入源（指定路径）
	 * @param output 输出源
	 * @param width  宽
	 * @param height 高
	 */
	public static void imageThumb(String source, String output, int width, int height) {
		try {
			Thumbnails.of(source).size(width, height).toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 指定大小进行缩放
	 *
	 * @param source 输入源（指定文件）
	 * @param output 输出源
	 * @param width  宽
	 * @param height 高
	 */
	public static void imageThumb(File source, String output, int width, int height) {
		try {
			Thumbnails.of(source).size(width, height).toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 按照比例进行缩放
	 *
	 * @param source 输入源（支持String(文件地址) file(文件)）
	 * @param output 输出源
	 * @param scale  比例（大于零时为扩大，小于零时为缩小）
	 */
	public static void imageScale(String source, String output, double scale) {
		try {
			Thumbnails.of(source).scale(scale).toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void imageScale(File source, String output, double scale) {
		try {
			Thumbnails.of(source).scale(scale).toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 不按照比例，指定大小进行缩放
	 *
	 * @param source          输入源
	 * @param output          输出源
	 * @param width           宽
	 * @param height          高
	 * @param keepAspectRatio 默认是按照比例缩放的,值为false 时不按比例缩放
	 */
	public static void imageNoScale(String source, String output, int width, int height, boolean keepAspectRatio) {
		try {
			Thumbnails.of(source).size(width, height).keepAspectRatio(keepAspectRatio).toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void imageNoScale(File source, String output, int width, int height, boolean keepAspectRatio) {
		try {
			Thumbnails.of(source).size(width, height).keepAspectRatio(keepAspectRatio).toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 旋转 ,正数：顺时针 负数：逆时针
	 *
	 * @param source 输入源
	 * @param output 输出源
	 * @param width  宽
	 * @param height 高
	 * @param rotate 角度(旋转 ,正数：顺时针 负数：逆时针)
	 */
	public static void imageRotate(String source, String output, int width, int height, double rotate) {
		try {
			Thumbnails.of(source).size(width, height).rotate(rotate).toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void imageRotate(File source, String output, int width, int height, double rotate) {
		try {
			Thumbnails.of(source).size(width, height).rotate(rotate).toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 水印
	 *
	 * @param source       输入源
	 * @param output       输入源
	 * @param width        宽
	 * @param height       高
	 * @param position     水印位置 Positions.BOTTOM_RIGHT o.5f
	 * @param watermark    水印图片地址
	 * @param transparency 透明度 0.5f
	 * @param quality      图片质量 0.8f
	 */
	public static void imageWatermark(String source, String output, int width, int height, Position position,
									  String watermark, float transparency, float quality) {
		try {
			Thumbnails.of(source).size(width, height)
					.watermark(position, ImageIO.read(new File(watermark)), transparency).outputQuality(quality)
					.toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void imageWatermark(File source, String output, int width, int height, Position position,
									  String watermark, float transparency, float quality) {
		try {
			Thumbnails.of(source).size(width, height)
					.watermark(position, ImageIO.read(new File(watermark)), transparency).outputQuality(quality)
					.toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 裁剪图片
	 *
	 * @param source          输入源
	 * @param output          输出源
	 * @param position        裁剪位置
	 * @param x               裁剪区域x
	 * @param y               裁剪区域y
	 * @param width           宽
	 * @param height          高
	 * @param keepAspectRatio 默认是按照比例缩放的,值为false 时不按比例缩放
	 */
	public static void imageSourceRegion(String source, String output, Position position, int x, int y, int width,
										 int height, boolean keepAspectRatio) {
		try {
			Thumbnails.of(source).sourceRegion(position, x, y).size(width, height).keepAspectRatio(keepAspectRatio)
					.toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void imageSourceRegion(File source, String output, Position position, int x, int y, int width,
										 int height, boolean keepAspectRatio) {
		try {
			Thumbnails.of(source).sourceRegion(position, x, y).size(width, height).keepAspectRatio(keepAspectRatio)
					.toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 按坐标裁剪
	 *
	 * @param source          输入源
	 * @param output          输出源
	 * @param xStart          起始x坐标
	 * @param yStart          起始y坐标
	 * @param xEnd            结束x坐标
	 * @param yEnd            结束y坐标
	 * @param width           宽
	 * @param height          高
	 * @param keepAspectRatio 默认是按照比例缩放的,值为false 时不按比例缩放
	 */
	public static void imageSourceRegion(String source, String output, int xStart, int yStart, int xEnd, int yEnd,
										 int width, int height, boolean keepAspectRatio) {
		try {
			Thumbnails.of(source).sourceRegion(xStart, yStart, xEnd, yEnd).size(width, height)
					.keepAspectRatio(keepAspectRatio).toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void imageSourceRegion(File source, String output, int xStart, int yStart, int xEnd, int yEnd,
										 int width, int height, boolean keepAspectRatio) {
		try {
			Thumbnails.of(source).sourceRegion(xStart, yStart, xEnd, yEnd).size(width, height)
					.keepAspectRatio(keepAspectRatio).toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 转化图像格式
	 *
	 * @param source 输入源
	 * @param output 输出源
	 * @param width  宽
	 * @param height 高
	 * @param format 图片类型，gif、png、jpg
	 */
	public static void imageFormat(String source, String output, int width, int height, String format) {
		try {
			Thumbnails.of(source).size(width, height).outputFormat(format).toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void imageFormat(File source, String output, int width, int height, String format) {
		try {
			Thumbnails.of(source).size(width, height).outputFormat(format).toFile(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出到OutputStream
	 *
	 * @param source 输入源
	 * @param output 输出源
	 * @param width  宽
	 * @param height 高
	 * @return toOutputStream(流对象)
	 */
	public static OutputStream imageOutputStream(String source, String output, int width, int height) {
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(output);
			Thumbnails.of(source).size(width, height).toOutputStream(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputStream;
	}

	public static OutputStream imageOutputStream(File source, String output, int width, int height) {
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(output);
			Thumbnails.of(source).size(width, height).toOutputStream(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputStream;
	}

	/**
	 * 输出到BufferedImage
	 *
	 * @param source 输入源
	 * @param output 输出源
	 * @param width  宽
	 * @param height 高
	 * @param format 图片类型，gif、png、jpg
	 * @return BufferedImage
	 */
	public static BufferedImage imageBufferedImage(String source, String output, int width, int height, String format) {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = Thumbnails.of(source).size(width, height).asBufferedImage();
			ImageIO.write(bufferedImage, format, new File(output));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}

	public static BufferedImage imageBufferedImage(File source, String output, int width, int height, String format) {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = Thumbnails.of(source).size(width, height).asBufferedImage();
			ImageIO.write(bufferedImage, format, new File(output));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}
}
