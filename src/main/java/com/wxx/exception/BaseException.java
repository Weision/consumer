package com.wxx.exception;

public class BaseException extends Exception {
	private static final long serialVersionUID = 1381325479896057076L;

	private String code;

	private Object[] values;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object[] getValues() {
		return values == null ? null : values.clone();
	}

	public void setValues(Object[] values) {
		this.values = values == null ? null : values.clone();
	}

	public BaseException(String message, Throwable cause, String code,
			Object[] values) {
		super(message, cause);
		this.code = code;
		this.values = values == null ? null : values.clone();
	}
}
