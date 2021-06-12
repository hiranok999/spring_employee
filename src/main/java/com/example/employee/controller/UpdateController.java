package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.employee.form.UpdateForm;
import com.example.employee.model.EmployeeModel;
import com.example.employee.service.EmployeeService;

@Controller
public class UpdateController {

	@Autowired
	UpdateForm updateForm;

	// 更新画面（初期遷移）
	@PostMapping("/update")
	public String update(Model model, @Validated @ModelAttribute UpdateForm updateForm) {

		model.addAttribute("old_id", updateForm.getId());
		model.addAttribute("old_name", updateForm.getName());
		model.addAttribute("old_birthdate", updateForm.getBirthdate());
		model.addAttribute("old_division", updateForm.getDivision());
		return "update";
	}

	@Autowired
	EmployeeModel empModel;
	@Autowired
	EmployeeService empService;

	// 更新画面（更新結果）
	@PostMapping("/updateResult")
	public String updateResult(Model model, @RequestParam("old_id") String old_id,
			@RequestParam("old_name") String old_name, @RequestParam("old_birthdate") String old_birthdate,
			@RequestParam("old_division") String old_division, @Validated @ModelAttribute UpdateForm updateForm,
			BindingResult result) throws Exception {

		// バリデーションチェックエラーの場合は画面遷移せずにエラーを表示
		if (result.hasErrors()) {
			return "update";
		}

		// 画面入力内容を変数に保存
		String new_id = updateForm.getId();
		String new_name = updateForm.getName();
		String new_birthdate = updateForm.getBirthdate();
		String new_division = updateForm.getDivision();

		// 画面入力内容が更新前の従業員情報と同一の場合は更新しない
		if (old_id.equals(new_id) && old_name.equals(new_name) && old_birthdate.equals(new_birthdate)
				&& old_division.equals(new_division)) {
			model.addAttribute("msg", "入力内容が更新前と同一です。<br>いずれかの項目を変更して下さい。");
			model.addAttribute("old_id", old_id);
			model.addAttribute("old_name", old_name);
			model.addAttribute("old_birthdate", old_birthdate);
			model.addAttribute("old_division", old_division);
			model.addAttribute("updateForm", updateForm);
			System.out.println(updateForm.getA().get("old"));
			System.out.println(updateForm.getA().get("new"));
			return "update";
		}

		empModel.setKey(old_id);
		empModel.setId(new_id);
		empModel.setName(new_name);
		empModel.setBirthdate(new_birthdate);
		empModel.setDivision(new_division);

		// 従業員テーブルを更新した場合、更新結果画面を表示する
		boolean updateResult = empService.updateEmployee(empModel);
		if (updateResult) {
			System.out.println(updateResult);
			return "updateResult";
		} else {
			return "update";
		}
	}

}
