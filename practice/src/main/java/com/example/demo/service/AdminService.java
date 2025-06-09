package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;

public interface AdminService {
	    // 管理者の新規登録
	    Admin saveAdmin(AdminForm adminForm);
	    
	    // メールアドレスで管理者を検索(ログイン時に使用)
	    Admin findByEmail(String email);
	    
	    // メールアドレスが既に存在するかのチェック
	    boolean existsByEmail(String email);
}
