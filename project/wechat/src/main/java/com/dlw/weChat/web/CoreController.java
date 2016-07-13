package com.dlw.weChat.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dlw.weChat.model.AccessToken;
import com.dlw.common.utils.ValidationUtil;
import com.dlw.common.utils.WechatUtil;
import com.dlw.weChat.service.CoreService;

/**
 * wechat核心服务
 * @author diaoliwei
 * @date 2016-2-23
 * @Copyright dlw
 */
@Controller
@RequestMapping("/core")
public class CoreController {

	@Autowired
	private CoreService coreService;

	/**
	 * 微信验证
	 * @author diaoliwei
	 * @date 2016-2-23
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public void coreIndexGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");//随机数
		String echostr = request.getParameter("echostr"); //随机字符串
		PrintWriter out = response.getWriter();
		if (ValidationUtil.checkSignature(signature, timestamp, nonce)) {
			System.out.println("接入成功");
			
			
			String appId = "";//TODO AppID
			String appSecret = "";//TODO AppSecret
			// 调用接口获取access_token
			AccessToken at = WechatUtil.getAccessToken(appId, appSecret);
			/*if (null != at) {
				System.out.println("========调用接口创建菜单======");
				// 调用接口创建菜单
				int result = WechatUtil.createMenu(MenuManager.getMenu(), at.getToken());
				// 判断菜单创建结果
				if (0 == result) {
					System.out.println("菜单创建成功！");
				} else {
					System.out.println("菜单创建失败，错误码：" + result);
				}
			}*/
			
			out.print(echostr);
		}else{
			System.out.println("接入失败");
		}
		out.close();
		out = null;
	}

	/**
	 * 处理微信服务器发来的消息
	 * @author diaoliwei
	 * @date 2016-2-24
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws DocumentException
	 */
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public void coreIndexPost(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset-utf-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("微信请求~~~~~");
		// 调用核心业务类接收消息、处理消息
        String respMessage = coreService.processRequest(request);
		//响应消息
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
	}
	 
}
