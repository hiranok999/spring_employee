package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.employee.form.UpdateForm;
import com.example.employee.form.UpdateListForm;
import com.example.employee.model.EmployeeModel;
import com.example.employee.service.EmployeeService;

@Controller
public class UpdateController {

	@Autowired
	UpdateForm updateForm;

	// 更新画面（初期遷移）
	@PostMapping("/update")
	public String update(Model model, @Validated @ModelAttribute("updateForms") UpdateListForm updateForms) {

		return "update";
	}

	@Autowired
	EmployeeModel empModel;
	@Autowired
	EmployeeService empService;

	// 更新画面（更新結果）
	@PostMapping("/updateResult")
	public String updateResult(Model model, @Validated @ModelAttribute("updateForms") UpdateListForm updateForms,
			BindingResult result) throws Exception {

		// バリデーションチェックエラーの場合は画面遷移せずにエラーを表示
		if (result.hasErrors()) {
			return "update";
		}

		// 更新前後で従業員情報が同一の場合は更新しない
		UpdateForm oldInfo = updateForms.getUpdateForms().get(0);
		UpdateForm newInfo = updateForms.getUpdateForms().get(1);
		if (oldInfo.equals(newInfo)) {
			model.addAttribute("msg", "入力内容が更新前と同一です。<br>いずれかの項目を変更して下さい。");
			return "update";
		}

		empModel.setKey(oldInfo.getId());
		empModel.setId(newInfo.getId());
		empModel.setName(newInfo.getName());
		empModel.setBirthdate(newInfo.getBirthdate());
		empModel.setDivision(newInfo.getDivision());

		try {
			// 従業員テーブルを更新した場合、更新結果画面を表示する
			empService.updateEmployee(empModel);
			return "updateResult";
		} catch(Exception e) {
			model.addAttribute("msg", "更新できませんでした。<br>入力内容を確認して下さい。");
			return "update";
		}
	}

}
