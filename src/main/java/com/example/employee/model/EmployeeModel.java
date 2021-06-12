package com.example.employee.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EmployeeModel implements Comparable<EmployeeModel> {

	@Pattern(regexp = "^[0-9]{7}$")
	private String key;

	@Pattern(regexp = "^[0-9]{7}$")
	private String id;
	
	@NotBlank
	private String name;
	
	@Pattern(regexp = "^[0-9]{8}$")
	private String birthdate;
	
	private String division;
	
	@Override
	public int compareTo(EmployeeModel empModel) {
		return this.getId().compareTo(empModel.getId());
	}

}
