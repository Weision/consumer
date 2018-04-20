package com.wxx.util;

public class Counter {
	/** 初始值 */
	private int seqNo = 0;

	private static Counter INSTANCE = null;

	private Counter() {

	}

	public synchronized static Counter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Counter();
		}
		return INSTANCE;
	}

	public synchronized int getSeqNo() throws Exception {
		seqNo = seqNo < 1000000 ? seqNo : 0;
		return seqNo++;
	}

}
