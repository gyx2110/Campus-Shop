package com.cumt.web.wechat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cumt.util.wechat.SignUtil;
/***
 * Wechat与本应用校验
 * @author draymonder
 *
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
	private static Logger log = LoggerFactory.getLogger(WechatController.class);

	@RequestMapping(method=RequestMethod.GET) 
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		log.debug("weixin get...");
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		PrintWriter out = null; 
		try {
			out = resp.getWriter();
			if(SignUtil.checkSignature(signature, timestamp, nonce)) {
				log.debug("wexin get success");
				out.print(echostr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null)
				out.close();
		}
	}
}
