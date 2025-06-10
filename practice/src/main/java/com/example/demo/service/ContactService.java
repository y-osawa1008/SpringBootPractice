package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;

public interface ContactService {
	public void saveContact(ContactForm contactForm);
    public List<Contact> findAll();
    public Contact findById(Long id);
    public void save(Contact contact);
    public void deleteById(Long id);
}
