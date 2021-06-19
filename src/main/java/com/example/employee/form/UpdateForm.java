package com.example.employee.form;

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
	
	public boolean equals(UpdateForm object) {
		boolean result = false;

		if (this.getId().equals(object.getId()) && this.getName().equals(object.getName())
				&& this.getBirthdate().equals(object.getBirthdate())
				&& this.getDivision().equals(object.getDivision())) {
			result = true;
		}

		return result;
	}

}
