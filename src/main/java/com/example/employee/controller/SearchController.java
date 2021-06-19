package com.example.employee.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.employee.form.SearchForm;
import com.example.employee.model.EmpListModel;
import com.example.employee.model.EmployeeModel;
import com.example.employee.service.EmployeeService;

@Controller
public class SearchController {
	
	@Autowired
	SearchForm searchForm;

	// 検索画面（初期遷移）
	@GetMapping("/search")
	public String search(Model model, @Validated @ModelAttribute SearchForm searchForm) {
		return "search";
	}
	
	@Autowired
	EmployeeService employeeService;

	// 検索画面（検索ボタン押下）
	@GetMapping("/searchResult")
	public String getEmployeeData(Model model, @Validated @ModelAttribute SearchForm searchForm, BindingResult result) throws Exception {

		// バリデーションチェックエラーの場合はエラーを表示
		if(result.hasErrors()) {
			return "search";
		}

		// 従業員テーブル検索
		List<EmployeeModel> empData = employeeService.loadEmployee(searchForm);

		// 社員番号の昇順でソート
		Collections.sort(empData);

		model.addAttribute("result", empData.size());
		model.addAttribute("employees", empData);
		
		return "search";
	}
	
	// 検索画面（一覧表示ボタン押下）
	@GetMapping("/allListResult")
	public ModelAndView allEmployee(@ModelAttribute EmpListModel empListModel, ModelAndView mav) {

		// 従業員テーブルからレコードを削除
		List<EmployeeModel> empList = employeeService.loadAllEmployee();
		
		// 社員番号の昇順でソート
		Collections.sort(empList);

		mav.addObject("employees", empList);
		mav.addObject("result", empList.size());
		mav.setViewName("/list :: timeline");

		return mav;
	}

	// 検索画面（削除ボタン押下）
	@PostMapping("/deleteResult")
	public ModelAndView deleteEmployee(@ModelAttribute EmpListModel empListModel, ModelAndView mav) {

		// 削除対象レコードのkey
		final String key = empListModel.getKey();
		// 従業員テーブルからレコードを削除
		employeeService.deleteEmployee(key);

		// 一覧表示する従業員リスト（削除対象の従業員を含む）
		List<EmployeeModel> empList = empListModel.getEmpList();
		// 従業員リストから削除対象の従業員を除外する
		for (int i = 0; i < empList.size(); i++) {
			if (empList.get(i).getId().equals(key)) {
				empList.remove(i);
				break;
			}
		}

		mav.addObject("employees", empList);
		mav.addObject("result", empList.size());
		mav.setViewName("/list :: timeline");

		return mav;
	}

}
