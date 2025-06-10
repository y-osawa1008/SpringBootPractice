package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public void saveContact(ContactForm contactForm) {
		Contact contact = new Contact();
		
		contact.setLastName(contactForm.getLastName());
		contact.setFirstName(contactForm.getFirstName());
		contact.setEmail(contactForm.getEmail());
		contact.setPhone(contactForm.getPhone());
		contact.setZipCode(contactForm.getZipCode());
		contact.setAddress(contactForm.getAddress());
		contact.setBuildingName(contactForm.getBuildingName());
		contact.setContactType(contactForm.getContactType());
		contact.setBody(contactForm.getBody());
		
		contactRepository.save(contact);
	}
	
	@Override
    public List<Contact> findAll() {
        return contactRepository.findAll();  // ← 一覧を返す
    }

	@Override
	public Contact findById(Long id) {
		return contactRepository.findById(id).orElse(null);
	}
	
	// 保存処理
	@Override
	public void save(Contact contact) {
		contactRepository.save(contact);
	}
	
	// 削除処理
	@Override
	public void deleteById(Long id) {
		contactRepository.deleteById(id);
	}
}
