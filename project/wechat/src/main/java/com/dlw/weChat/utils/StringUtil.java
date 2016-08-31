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
	
	/**
	 * 判断emoji表情
	 * @param content
	 * @date 2016-8-30
	 * @author diaoliwei
	 * @return
	 */
	public static boolean isEmojiFace(String content){
		boolean result = false;
		Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
					Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE) ;
					
		Matcher matcher = emoji.matcher(content);
		if (matcher.matches()) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 字符串转换成十六进制字符串
	 * @date 2016-8-30
	 * @author diaoliwei
	 * @param str
	 * @return
	 */
	public static String str2HexStr(String str) {    
	    char[] chars = "0123456789ABCDEF".toCharArray();    
	    StringBuilder sb = new StringBuilder("");  
	    byte[] bs = str.getBytes();    
	    int bit;    
	    for (int i = 0; i < bs.length; i++) {    
	        bit = (bs[i] & 0x0f0) >> 4;    
	        sb.append(chars[bit]);    
	        bit = bs[i] & 0x0f;    
	        sb.append(chars[bit]);  
	        sb.append(' ');  
	    }    
	    return sb.toString().trim();    
	}  
	
	public static String filterEmoji(String source) {
        if (StringUtils.isEmpty(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isNotEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
                }
        }
        if (buf == null) {
            return source;
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
	}
	

    private static boolean isNotEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }
    
    
    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|t*|r*|n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
            }
        }
        float result = count / chLength;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }

    }
    
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
    
     

}
