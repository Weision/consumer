package com.wxx.framework.handler;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderAdapter;

import com.wxx.constant.StaticConstant;

public class XmlHandler extends XMLReaderAdapter {

	public XmlHandler() throws SAXException {
		super();
	}

	public void init(String node) {
		this.propsNode = new Properties();
		this.propsValue = new Properties();
		nodes.delete(0, nodes.length());
		value.delete(0, value.length());
		isStop = false;
		theNode = node;
	}

	private Properties propsNode;
	private Properties propsValue;
	private StringBuffer nodes = new StringBuffer();
	private StringBuffer value = new StringBuffer();
	private boolean isStop = false;
	private String theNode;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if (isStop) {
			return;
		}
		value.delete(0, value.length());
		nodes.append(StaticConstant.XML_STARTNODE_BEGIN).append(qName);
		for (int i = 0; atts != null && i < atts.getLength(); i++) {
			String attName = atts.getQName(i);
			String attValue = atts.getValue(i);
			nodes.append(StaticConstant.XML_BIGSPACE).append(attName)
					.append(StaticConstant.XML_EQUAL)
					.append(StaticConstant.XML_QUOTE).append(attValue)
					.append(StaticConstant.XML_QUOTE);
		}
		nodes.append(StaticConstant.XML_NODE_END);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (isStop) {
			return;
		}

		nodes.append(StaticConstant.XML_ENDNODE_BEGIN).append(qName)
				.append(StaticConstant.XML_NODE_END);

		if (qName.equals(theNode)) {
			String[] postSplitStrings = nodes
					.toString()
					.replace(
							StaticConstant.XML_STARTNODE_BEGIN + qName,
							StaticConstant.XML_DELIMITER
									+ StaticConstant.XML_STARTNODE_BEGIN
									+ qName).split(StaticConstant.XML_REGEX);

			StringBuffer sb = new StringBuffer();

			for (int i = 1; i < postSplitStrings.length; i++) {
				sb.append(postSplitStrings[i].replace(
						StaticConstant.XML_ENDNODE_BEGIN + qName
								+ StaticConstant.XML_NODE_END,
						StaticConstant.XML_ENDNODE_BEGIN + qName
								+ StaticConstant.XML_NODE_END
								+ StaticConstant.XML_DELIMITER).split(
						StaticConstant.XML_REGEX)[0]);
			}

			propsNode.put(qName.toLowerCase(), sb.toString().trim());
		} else {
			propsNode.put(qName, StringUtils.EMPTY);
		}
		if (qName.equals(theNode) && value.length() > 0) {
			propsValue.put(qName.toLowerCase(), value.toString().trim());
		} else {
			propsValue.put(qName, StringUtils.EMPTY);
		}
		value.delete(0, value.length());
		if (theNode.equals(qName)) {
			isStop = true;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (isStop) {
			return;
		}
		value.append(ch, start, length);
		nodes.append(ch, start, length);
	}

	public Properties getPropsNode() {
		return propsNode;
	}

	public Properties getPropsValue() {
		return propsValue;
	}
}
