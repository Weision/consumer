/********************************************************************
 * 文件名：StringUtil.java 版权：Copyright (C) 2009 Bocsoft. All Rights Reserved. 修改人：郭
 * 荣 修改时间：2009-10-15 修改内容：
 *******************************************************************/
package com.wxx.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.wxx.constant.StaticConstant;
import com.wxx.exception.JSonFormatException;
import com.wxx.framework.handler.XmlHandler;

/**
 * 该类是对 的处理类
 * 
 * @author 郭 荣
 * @since 项目 1.0 版本
 * @history 2009-10-15 郭 荣 新建
 */
public class StringUtil {
	private static XMLSerializer xmlSerializer = new XMLSerializer();
	private static Logger logger = Logger.getLogger(StringUtil.class);
	public static final String MESSAGE = "message";

	static {
		StringUtil.xmlSerializer.setSkipNamespaces(true);
		StringUtil.xmlSerializer.setTrimSpaces(true);
		StringUtil.xmlSerializer.setArrayName("saplist");
		// StringUtil.xmlSerializer.setElementName("objdata"); //Sets the name
		// used for JSONArray elements.Default is 'e'.
	}

	/**
	 * 组建一个xml节点
	 * 
	 * @param tag
	 *            节点名（例如：age）
	 * @param value
	 *            int类型的节点值 （例如：25）
	 * @param classType
	 * @return 组成的带标签的节点（本例中为 {@code <age>25</age>} ）
	 */
	public static String addTag(String tag, int value, String classType) {
		return StringUtil.addTag(tag, String.valueOf(value), classType);
	}

	/**
	 * 组建一个xml节点
	 * 
	 * @param tag
	 *            节点名（例如：name）
	 * @param value
	 *            String类型的节点值 （例如：Fred）
	 * @param classType
	 * @return 组成的带标签的节点（本例中为 {@code <name>Fred</name>} ）
	 */
	public static String addTag(String tag, String value, String classType) {
		String thisClassType = classType == null ? StringUtils.EMPTY
				: " class=" + StaticConstant.XML_QUOTE + classType
						+ StaticConstant.XML_QUOTE;
		return new StringBuffer(StaticConstant.XML_STARTNODE_BEGIN).append(tag)
				.append(thisClassType).append(StaticConstant.XML_NODE_END)
				.append(value).append(StaticConstant.XML_ENDNODE_BEGIN)
				.append(tag).append(StaticConstant.XML_NODE_END).toString();
	}

	/**
	 * 从xml中取出节点名为key的节点的值
	 * 
	 * @param xml
	 *            xml格式的源字符串(例如:
	 *            {@code<user><name>Fred</name><password>password</password></user>}
	 *            )
	 * @param key
	 *            要获取的节点名称(例如:name)
	 * @return 节点的值(本例中为 Fred)
	 * @throws SAXException
	 * @throws IOException
	 */
	public static String getContentFromXML(String xml, String key) {
		return StringUtil.parse4Value(xml, key);
	}

	/**
	 * 从xml中取出节点名为key的节点，包括子节点
	 * 
	 * @param xml
	 *            xml格式的源字符串(例:
	 *            {@code<user><name>Fred</name><password>password</password></user>}
	 *            )
	 * @param key
	 *            要获取的节点名称(例:name)
	 * @return 带标签的节点(本例中为 {@code<name>Fred</name>})
	 * @throws SAXException
	 * @throws IOException
	 */
	public static String getContentFromXMLWithTab(String xml, String key) {
		return StringUtil.getBodyElement(xml, key);
	}

	/**
	 * 根据标签节点名称来截取节点报文内容
	 * 
	 * @param xml
	 * @param key
	 * @return
	 */
	public static String getBodyElement(String xml, String node) {
		int start = xml.indexOf("<" + node);
		int end = xml.lastIndexOf("</" + node + ">");
		if (start > 0 && end < 0) {
			return String
					.format("<%s xmlns:xsi=\"http://openapi.boc.cn\"></%s>",
							node, node);
		} else if (start < 0 || end < 0) {
			return StringUtils.EMPTY;
		}

		String result = xml.substring(start, end);
		return new StringBuffer(result).append("</" + node + ">").toString();
	}

	/**
	 * 正则匹配 Add by 朱正伟
	 * 
	 * @param src
	 *            源字符串
	 * @param regex
	 *            正则表达式
	 * @return
	 */
	public static String getPatternString(String src, String regex) {
		return src.replace(StringUtil.getPatternStrValue(src, regex),
				StringUtils.EMPTY);

	}

	public static String getPatternStrValue(String src, String regex) {
		return StringUtil.getPatternValue(src, regex, 0);
	}

	public static String getPatternValue(String src, String regex) {
		return StringUtil.getPatternValue(src, regex, 1);
	}

