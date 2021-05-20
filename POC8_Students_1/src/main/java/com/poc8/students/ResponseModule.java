package com.poc8.students;

public class ResponseModule {
	 private String message;
	 private boolean status;
	 private String progressMessage;
	 public ResponseModule(String message, boolean status,String progressMessage) {
			super();
			this.message = message;
			this.status = status;
			this.progressMessage=progressMessage;
		}
	 public ResponseModule() {
		 super();
	 }
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getProgressMessage() {
		return progressMessage;
	}
	public void setProgressMessage(String progressMessage) {
		this.progressMessage = progressMessage;
	}
	@Override
	public String toString() {
		return "ResponseModule [message=" + message + ", status=" + status + ", progressMessage=" + progressMessage + "]";
	}
	 
	}