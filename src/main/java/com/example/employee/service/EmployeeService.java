package com.example.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.form.SearchForm;
import com.example.employee.mapper.EmployeeMapper;
import com.example.employee.model.EmployeeModel;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	/**
	 * 従業員データ読み込み処理（key指定）
	 * @param form SearchFormオブジェクト 
	 * @return 従業員データ：EmployeeModelリスト
	 */
	public List<EmployeeModel> loadEmployee(SearchForm form) {
		
		List<EmployeeModel> employee;

		if (form.getSearchType().equals("id")) {
			employee = employeeMapper.selectEmployeeById(form.getId());
		} else {
			employee = employeeMapper.selectEmployeeByName(form.getName());
		}
		
		return employee;
	}
	
	/**
	 * 従業員データ読み込み処理（全件）
	 * @return 従業員データ：EmployeeModelリスト
	 */
	public List<EmployeeModel> loadAllEmployee() {
		List<EmployeeModel> employee = employeeMapper.selectEmployeeAll();
		
		return employee;
	}

	/**
	 * 従業員データ登録処理
	 * @param model EmployeeModelオブジェクト
	 */
	public void registEmployee(EmployeeModel model) {
		employeeMapper.insertEmployee(model);
	}
	
	/**
	 * 従業員データ更新処理
	 * @param model EmployeeModelオブジェクト
	 */
	public boolean updateEmployee(EmployeeModel model) {
		return employeeMapper.updateEmployee(model);
	}

	/**
	 * 従業員データ削除処理
	 * @param id 社員番号（削除キー）
	 */
	public void deleteEmployee(String id) {
		employeeMapper.deleteEmployee(id);
	}
}
