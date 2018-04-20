package com.wxx.exception;

public class OutofMaxDatagramException extends BaseException {

	private static final long serialVersionUID = -3711290613973933714L;

	public OutofMaxDatagramException(String code) {
		super(code, null, code, null);
	}

	public OutofMaxDatagramException(Throwable cause, String code) {
		super(code, cause, code, null);
	}

	public OutofMaxDatagramException(String code, Object[] values) {
		super(code, null, code, values);
	}

	public OutofMaxDatagramException(Throwable cause, String code,
			Object[] values) {
		super(code, cause, code, values);
	}

	public OutofMaxDatagramException(String code, String message) {
		super(message, null, code, null);
	}

	public OutofMaxDatagramException(String code, Throwable cause,
			String message) {
		super(message, cause, code, null);
	}

	public OutofMaxDatagramException(String code, Throwable cause,
			String message, Object[] values) {
		super(message, cause, code, values);
	}
}
