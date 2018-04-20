package com.wxx.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class StaticConstant {

	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE=3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;
	public static final int EIGHT = 8;
	public static final int NINE = 9;
	public static final int TEN = 10;
	public static final int ELEVEN=11;
	public static final int TWELVE = 12;
	public static final int THIRTEEN = 13;
	public static final int FOURTEEN = 14;
	public static final int FIFTEEN = 15;
	public static final int SIXTEEN = 16;
	public static final int SEVENTEEN = 17;
	public static final int EIGHTEEN = 18;
	public static final int NINETEEN = 19;
	public static final int TWENTY = 20;
	public static final int TWENTY_ONE = 21;
	public static final int TWENTY_TWO = 22;
	public static final int TWENTY_THREE = 23;
	public static final int TWENTY_NINE = 29;
	public static final int THIRTY = 30;
	public static final int THIRTYFOUR = 34;
	public static final int THIRTYEIGHT = 38;
	public static final int FORTY = 40;
	public static final int FORTYTHREE = 43;
	public static final int FORTYNINE = 49;
	public static final int FIFTY = 50;
	public static final int SIXTYSIX = 66;
	public static final int ONEHUNDRED = 100;
	public static final int ONE_TWO_EIGHT = 128;
	public static final int ONE_THREE_ONE = 131;
	public static final int ONE_THREE_SIX = 136;
	public static final int ONE_FOUR_TWO = 142;
	public static final int ONE_FOUR_THREE = 143;
	public static final int TWO_HUNDRED = 200;
	public static final int TWO_FIVE_FIVE = 0XFF;
	public static final int TWO_THREE_ONE = 231;
	public static final int TWO_THREE_SIX = 236;
	public static final int TWO_FIVE_SIX = 256;
	public static final int FOUR_ZERO_ONE = 401;
	public static final int EIGHT_HUNDRED = 800;
	public static final int ONE_THOUSAND = 1000;
	public static final int TWO_THOUSAND = 2000;
	public static final int FOUR_THOUSAND = 4000;
	public static final int ONE_ZZ_ONE_THREE = 10013;
	public static final int ONE_ZZ_ONE_FOUR = 10014;
	public static final int ONE_ZZ_ONE_NINE = 10019;
	public static final int SIX_FIVE_THOUSAND = 65000;

	public static final long MINOFDAY = 86400000L;

	public static final String FROM_STR = " FROM ";
	public static final String AND_STR = " AND ";
	public static final String EQUAL_QUES_STR = " = ? ";
	public static final String UNCHECKED = "unchecked";
	public static final char XML_QUES = '?';
	public static final String XML_STARTNODE_BEGIN = "<";
	public static final String XML_ENDNODE_BEGIN = "</";
	public static final String XML_NODE_END = ">";
	public static final String XML_DELIMITER = "<|>";
	public static final String XML_REGEX = "<\\|>";
	public static final String XML_BIGSPACE = " ";
	public static final String XML_QUOTE = "\"";
	public static final String XML_EQUAL = "=";
	public static final String XML_COMMA = ",";
	public static final String XML_NEW_LINE = "\n";
	public static final String ROUTE_TYPE = "04"; //根据 （04－省行机构号）路由
	public static final String HEAD_BRANCO_NO = "00001"; //总行机构号
	

	public static final String CLIENTID_ELEMENT = "client_id";
	public static final String BOCOP_MSG = "中银开放平台异常";
	public static final String ERROR_MSG = "后台系统异常";

	public static final int ONEHUND = 1000;

	public static final Map<String, Integer> TRANCOUNTS = new HashMap<String, Integer>();

	public static final String USERID = "userid";
	
	public static final ExecutorService POOL = Executors.newCachedThreadPool();
	
	private StaticConstant() {

	}
}
