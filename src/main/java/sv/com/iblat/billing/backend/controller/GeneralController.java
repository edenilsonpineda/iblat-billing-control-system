package sv.com.iblat.billing.backend.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import sv.com.iblat.billing.backend.catalog.Constants;
import sv.com.iblat.billing.backend.config.SecurityUtils;
import sv.com.iblat.billing.backend.core.service.UserService;
import sv.com.iblat.billing.backend.model.entities.Role;
import sv.com.iblat.billing.backend.model.entities.User;

@Slf4j
@Controller
public class GeneralController {
	
	@Autowired
	private UserService userService;
	
	Set<Role> roles = new HashSet<Role>();
	
	public void redirectLogin(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		String role = authentication.getAuthorities().toString();
		
		if(role.contains(Constants.ADMIN_ROLE)) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath().concat("/admin/home")));
		}else {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath().concat("/")));
		}
	}
	
	@GetMapping("/login")
	public ModelAndView redirectLogin() {
		ModelAndView modelAndView = new ModelAndView();
		
		if(SecurityUtils.isUserLoggedIn()) {
			modelAndView.setViewName("login");
		}else {
			modelAndView.setViewName("/");
		}
		
		
		return modelAndView;
	}
	
	@GetMapping("/admin/home")
	public ModelAndView adminHome() {
		ModelAndView modelAndView = new ModelAndView();
		log.info("User retrieved from Context: " + SecurityUtils.getUsername());
		
		modelAndView.addObject("userName", "Bienvenido de nuevo " + SecurityUtils.getUsername().toUpperCase());
		modelAndView.addObject("adminMessage", "Contenido disponible solamente para usuarios con ROL de Administrador");
		modelAndView.setViewName("admin/home");
		
		return modelAndView;
	}
	
	@GetMapping("/admin/users")
	public ModelAndView usersPage() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("usersList", userService.findAll());
		
		return modelAndView;
	}
	
	@GetMapping("/admin/users/new")
	public ModelAndView createNewUser() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("newUser", userService.createNew(null));
		modelAndView.addObject("rolesList", roles);
		
		return modelAndView;
	}
	
	@PostMapping("/admin/users")
	public String saveUser(@ModelAttribute("newUser") User user) {
		
		
		userService.save(null, user);
		
		return "redirect:/admin/users";
	}
	
	
}
