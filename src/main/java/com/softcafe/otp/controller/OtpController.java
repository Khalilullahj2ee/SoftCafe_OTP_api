package com.softcafe.otp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.softcafe.otp.model.OtpModel;
import com.softcafe.otp.repository.OtpRepository;

@RestController
@CrossOrigin(origins = "*")
public class OtpController {

	@Autowired
	OtpRepository otpRepo;
	
	@PostMapping("/otp")
	public ResponseEntity<?> sentOtp(@RequestBody OtpModel otpModel){ 
		Map<String, Object> map = new HashMap<String, Object>();
		
		
	try {
	boolean isValidPhone =	otpModel.getPhoneNumber().matches("(^(\\+88|0088)?(01){1}[3456789]{1}(\\d){8})$");
	OtpModel otp =	null;

	if (isValidPhone) {
			otp = otpRepo.save(otpModel);
			map.put("message", "OTP successfully saved");
			map.put("statusCode", 200); 
			map.put("data", otp); 
			return ResponseEntity.ok(map);
		}else {
			map.put("message", "Please Enter Valid Phone Number"); 
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		} 
	} catch (Exception e) {
		e.printStackTrace();
		map.put("message", "OTP saved failed");
		map.put("data", null);
		map.put("statusCode", 400);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
	}
	}
}
