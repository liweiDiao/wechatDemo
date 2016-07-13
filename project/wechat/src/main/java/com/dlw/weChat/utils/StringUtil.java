package com.dlw.weChat.utils;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * 工具类
 * @author diaoliwei
 * @date 2016-2-25
 * @Copyright dlw
 */
public class StringUtil {
	
	/**
	 * 计算采用utf-8编码方式时字符串所占字节数
	 * @author diaoliwei
	 * @date 2016-2-25
	 * @param context
	 * @return
	 */
	public static int getByteSize(String context){
		int size = 0;
		if(null != context){
			try {
				size = context.getBytes("utf-8").length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return size;
	}
	
	/**
	 * 日期格式yyyy年MM月dd日HH时mm分形式
	 * @创建时间 2015年8月2日下午3:31:01
	 * @param obj
	 * @return
	 * @throws ParseException
	 */
	public static String FormatYmdhByDate(Object obj) {
		DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");// 日期转换
		String strDate = "";
		try {
			if (!StringUtils.isEmpty(obj)) {
				strDate = sdf1.format(obj);
			}
		} catch (Exception e) {
		}

		return strDate;
	}
	
	/**
	 * 判断是否是QQ表情
	 * @author diaoliwei
	 * @date 2016-2-25
	 * @param content
	 * @return
	 */
	public static boolean isQqFace(String content){
		boolean result = false;
		// 判断QQ表情的正则表达式
		//String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		String qqfaceRegex = "(/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>){1,}";
		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss）
	 * @author diaoliwei
	 * @date 2016-2-25
	 * @param createTime
	 * @return
	 */
	public static String formatTime(String createTime){
		long msgCreateTime = Long.parseLong(createTime) * 1000L;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(msgCreateTime));
	}
	
	/**
	 * emoji表情转换(hex -> utf-16)
	 * @author diaoliwei
	 * @date 2016-2-25
	 * @param hexEmoji
	 * @return
	 */
	public static String emojiFace(int hexEmoji){
		return String.valueOf(Character.toChars(hexEmoji));
	}

}
