package com.dlw.weChat.model;

/**
 * 复杂按钮
 * @author diaoliwei
 * @date 2016-2-25
 *
 */
public class ComplexButton extends Button{

	private Button[] sub_button;
	
	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
	
}
