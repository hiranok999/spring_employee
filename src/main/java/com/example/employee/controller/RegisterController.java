package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.employee.model.EmployeeModel;
import com.example.employee.service.EmployeeService;

@Controller
public class RegisterController {

	@Autowired
	EmployeeModel empModel;

	@GetMapping("/register")
	public String register(Model model, @ModelAttribute EmployeeModel employeeModel) {
		return "register";
	}

	@Autowired
	private EmployeeService employeeService;

	@Transactional
	@PostMapping("/register")
	public String registerResult(Model model, @Validated @ModelAttribute EmployeeModel employeeModel,
			BindingResult result) throws Exception {

		// バリデーションチェックエラーの場合はエラーを表示
		if (result.hasErrors()) {
			return "register";
		}
		
		try {
			// 従業員テーブルに登録
			employeeService.registEmployee(employeeModel);
			model.addAttribute("result", "登録が完了しました。");
		} catch(Exception e) {
			model.addAttribute("result", "登録に失敗しました。<br>入力内容を確認して下さい。");
		}

		return "register";
	}

}
