package com.example.employee.form;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UpdateForm {

	@Pattern(regexp = "^[0-9]{7}$")
	private String id;
	
	@NotBlank
	private String name;
	
	@Pattern(regexp = "^[0-9]{8}$")
	private String birthdate;
	
	private String division;

	private Map<String, String> a;
}
