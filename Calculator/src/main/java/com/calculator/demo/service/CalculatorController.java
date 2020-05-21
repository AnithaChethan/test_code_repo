package com.calculator.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.calculator.demo.dto.CalculatorDTO;

@RestController
public class CalculatorController {

	private static Map<Integer, CalculatorDTO> storedData = new HashMap<Integer, CalculatorDTO>();
	
	@RequestMapping(value = "/calculate", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllOperations() {
		return new ResponseEntity<>(storedData.values(), HttpStatus.OK);
	}

	@RequestMapping(value = "/calculate", method = RequestMethod.POST)
	public ResponseEntity<Object> calculate(@RequestBody CalculatorDTO data) {
		if(validateData(data)){
			calculateResult(data);
			Integer key = storedData.size() + 1;
			storedData.put(key, data);
		}
		return new ResponseEntity<>(data.getMsg(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/replay", method = RequestMethod.POST)
	public ResponseEntity<Object> replay(@RequestBody CalculatorDTO data) {
		CalculatorDTO dto = storedData.get(data.getReplaySeq());
		String msg = "Replay Sequence data doesn't exist";
		if (dto != null) {
			msg = dto.getMsg();
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	private boolean validateData(CalculatorDTO data) {
		boolean valid = true;
		String msg = "";
		if (!(data.getNumber1() instanceof Double)
				&& !(data.getNumber2() instanceof Double)) {
			msg = "Both input numbers are invalid ";
			data.setMsg(msg);
			valid = false;
		} else if (!(data.getNumber1() instanceof Double)) {
			msg = "Invalid number" + data.getNumber1();
			data.setMsg(msg);
			valid = false;
		} else if (!(data.getNumber2() instanceof Double)) {
			msg = "Invalid number" + data.getNumber2();
			data.setMsg(msg);
			valid = false;
		}
		return valid;
	}

	private void calculateResult(CalculatorDTO data) {
		double res = 0;
		String msg = "";
		String operation = data.getOperation();
		Double number1 = data.getNumber1();
		if(!(number1 instanceof Double)){
			msg = "Invalid number" + number1;
			return;
		}
		Double number2 = data.getNumber2();
		switch (operation) {
		case "ADD":
			res = number1 + number2;
			msg = "Sum of " + number1 + " and " + number2 + " is " + res;
			break;
		case "SUBTRACT":
			res = number1 - number2;
			msg = "Difference of " + number1 + " and " + number2 + " is " + res;
			break;
		case "DIVIDE":
			try{
				res = number1.intValue() / number2.intValue();
			}
			catch(ArithmeticException e){
				msg = "Exception occured while performing division operation";
				break;
			}
			msg = "Division of " + number1 + " and " + number2 + " is " + number1/number2;
			break;
		case "MULTIPLY":
			res = number1 * number2;
			msg = "Multiplication of " + number1 + " and " + number2 + " is "
					+ res;
			break;
		case "MODULO":
			res = number1 % number2;
			msg = "MODULO of " + number1 + " and " + number2 + " is " + res;
			break;
		default:
			msg = "Invalid Operation";
		}
		data.setMsg(msg);
		data.setResult(res);
	}

}
