package com.wxx.exception;

public class ControllerException extends BaseException {

	private static final long serialVersionUID = -3711290613973933714L;

	public ControllerException(String code) {
		super(code, null, code, null);
	}

	public ControllerException(String code, Object... objs) {
		super(code, null, code, objs);
	}

	public ControllerException(Throwable cause, String code, Object... objs) {
		super(code, cause, code, objs);
	}

	public ControllerException(Throwable cause, String code) {
		super(code, cause, code, null);
	}
}
