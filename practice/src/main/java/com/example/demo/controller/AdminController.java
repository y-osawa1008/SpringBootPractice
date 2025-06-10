package com.example.demo.controller;

import java.util.List;
import java.util.Objects;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Contact;
import com.example.demo.form.AdminForm;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.AdminService;
import com.example.demo.service.ContactService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    private ContactRepository contactRepository;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/signup")
	public String showSignupForm(Model model) {
		    
		    model.addAttribute("adminForm", new AdminForm());//空のフォームオブジェクトを渡す
		    return "admin/signup";
	}
	
	@PostMapping("/signup")
	public String prcessSignup(@ModelAttribute("adminForm") @Valid AdminForm adminForm,
			                   BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/signup";
		}
		
		adminService.saveAdmin(adminForm); // 登録処理
		return "redirect:/admin/signin"; // 登録後はログイン画面へリダイレクト
	}
	
	// ログイン画面表示
	@GetMapping("/signin")
	public String showSigninForm(Model model) {
		model.addAttribute("signinForm", new AdminForm());
		return "admin/signin";
	}
	
	// ログアウト処理は SecurityConfig に任せるため不要
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/contacts")
    public String showContacts(Model model) {
		//問い合わせ一覧を取得
		List<Contact> contacts = contactService.findAll();
		contacts.removeIf(Objects::isNull); // null の要素を除外
		//モデルにセット
		model.addAttribute("contacts", contacts);
        return "admin/contacts";
    }
	
	@GetMapping("/contacts/{id}")
	public String showContactDetail(@PathVariable Long id, Model model) {
		Contact contact = contactService.findById(id);
		if (contact == null) {
			return "redirect:/admin/contacts";
		}
		model.addAttribute("contact", contact);
		return "admin/contactDetail"; // 詳細画面テンプレートに返す
	}
	
	// 編集画面表示用
    @GetMapping("/contacts/edit/{id}")
    public String editContactForm(@PathVariable Long id, Model model) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));
        model.addAttribute("contact", contact);
        return "admin/contactEdit";
    }
    
 // 更新処理
    @PostMapping("/contacts/edit/{id}")
    public String updateContact(@PathVariable Long id,
                                @ModelAttribute("contact") @Valid Contact form,
                                BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
    		return "admin/contactEdit";
    	}

        Contact contact = contactService.findById(id);
        contact.setLastName(form.getLastName());
        contact.setFirstName(form.getFirstName());
        contact.setEmail(form.getEmail());
        contact.setPhone(form.getPhone());
        contact.setZipCode(form.getZipCode());
        contact.setAddress(form.getAddress());
        contact.setBuildingName(form.getBuildingName());
        contact.setContactType(form.getContactType());
        contact.setBody(form.getBody());
        contactService.save(contact);

        return "redirect:/admin/contacts";
    }
    
 // 削除処理
    @PostMapping("/contacts/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteById(id);       // deleteById を用意
        return "redirect:/admin/contacts";
    }
}
