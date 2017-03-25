package com.jhs.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jfinal.kit.Ret;

import sun.misc.BASE64Decoder;

/**
 * 检测舌像
 * 
 * @author tianr
 *
 */
public class TongueUtil {

	private static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

	public static Ret check(String data) {
		Ret ret = new Ret();
		String base64String = data.substring(data.indexOf(",") + 1);
		int flag;
		String color;

		try {
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(decoder.decodeBuffer(base64String)));

			// System.out.println(writeJPEGImage(createBufferedImage(width,height
			// ,inPixels),"D:\\tj.png"));

			int width = image.getWidth();
			int height = image.getHeight();
			System.out.println("裁剪舌像宽度:" + width);
			System.out.println("裁剪舌像高度:" + height);

			double centerRedSum = 0, centerGreenSum = 0, centerBlueSum = 0, centerRedAvg = 0, centerGreenAvg = 0, centerBlueAvg = 0;

			// 将舌像转换成RGB矩阵
			int[] inPixels = new int[width * height];
			// inPixels = getRGB(image, 0, 0, width, height, inPixels);
			inPixels = image.getRGB(0, 0, width, height, inPixels, 0, width);
			// 输出原图
			//writePNGImage(image, "/usr/local/tomcat8/webapps/jhResc/image.png");
			// System.out.println(writeJPEGImage(createBufferedImage(width,height
			// ,inPixels),"D:\\tj.png"));
			// 计算舌像中间R,G权值

			double centerR, centerG;
			// for (int row = (centerY + (height * 1) / 4); row < (centerY +
			// (height * 1) / 2); row++) {
			// for (int col = (centerY - width / 5); col < (centerX + width /
			// 5); col++) {
			// centerRedSum += (image.getRGB(col, row) >> 16) & 0xff;
			// centerGreenSum += (image.getRGB(col, row) >> 8) & 0xff;
			// centerBlueSum += (image.getRGB(col, row)) & 0xff;
			// }
			// }
			// int[] centerPixels = new int[((int)(width*0.2)) * ((int)(height
			// *0.2))];
			for (int row = (int) (height * 0.6); row <= (int) (height * 0.8); row++) {
				for (int col = (int) (width * 0.4); col <= (int) (width * 0.6); col++) {
					// centerRedSum += (image.getRGB(col, row) >> 16) & 0xff;
					// centerGreenSum += (image.getRGB(col, row) >> 8) & 0xff;
					// centerBlueSum += (image.getRGB(col, row)) & 0xff;
					centerRedSum += (inPixels[row * width + col] >> 16) & 0xff;
					centerGreenSum += (inPixels[row * width + col] >> 8) & 0xff;
					centerBlueSum += (inPixels[row * width + col]) & 0xff;
				}
			}
			centerRedAvg = (double) ((centerRedSum * 1.0) / (((int) (height * 0.2)) * ((int) (width * 0.2))));
			centerGreenAvg = (double) ((centerGreenSum * 1.0) / (((int) (height * 0.2)) * ((int) (width * 0.2))));
			centerBlueAvg = (double) ((centerBlueSum * 1.0) / (((int) (height * 0.2)) * ((int) (width * 0.2))));

			System.out.println("舌像中心平均值：R=" + centerRedAvg + ",G=" + centerGreenAvg + ",B=" + centerBlueAvg);
			// 输出中间图像
			//BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
			//bi.setRGB((int) (width * 0.4), (int) (height * 0.6), (int) (width * 0.2), (int) (height * 0.2), inPixels, ((int) (height * 0.6) - 1) * width + ((int) (width * 0.4)), width);
			//writePNGImage(bi, "D:\\center.png");
			//writePNGImage(bi, "/usr/local/tomcat8/webapps/jhResc/center.png");
			// 计算舌像左边R,G权值
			double leftRedSum = 0, leftGreenSum = 0, leftBlueSum = 0, leftRedAvg = 0, leftGreenAvg = 0, leftBlueAvg = 0;
			double leftR, leftG;
			// for (int row = 0; row < width / 5; row++) {
			// for (int col = 0; col < height / 5; col++) {
			// leftRedSum += (image.getRGB(col, row) >> 16) & 0xff;
			// leftGreenSum += (image.getRGB(col, row) >> 8) & 0xff;
			// leftBlueSum += (image.getRGB(col, row)) & 0xff;
			// }
			// }
			for (int row = 0; row < height / 12; row++) {
				for (int col = 0; col < width / 12; col++) {
//					leftRedSum += (image.getRGB(col, row) >> 16) & 0xff;
//					leftGreenSum += (image.getRGB(col, row) >> 8) & 0xff;
//					leftBlueSum += (image.getRGB(col, row)) & 0xff;
					leftRedSum += (inPixels[row * width + col] >> 16) & 0xff;
					leftGreenSum += (inPixels[row * width + col] >> 8) & 0xff;
					leftBlueSum += (inPixels[row * width + col]) & 0xff;
				}
			}
			leftRedAvg = (double) ((leftRedSum * 1.0) / ((int) (height / 12) * (int) (width / 12)));
			leftGreenAvg = (double) ((leftGreenSum * 1.0) / ((int) (height / 12) * (int) (width / 12)));
			leftBlueAvg = (double) ((leftBlueSum * 1.0) / ((int) (height / 12) * (int) (width / 12)));
			leftR = leftRedAvg / ((leftRedAvg + leftGreenAvg + leftBlueAvg) * 1.0);
			leftG = leftGreenAvg / ((leftRedAvg + leftGreenAvg + leftBlueAvg) * 1.0);
			System.out.println("舌像左边平均值：R=" + leftRedAvg + ",G=" + leftGreenAvg + ",B=" + leftBlueAvg);
			System.out.println("舌像左边权值：R=" + leftR + ",G=" + leftG);
			System.out.println("---------------------------------------------------");
			// 输出左上图像
			//BufferedImage biLeft = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
			//biLeft.setRGB(0, 0, (int) (int) (width / 12), (int) (height / 12), inPixels, 0, width);
			//writePNGImage(biLeft, "/usr/local/tomcat8/webapps/jhResc/left.png");
			 
			// 计算舌像右边R,G权值
			double rightRedSum = 0, rightGreenSum = 0, rightBlueSum = 0, rightRedAvg = 0, rightGreenAvg = 0, rightBlueAvg = 0;
			double rightR, rightG;
			// for (int row = 0; row < height / 5; row++) {
			// for (int col = (width * 4) / 5; col < width; col++) {
			// rightRedSum += (image.getRGB(col, row) >> 16) & 0xff;
			// rightGreenSum += (image.getRGB(col, row) >> 8) & 0xff;
			// rightBlueSum += (image.getRGB(col, row)) & 0xff;
			// }
			// }
			for (int row = 0; row < (height / 12); row++) {
				for (int col = width - (width / 12); col < width; col++) {
//					rightRedSum += (image.getRGB(col, row) >> 16) & 0xff;
//					rightGreenSum += (image.getRGB(col, row) >> 8) & 0xff;
//					rightBlueSum += (image.getRGB(col, row)) & 0xff;
					rightRedSum += (inPixels[row * width + col] >> 16) & 0xff;
					rightGreenSum += (inPixels[row * width + col] >> 8) & 0xff;
					rightBlueSum += (inPixels[row * width + col]) & 0xff;
				}
			}
			rightRedAvg = (double) ((rightRedSum * 1.0) / ((int) (height / 12) * (int) (width / 12)));
			rightGreenAvg = (double) ((rightGreenSum * 1.0) / ((int) (height / 12) * (int) (width / 12)));
			rightBlueAvg = (double) ((rightBlueSum * 1.0) / ((int) (height / 12) * (int) (width / 12)));
			rightR = rightRedAvg / ((rightRedAvg + rightGreenAvg + rightBlueAvg) * 1.0);
			rightG = rightGreenAvg / ((rightRedAvg + rightGreenAvg + rightBlueAvg) * 1.0);
			System.out.println("舌像右边平均值：R=" + rightRedAvg + ",G=" + rightGreenAvg + ",B=" + rightBlueAvg);
			System.out.println("舌像右边权值：R=" + rightR + ",G=" + rightG);
			// 输出右上图像
			BufferedImage biRight = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
			biRight.setRGB(width - (int) (width / 12), 0, (int) (width / 12), (int) (height / 12), inPixels,  (width - (int) (width / 12)), width);
			//writePNGImage(biRight, "/usr/local/tomcat8/webapps/jhResc/right.png");
			// 计算舌像右边R,G权值
			double downRedSum = 0, downGreenSum = 0, downBlueSum = 0, downRedAvg = 0, downGreenAvg = 0, downBlueAvg = 0;
			double downR, downG;
			for (int row = height - (int) (height / 40); row < height; row++) {
				for (int col = (int) (width * 0.4); col < (int) (width * 0.6); col++) {
//					downRedSum += (image.getRGB(col, row) >> 16) & 0xff;
//					downGreenSum += (image.getRGB(col, row) >> 8) & 0xff;
//					downBlueSum += (image.getRGB(col, row)) & 0xff;
					downRedSum += (inPixels[row * width + col] >> 16) & 0xff;
					downGreenSum += (inPixels[row * width + col] >> 8) & 0xff;
					downBlueSum += (inPixels[row * width + col]) & 0xff;
				}
			}
			downRedAvg = (double) ((downRedSum * 1.0) / (((int) (height / 40)) * ((int) (width * 0.2))));
			downGreenAvg = (double) ((downGreenSum * 1.0) / (((int) (height / 40)) * ((int) (width * 0.2))));
			downBlueAvg = (double) ((downBlueSum * 1.0) / (((int) (height / 40)) * ((int) (width * 0.2))));
			downR = downRedAvg / ((downRedAvg + downGreenAvg + downBlueAvg) * 1.0);
			downG = downGreenAvg / ((downRedAvg + downGreenAvg + downBlueAvg) * 1.0);
			System.out.println("舌像下边边总值：R=" + downRedSum + ",G=" + downGreenSum + ",B=" + downBlueSum);
			System.out.println("舌像下边边平均值：R=" + downRedAvg + ",G=" + downGreenAvg + ",B=" + downBlueAvg);
			System.out.println("舌像下边权值：R=" + downR + ",G=" + downG);
			// 输出下图像
			//BufferedImage biDown = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
			//biDown.setRGB((int) (width * 0.4), height - (int) (height / 40), (int) (width * 0.2), (int) (height / 40), inPixels, (((height - (int) (height / 40) - 1) * width) + (int) (width * 0.4)),width);
			//writePNGImage(biDown, "/usr/local/tomcat8/webapps/jhResc/down.png");

			double rl = xiangsi(leftRedAvg, leftGreenAvg, leftBlueAvg, rightRedAvg, rightGreenAvg, rightBlueAvg);
			System.out.println("左右相差比例均值：" + rl);
			double lc = xiangsi(leftRedAvg, leftGreenAvg, leftBlueAvg, centerRedAvg, centerGreenAvg, centerBlueAvg);
			System.out.println("左中相差比例均值：" + lc);
			double rc = xiangsi(rightRedAvg, rightGreenAvg, rightBlueAvg, centerRedAvg, centerGreenAvg, centerBlueAvg);
			System.out.println("右中相差比例均值：" + rc);
			double dc = xiangsi(downRedAvg, downGreenAvg, downBlueAvg, centerRedAvg, centerGreenAvg, centerBlueAvg);
			System.out.println("中下相差比例均值：" + dc);
			
			if (lc <1 && rc <1 && dc <1) {
				for (int row = (int) (height * 0.6); row <= (int) (height * 0.8); row++) {
					for (int col = (int) (width * 0.4); col <= (int) (width * 0.6); col++) {
//						centerRedSum += (image.getRGB(col, row) >> 16) & 0xff;
//						centerGreenSum += (image.getRGB(col, row) >> 8) & 0xff;
//						centerBlueSum += (image.getRGB(col, row)) & 0xff;
						centerRedSum += (inPixels[row * width + col] >> 16) & 0xff;
						centerGreenSum += (inPixels[row * width + col] >> 8) & 0xff;
						centerBlueSum += (inPixels[row * width + col]) & 0xff;
					}
				}
				centerRedAvg = (double) ((centerRedSum * 1.0) / (((int) (height * 0.2)) * ((int) (width * 0.2))));
				centerGreenAvg = (double) ((centerGreenSum * 1.0) / (((int) (height * 0.2)) * ((int) (width * 0.2))));
				centerBlueAvg = (double) ((centerBlueSum * 1.0) / (((int) (height * 0.2)) * ((int) (width * 0.2))));

				centerR = centerRedAvg / ((centerRedAvg + centerGreenAvg + centerBlueAvg) * 1.0);
				centerG = centerGreenAvg / ((centerRedAvg + centerGreenAvg + centerBlueAvg) * 1.0);
				System.out.println("centerR:" + centerR + ",centerG:" + centerG);
				if (centerR >= 0.4 && centerG <= 0.34) {
					flag = 1;
					color = "灰黑";
				} else if (centerR >= 0.4 & centerG > 0.34) {
					flag = 1;
					color = "黄苔";
				} else if (centerR >= 0.37 & centerR < 0.4 & centerG >= 0.28) {
					flag = 1;
					color = "淡黄";
				} else if (centerR >= 0.34 & centerR < 0.37 & centerG > 0.28 & centerG <= 0.34) {
					flag = 1;
					color = "白苔";
				} else if (centerR > 0.35 & centerR <= 0.36 & centerG >= 0.30 & centerG <= 0.31) {
					flag = 1;
					color = "薄白";
				} else if (centerR < 0.39 && centerR < 0.46 && centerG < 0.3) {
					flag = 1;
					color = "无苔";
				} else {
					flag = 2;
					color = "";
				}
			} else if(dc >= 1){
				flag = 3;
				color = "";
			}
			else{
				flag = 0;
				color = "";
			}
			
		} catch (IOException e) {
			flag = 0;
			color = "";
			e.printStackTrace();
		}
	
