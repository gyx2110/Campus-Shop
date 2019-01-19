package com.cumt.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/***
 * 图片处理类 处理缩略图，删除目录，给出地址传送图片
 *
 * @author draymonder
 *
 */
public class ImageUtil {
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private final static Random r = new Random();

	/**
	 * 处理缩略图
	 *
	 * @param thumbnail
	 *            Spring自带的文件处理对象
	 * @param targetAddr
	 *            图片存储相对路径
	 * @return
	 */
	public static String genarateThumbnail(MultipartFile thumbnail, String targetAddr) {
		// 文件名
		String realFileName = getRandomFileName();
		// 拓展名
		String extension = getFileExtension(thumbnail);
		// 创建目录
		makeDirPath(targetAddr);
		// 相对路径
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getInputStream()).size(200, 200).outputQuality(0.8).toFile(dest);
		} catch (Exception e) {
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		return relativeAddr;
	}

	/**
	 * 处理商铺分类图
	 *
	 * @param thumbnail
	 *            Spring自带的文件处理对象
	 * @param targetAddr
	 *            图片存储路径
	 * @return
	 */
	public static String generateShopCategoryImg(MultipartFile thumbnail, String targetAddr) {
		// 获取随机文件名，防止文件重名
		String realFileName = getRandomFileName();
		// 获取文件扩展名
		String extension = getFileExtension(thumbnail);
		// 在文件夹不存在时创建
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getInputStream()).size(50, 50).outputQuality(0.9f).toFile(dest);
		} catch (Exception e) {
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		return relativeAddr;
	}

	/**
	 * 处理商品缩略图
	 *
	 * @param thumbnail
	 *            Spring自带的文件处理对象
	 * @param targetAddr
	 *            图片存储路径
	 * @return
	 */
	public static String generateNormalImg(MultipartFile thumbnail, String targetAddr) {
		// 获取随机文件名，防止文件重名
		String realFileName = getRandomFileName();
		// 获取文件扩展名
		String extension = getFileExtension(thumbnail);
		// 在文件夹不存在时创建
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getInputStream()).size(337, 640).outputQuality(0.9f).toFile(dest);
		} catch (Exception e) {
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		return relativeAddr;
	}

	/**
	 * 创建目标路径所涉及到的目录
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取文件拓展名
	 *
	 * @param targetAddr
	 */
	private static String getFileExtension(MultipartFile cFile) {
		String originalFileName = cFile.getOriginalFilename();
		return originalFileName.substring(originalFileName.lastIndexOf("."));
	}

	/**
	 * 获取随机文件名
	 *
	 * @return String 文件随机名
	 */
	private static String getRandomFileName() {
		int randNum = r.nextInt(89999) + 10000;
		String nowTimestr = sdf.format(new Date());
		return nowTimestr + randNum;
	}

	/**
	 * 删除文件或目录下的文件
	 *
	 * @param storePath：文件路径或者目录路径
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
		// 存在
		if (fileOrPath.exists()) {
			// 如果是目录
			if (fileOrPath.isDirectory()) {
				File[] files = fileOrPath.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			// 删除文件或文件夹
			fileOrPath.delete();
		}
	}

	/**
	 * filePath to MultipartFile
	 *
	 * @param filePath
	 * @throws IOException
	 */
	public static MultipartFile path2MultipartFile(String filePath) throws IOException {
		File file = new File(filePath);
		FileItem fileItem = new DiskFileItem("mainFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
		try {
			InputStream input = new FileInputStream(file);
			OutputStream os = fileItem.getOutputStream();
			IOUtils.copy(input, os); // Or faster.. // IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
		} catch (IOException ex) { // do something.
		}
		MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
		return multipartFile;
	}

}
