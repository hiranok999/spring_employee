package com.example.employee.form;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UpdateListForm {
	@Valid
	private List<UpdateForm> updateForms;

}