		ret.set("state", flag);
		ret.set("color", color);
		System.out.println("color:" + color);
		return ret;
	}

	public static int[] getRGB(BufferedImage image, int x, int y, int width, int height, int[] pixels) {
		int type = image.getType();
		if (type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB) {
			return (int[]) image.getRaster().getDataElements(x, y, width, height, pixels);
		} else {
			return image.getRGB(x, y, width, height, pixels, 0, width);
		}
	}

	public static BufferedImage setRGB(BufferedImage image, int x, int y, int width, int height, int[] pixels) {
		int type = image.getType();
		if (type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB) {
			image.getRaster().setDataElements(x, y, width, height, pixels);
		} else {
			image.setRGB(x, y, width, height, pixels, 0, width);
		}
		return image;
	}

	public static double xiangsi(double r1, double g1, double b1, double r2, double g2, double b2) {
		double rRate;
		if (r1 <= r2) {
			rRate = (r1) / r2;
		} else {
			rRate = (r2) / r1;
		}

		double gRate;
		if (g1 <= g2) {
			gRate = (g1) / g2;
		} else {
			gRate = (g2) / g1;
		}
		double bRate;
		if (b1 <= b2) {
			bRate = (b1) / b2;
		} else {
			bRate = (b2) / b1;
		}
		if(rRate < 0.9 || gRate < 0.9 || bRate < 0.9)
		{
			return 0;
		}
		else{
			return 1;
		}
		//return ((rRate) + (gRate) + (bRate)) / 3.0;
	}

	public static boolean writePNGImage(RenderedImage im, String fileName) {
		return writeImage(im, "PNG", fileName);
	}

	public static boolean writeImage(RenderedImage im, String formatName, String fileName) {
		boolean result = false;
		try {
			result = ImageIO.write(im, formatName, new File(fileName));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return result;
	}

	public static BufferedImage createBufferedImage(int width, int height, int[] pixels) {

		ColorModel cm = getColorModel();
		SampleModel sm = new SinglePixelPackedSampleModel(DataBuffer.TYPE_BYTE, width, height, pixels);
		DataBuffer db = new DataBufferByte(width * height, 0);
		WritableRaster raster = Raster.createWritableRaster(sm, db, null);
		BufferedImage image = new BufferedImage(cm, raster, false, null);
		return image;
	}

	public static IndexColorModel getColorModel() {
		byte[] r = new byte[256];
		byte[] g = new byte[256];
		byte[] b = new byte[256];

		for (int i = 0; i < 256; i++) {
			r[i] = (byte) i;
			g[i] = (byte) i;
			b[i] = (byte) i;
		}

		return new IndexColorModel(8, 256, r, g, b);
	}
}
