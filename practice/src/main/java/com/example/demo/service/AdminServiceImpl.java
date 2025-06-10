package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	            
	@Autowired
	private AdminRepository adminRepository;
	            
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 管理者登録処理
	@Override
	public Admin saveAdmin(AdminForm adminForm) {
		Admin admin = new Admin();
		admin.setLastName(adminForm.getLastName());
		admin.setFirstName(adminForm.getFirstName());
		admin.setEmail(adminForm.getEmail());
		admin.setPassword(passwordEncoder.encode(adminForm.getPassword()));
		
		//作成・更新日時を現在日時で設定
		LocalDateTime now = LocalDateTime.now();
		admin.setCreatedAt(now);
		admin.setUpdatedAt(now);
		
		return adminRepository.save(admin);
		
	}
	
	// メールアドレスで管理者検索（認証時）
	@Override
	public Admin findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}
	
	//メールアドレスが既に存在しているかチェック
	@Override
	public boolean existsByEmail(String email) {
		return adminRepository.existsByEmail(email);
	}
}
