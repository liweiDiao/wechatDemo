package com.dlw.weChat.utils;

import com.dlw.weChat.model.AccessToken;
import com.dlw.weChat.model.Button;
import com.dlw.weChat.model.CommonButton;
import com.dlw.weChat.model.ComplexButton;
import com.dlw.weChat.model.Menu;
import com.dlw.common.utils.WechatUtil;

/**
 * 创建菜单
 * @author diaoliwei
 *
 */
public class MenuManager {

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "";
		// 第三方用户唯一凭证密钥
		String appSecret = "";
		// 调用接口获取access_token
		AccessToken at = WechatUtil.getAccessToken(appId, appSecret);
		if (null != at) {
			// 调用接口创建菜单
			int result = WechatUtil.createMenu(getMenu(), at.getToken());
			// 判断菜单创建结果
			if (0 == result) {
				System.out.println("菜单创建成功！");
			} else {
				System.out.println("菜单创建失败，错误码：" + result);
			}
		}
	}

	public static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("天气预报");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn13 = new CommonButton();
		btn13.setName("周边搜索");
		btn13.setType("click");
		btn13.setKey("13");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("生活助手");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("休闲驿站");
		/*mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24,
				btn25 });*/
		
		CommonButton btn12 = new CommonButton();
		btn12.setName("进度查询");
		btn12.setType("click");
		btn12.setKey("12");

		/**
		 * 这是公众号目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, btn12 });
		return menu;
	}

}
