package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name = "contacts")
public class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotBlank(message = "姓を入力してください")
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@NotBlank(message = "名を入力してください")
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	// @Email: メールアドレス形式なのか検証
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "有効なメールアドレス形式で入力してください")
	@Column(name = "email", nullable = false)
	private String email;
	
	@NotBlank(message = "電話番号を入力してください")
	@Size(min = 10, max = 11)
	@Column(name = "phone", nullable = false)
	private String phone;
	
	@NotBlank(message = "郵便番号を入力してください")
	@Pattern(regexp = "[0-9]{3}[-]{0,1}[0-9]{4}")
	@Column(name = "zip_code", nullable = false)
	private String zipCode;
	
	@NotBlank(message = "住所を入力してください")
	@Column(name = "address", nullable = false)
	private String address;
	
	@NotBlank(message = "建物名を入力してください")
	@Column(name = "building_name", nullable = false)
	private String buildingName;
	
	@NotBlank
	@Column(name = "contact_Type", nullable = false)
	private String contactType;
	
	@NotBlank(message = "内容を入力してください")
	@Size(max = 1000, message = "内容は1000文字以内で入力してください")
	@Column(name = "body", nullable = false)
	private String body;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	@PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
	}
}
