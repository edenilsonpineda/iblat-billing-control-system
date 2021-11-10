package sv.com.iblat.billing.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import sv.com.iblat.billing.backend.config.SecurityUtils;
import sv.com.iblat.billing.backend.core.service.UserService;

@Slf4j
@Controller
public class MvcController {
	
	@Autowired
	UserService userService;
	
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
		
		modelAndView.addObject("userName", "Bienvenido de nuevo " + SecurityUtils.getUsername());
		modelAndView.addObject("adminMessage", "Contenido disponible solamente para usuarios con ROL de Administrador");
		modelAndView.setViewName("admin/home");
		
		return modelAndView;
	}
}
