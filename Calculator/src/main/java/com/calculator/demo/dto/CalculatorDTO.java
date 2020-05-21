package com.calculator.demo.dto;

public class CalculatorDTO {
	
	private String operation;
	private Double number1;
	private Double number2;
	private Double result;
	private String msg;
	private int replaySeq;
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Double getNumber1() {
		return number1;
	}
	public void setNumber1(Double number1) {
		this.number1 = number1;
	}
	public Double getNumber2() {
		return number2;
	}
	public void setNumber2(Double number2) {
		this.number2 = number2;
	}
	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getReplaySeq() {
		return replaySeq;
	}
	public void setReplaySeq(int replaySeq) {
		this.replaySeq = replaySeq;
	}
	
}
