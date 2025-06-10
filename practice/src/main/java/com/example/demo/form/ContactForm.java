package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class ContactForm implements Serializable {
	@NotBlank(message = "姓を入力してください")
	private String lastName;
	
	@NotBlank(message = "名を入力してください")
	private String firstName;
	
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "有効なメールアドレス形式で入力してください")
	private String email;
	
	@NotBlank(message = "電話番号を入力してください")
	@Size(min = 10, max = 11)
	private String phone;
	
	@NotBlank(message = "郵便番号を入力してください")
	@Pattern(regexp = "[0-9]{3}[-]{0,1}[0-9]{4}")
	private String zipCode;
	
	@NotBlank(message = "住所を入力してください")
	private String address;
	
	@NotBlank(message = "建物名を入力してください")
	private String buildingName;
	
	@NotBlank
	private String contactType;
	
	@NotBlank(message = "内容を入力してください")
	private String body;
}
