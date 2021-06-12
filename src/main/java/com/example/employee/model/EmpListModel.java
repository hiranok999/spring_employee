package com.example.employee.model;

import java.util.List;

import lombok.Data;

@Data
public class EmpListModel {
	
	private String key;

	private List<EmployeeModel> empList;

}
