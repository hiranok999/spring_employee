package com.example.employee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.employee.model.EmployeeModel;

/**
 * t_employeeのSQL記述クラス
 */
@Mapper
public interface EmployeeMapper {

	// 社員番号をkeyに従業員データ取得
	@Select("SELECT * FROM public.t_employee WHERE id = #{id}")
	public List<EmployeeModel> selectEmployeeById(@Param("id") String id);

	// 氏名をkeyに従業員データ取得
	@Select("SELECT * FROM public.t_employee WHERE name = #{name}")
	public List<EmployeeModel> selectEmployeeByName(@Param("name") String name);

	// 社員番号をkeyに従業員データ取得
	@Select("SELECT * FROM public.t_employee")
	public List<EmployeeModel> selectEmployeeAll();

	// 従業員データを登録
	@Insert("INSERT INTO t_employee (id, name, birthdate, division) VALUES (#{id}, #{name}, #{birthdate}, #{division})")
	public void insertEmployee(EmployeeModel model);
	
	// 従業員データを更新
	@Update("UPDATE t_employee set id = #{id}, name = #{name}, birthdate = #{birthdate}, division = #{division} WHERE id = #{key}")
	public boolean updateEmployee(EmployeeModel model);
	
	// 従業員データを削除
	@Delete("DELETE FROM t_employee WHERE id = #{id}")
	public void deleteEmployee(@Param("id") String id);
}
