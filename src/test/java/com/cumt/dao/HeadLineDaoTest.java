package com.cumt.dao;

import java.io.IOException;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cumt.entity.HeadLine;
import com.cumt.util.ImageUtil;
import com.cumt.util.PathUtil;

public class HeadLineDaoTest extends BaseTest {

	@Autowired
	private HeadLineDao headLineDao;

	@Ignore
	@Test
	public void testInsertHeadLine() {
		HeadLine headLine = new HeadLine(null, "头条展示", "111", "111", 0, 1, new Date(), new Date());
		int effectedNum = headLineDao.insertHeadLine(headLine);
		System.out.println(effectedNum);
	}

	@Ignore
	@Test
	public void testUpdateHeadLine() {
		HeadLine headLine = new HeadLine(1L, null, "", "", null, 1, null, new Date());
		int effectedNum = headLineDao.updateHeadLine(headLine);
		System.out.println(effectedNum);
	}

	@Test
	public void testqueryHeadLineById() {
		// long Id = 1;
		// // System.out.println(headLineDao.queryHeadLineById(Id).getLineName());
		// HeadLine headLine = new HeadLine();
		// headLine.setLineId(Id);
		// // headLine.setEnableStatus(7.);
		// System.out.println(headLineDao.queryHeadLineList(headLine).size());
		long Id = 3;
		String url = "d:\\image\\10.jpg";
		HeadLine headLine = new HeadLine();
		headLine.setLineImg(transImgUrl(url));
		headLine.setLineId(Id);
		int num = headLineDao.updateHeadLine(headLine);
		System.out.println(num);
	}
	public String transImgUrl(String url) {
		String dest = PathUtil.getHeadLineImagePath();
		String imgUrl = null;
		try {
			imgUrl = ImageUtil.generateNormalImg(ImageUtil.path2MultipartFile(url), dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imgUrl;
	}
}