	private static String getPatternValue(String src, String regex, int group) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(src);
		String result = null;
		if (matcher.find()) {
			result = matcher.group(group);
		}
		return result;
	}

	/**
	 * 从request对象中获得交易路径（如：/rate/search）
	 * 
	 * @param request
	 * 
	 * @return shortUri
	 */
	public static String getShortUri(HttpServletRequest request) {
		String shortUri = request.getServletPath();
		int index = shortUri.indexOf(StaticConstant.XML_QUES);
		if (index > 0) {
			shortUri = shortUri.substring(0, index);
		}
		return shortUri;
	}

	public static int getStrLength(String str) {
		if (str == null) {
			return 0;
		}
		int len = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) < StaticConstant.ONE_TWO_EIGHT) {
				len++;
			} else {
				len += 2;
			}
		}
		return len;
	}

	public static String hexString(byte[] array) {
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			byte b = array[i];
			String hex = Integer.toHexString(b & StaticConstant.TWO_FIVE_FIVE);
			if (1 == hex.length()) {
				hex = '0' + hex;
			}
			if (0 == ((i + StaticConstant.FOUR) % StaticConstant.SIXTEEN)) {
				res.append(hex.toUpperCase()).append(
						StaticConstant.XML_NEW_LINE);
			} else if (array.length == (i + 1)) {
				res.append(hex.toUpperCase());
			} else {
				res.append(hex.toUpperCase()).append(
						StaticConstant.XML_BIGSPACE);
			}
		}
		return res.toString();
	}

	public static String jsonToXml(String jsonStr) throws JSonFormatException {
		if ((jsonStr == null) || StringUtils.EMPTY.equals(jsonStr)
				|| "{}".equals(jsonStr)) {
			return StringUtils.EMPTY;
		}
		String str = StringUtils.EMPTY;
		String rlt = StringUtils.EMPTY;
		try {
			str = StringUtil.xmlSerializer
					.write(JSONObject.fromObject(jsonStr));
			rlt = str.substring(StaticConstant.FORTYTHREE, str.length()
					- StaticConstant.SIX);
		} catch (Exception e) {
			throw new JSonFormatException("Unsupport this json Format ["
					+ jsonStr + "]", e);
		}
		return rlt;
	}

	/**
	 * 从xml中取出节点名为node的节点的值
	 * 
	 * @param xml
	 *            xml格式的源字符串
	 * @param node
	 *            要获取的节点名称
	 * @return 节点的值
	 */
	public static String parse4Value(String xml, String node) {
		InputSource is = new InputSource();
		StringReader xmlStr = new StringReader(xml);
		is.setCharacterStream(xmlStr);
		XmlHandler handler;
		try {
			handler = new XmlHandler();
		} catch (SAXException e) {
			return StringUtils.EMPTY;
		}
		handler.init(node);
		try {
			handler.parse(is);
		} catch (IOException e) {
		} catch (SAXException e) {
			logger.error(e.getMessage());
		}
		return handler.getPropsValue() != null ? (String) handler
				.getPropsValue().get(node.toLowerCase()) : StringUtils.EMPTY;
	}

	public static byte[] replaceSubString(byte[] message, byte[] msg,
			Integer first, int length, byte pad) {
		int last = first + length;
		byte[] newMessage = ByteUtil.rightPad(message, last, pad);
		return ArrayUtils.addAll(ArrayUtils.addAll(ArrayUtils.subarray(
				newMessage, 0, first),
				((msg.length > length) ? ArrayUtils.subarray(msg, 0, length)
						: ByteUtil.leftPad(msg, length))), ArrayUtils.subarray(
				newMessage, last, newMessage.length));
	}

	public static String replaceSubString(String str, String replaceStr,
			int first, int length, char padChar) {
		int last = first + length;
		String newStr = StringUtils.rightPad(str, last, padChar);
		return newStr.substring(0, first)
				+ ((replaceStr.length() > length) ? replaceStr.substring(0,
						length) : StringUtils.leftPad(replaceStr, length))
				+ newStr.substring(last);
	}

	// public static void main(String[] args){
	// String str =
	// "<body><totamt>00000000000170000+</totamt><flag></flag><messagecode>6082</messagecode><currcyno> </currcyno></body>";
	// System.out.println(str);
	//
	// System.out.println(xmlToJson(str));
	// }

	public static String xmlToJson(String xmlStr) {
		return StringUtil.xmlSerializer.read(xmlStr).toString();
	}

	/**
	 * 从xml中取出节点名为node的节点，包括子节点
	 * 
	 * @param xml
	 *            xml格式的源字符串
	 * @param node
	 *            要获取的节点名称
	 * @return 带标签的节点
	 */
	public static String parse4Node(String xml, String node) {
		InputSource is = new InputSource();
		StringReader xmlStr = new StringReader(xml);
		is.setCharacterStream(xmlStr);
		XmlHandler handler;
		try {
			handler = new XmlHandler();
		} catch (SAXException e) {
			logger.error(e.getMessage());
			return StringUtils.EMPTY;
		}
		handler.init(node);
		try {
			handler.parse(is);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		}
		return handler.getPropsNode() != null ? (String) handler.getPropsNode()
				.get(node.toLowerCase()) : StringUtils.EMPTY;
	}

	/**
	 * 如果参数为null，返回""，否则原样返回
	 * 
	 * @param str
	 * @return
	 */
	public static String getString(String str) {
		return str == null ? StringUtils.EMPTY : str;
	}

	/**
	 * 如果参数为null，返回""，否则原样返回
	 * 
	 * @param str
	 * @return
	 */
	public static String getString(Double dou) {
		return dou == null ? StringUtils.EMPTY : dou.toString();
	}

	public static String encode(String str) {
		String to = str;
		try {
			to = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return to;
	}

	public static int readIntFromByte(byte[] b) {
		int num = 0;
		for (int i = 0; i < b.length; i++) {
			num <<= 8;
			num += b[i] < 0 ? 256 + b[i] : ((int) (b[i]));
		}
		return num;
	}

	public static String leftPad(String str, int size, char padChar)
			throws UnsupportedEncodingException {
		if (str == null) {
			return null;
		}
		int pads = size - str.getBytes("UTF-8").length;
		if (pads < 0) {
			return str;
		}
		return org.apache.commons.lang3.StringUtils.repeat(padChar, pads)
				.concat(str);
	}

	// 返回obj对象字符串
	public static String getString(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	public static String setTranUrl(String url, HttpServletRequest request) {
		String tranUrl = null;
		if (url != null && url.length() > 0) {
			tranUrl = url;
		} else {
			tranUrl = StringUtil.getShortUri(request);
		}
		return tranUrl;
	}

	/**
	 * 左补0到length位数
	 * 
	 * @param obj
	 * @param length
	 * @return
	 */
	public static String appendLeftZero(Object obj, int length) {
		String str = getString(obj);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < (length - str.length()); i++) {
			sb.append("0");
		}
		sb.append(str);
		return sb.toString();
	}

	public static int getStrUtfLength(String arg) {
		try {
			return arg.getBytes("UTF-8").length;
		} catch (UnsupportedEncodingException e) {
			return 0;
		}
	}

	// 去掉左边的0
	public static String leftZeroOff(String str) {
		if (str == null || "".equals(str.trim())) {
			return "";
		}
		String newStr = str.trim();
		int strLen = newStr.length();
		for (int i = 0; i < strLen; i++) {
			if ("0".equals(String.valueOf(newStr.charAt(i)))) {
				newStr = newStr.substring(1, strLen);
			} else {
				break;
			}
			strLen = newStr.length();
			i--;
		}

		return newStr;
	}

	/**
	 * 字符串为空判断
	 * 
	 * @param arg
	 * @return
	 */
	public static boolean isEmpty(String arg) {
		if (null == arg || "".equals(arg.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串非为空判断
	 * 
	 * @param arg
	 * @return
	 */
	public static boolean isNotEmpty(String arg) {
		if (null == arg || "".equals(arg.trim())) {
			return false;
		}
		return true;
	}

	/**
	 * 如果字符串中包含了回车、制表符和换行符，则替换成空字符
	 * 
	 * @param arg
	 * @return
	 */
	public static String replaceStr(String arg) {
		return getString(arg).replaceAll("[\\t\\n\\r]", "");
	}

	/**
	 * 替换字符串中的{x} x可以是字符串或数字 替换是按照{}出现的顺序，与{}中的内容无关
	 * 
	 * @param value
	 * @param args
	 * @return
	 */
	public static String fillingValue(String value, Object[] args) {
		String result = value;
		if (args == null || args.length == 0) {
			return result;
		}

		for (Object arg : args) {
			if (arg instanceof CharSequence) {
				result = result.replaceFirst("\\{[^\\{]*\\}", arg.toString());
			}
		}
		return result;
	}

	/**
	 * 如果字符串中包含了回车、和换行符，则替换成空字符 去掉base64编码时出现的回车和换行符
	 * 
	 * @param arg
	 * @return
	 */
	public static String replaceBaseStr(String arg) {
		return getString(arg).replaceAll("[\\n\\r]", "");
	}

	public static String getSeqNo() throws Exception {
		String time = DateUtil.dateFormat(new Date(), "HHmmss");
		String ret = time
				+ String.format("%06d", Counter.getInstance().getSeqNo());
		return ret;
	}

}
