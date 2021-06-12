package com.example.employee.form;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.AssertTrue;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class SearchForm implements Serializable {

	private static final long serialVersionUID = -157143280035400042L;

	private String id;

	private String name;
	
	private String searchType = "id";

	public int i = 0;
	@AssertTrue(message = "社員番号を半角数字７桁で入力して下さい。")
	public boolean isIdValid() {

		boolean result = true;

		// 社員番号で検索する場合
		if (this.searchType.equals("id") && this.id != null) {
			// パターン生成
			String regex = "^[0-9]{7}$";
			Pattern p = Pattern.compile(regex);
			// パターンとマッチング
			Matcher m = p.matcher(this.id);

			result = m.find();
		}
		
		return result;
	}

	@AssertTrue(message = "氏名を入力して下さい。")
	public boolean isNameValid() {
		
		boolean result = true;

		// 氏名で検索する場合
		if (this.searchType.equals("name")) {
			result = !(this.name.isEmpty());
		}
		
		return result;
	}
}