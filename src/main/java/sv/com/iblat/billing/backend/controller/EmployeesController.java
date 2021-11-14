package sv.com.iblat.billing.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import sv.com.iblat.billing.backend.core.service.EmployeeService;

@Controller
public class EmployeesController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/admin/employees")
	public ModelAndView usersPage() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("employeesList", employeeService.findAll());
		return modelAndView;
	}

}
