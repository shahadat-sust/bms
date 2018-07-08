package com.bms.common.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.log4j.Logger;

import com.bms.common.Enums.ImageType;

public class ImageUtils {
	private static final Logger logger = Logger.getLogger(ImageUtils.class);
	
	public static void resizeImage(File srcFile, File dstFile, ImageType imageType) throws IOException {
		BufferedImage originalImage = ImageIO.read(srcFile);
		BufferedImage resizeImage = ImageUtils.resizeImage(originalImage, imageType);
		ImageIO.write(resizeImage, ImageUtils.getFormatName(srcFile), dstFile);
	}
	
	public static BufferedImage resizeImage(BufferedImage originalImage, ImageType imageType) {
		int origWidth = originalImage.getWidth();
		int origHeight = originalImage.getHeight();
		int width = imageType.getSize();
		int height = (origHeight * width) / origWidth;
		logger.debug(imageType.getName() + " -> "
				+ "width : " + width + "(" + origWidth + "), "
				+ "height : " + height + "(" + origHeight + ")");
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();	
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		
		return resizedImage;
	}
	
	public static void cropImage(File srcFile, File dstFile, int cropX, int cropY, int cropW, int cropH) throws IOException {
		BufferedImage originalImage = ImageIO.read(srcFile);
		int origWidth = originalImage.getWidth();
		int origHeight = originalImage.getHeight(); 
		int newCropW = cropW == 0 ? origWidth - cropX : (cropX + cropW > origWidth ? origWidth - cropX : cropW);
		int newCropH = cropH == 0 ? origHeight - cropY : (cropY + cropH > origHeight ? origHeight - cropY : cropH);
		logger.debug(ImageType.CROP.getName() + " -> "
				+ "cropX : " + cropX + "(" + cropX + "), "
				+ "cropY : " + cropY + "(" + cropY + "), "
				+ "newCropW : " + newCropW + "(" + cropW + "), "
				+ "newCropH : " + newCropH + "(" + cropH + ")");
		BufferedImage croppedImage = originalImage.getSubimage(cropX, cropY, newCropW, newCropH);
		ImageIO.write(croppedImage, ImageUtils.getFormatName(srcFile), dstFile);
	}
	
	public static String getFormatName(File file) throws IOException {
		ImageInputStream iis = null;
		try {
			iis = ImageIO.createImageInputStream(file);
			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
			if (!iter.hasNext()) {
				throw new RuntimeException("No readers found!");
			}
			ImageReader reader = iter.next();
			logger.debug("Format: " + reader.getFormatName());
			return reader.getFormatName();
		} finally {
			iis.close();
		}
	}
	
}
