package com.mycompany.springhibernatemavenwithannotations.controller;

import com.mycompany.springhibernatemavenwithannotations.model.User;
import com.mycompany.springhibernatemavenwithannotations.service.UserService;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/")
public class AppController {

	@Autowired
	UserService service;
	
	

        @RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String login(@ModelAttribute("login") User user,
                        BindingResult result) {

		return "login";
	}
        
         @RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String login(@Valid User user, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		} else {
                    List<User> users = service.findAllUsers();
                    model.addAttribute("user", users);
                    return "index";
                }
	}
        
        
        @GetMapping("/users")
   public ResponseEntity<List<User>> list() {
      List<User> users = service.findAllUsers();
      return ResponseEntity.ok().body(users);
   }
   
//	@RequestMapping(value = { "/users", "/list" }, method = RequestMethod.GET)
//	public String listUsers(ModelMap model) {
//
//		List<User> users = service.findAllUsers();
//		model.addAttribute("user", users);
//		return "index";
//	}
      
        @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "registration";
	}
        
        @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveEmployee(@Valid User user, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}
                
		service.saveUser(user);

		model.addAttribute("success", "User " + user.getFirst_name() + " registered successfully");
		return "success";
	}
        
        
	/*
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-{user_id}-user" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable String user_id, ModelMap model) {
		User user = service.findById(Integer.parseInt(user_id));
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{user_id}-user" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String user_id) {

		if (result.hasErrors()) {
			return "registration";
		}
               
                service.updateUser(user);

		model.addAttribute("success", "User " + user.getFirst_name() + user.getLast_name() + " updated successfully");
		return "success";
	}

	@RequestMapping(value = { "/delete-{user_id}-user" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String user_id) {
		service.deleteUserById(Integer.parseInt(user_id));
		return "redirect:/list";
	}
}
