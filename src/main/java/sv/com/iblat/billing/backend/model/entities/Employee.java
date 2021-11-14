package sv.com.iblat.billing.backend.model.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sv.com.iblat.billing.backend.catalog.Constants;

@Entity
@Table(name = "employees")
@Getter @Setter @NoArgsConstructor
public class Employee extends AbstractEntity{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(name = "name")
	@Size(max = 60)
	private String name;
	
	@NotBlank
	@Column(name = "phoneNumber")
	@Size(max = 8, message = "{billing.phone.number.invalid}")
	@Pattern(regexp = "(6|7|2)[ -]*([0-9][ -]*){7}", message = "{billing.phone.number.invalid}")
	private String phoneNumber;
	
	@NotBlank
	@Column(name = "mobileNumber")
	@Size(max = 8, message = "{billing.phone.number.invalid}")
	@Pattern(regexp = "(6|7|2)[ -]*([0-9][ -]*){7}", message = "{billing.phone.number.invalid}")
	private String mobileNumber;
	
	@NotBlank
	@Size(max = 256)
	@Pattern(regexp = Constants.EMAIL_REGEX, message = "{billing.email.invalid}")
	@Column(name = "email")
	private String email;
	
	@NotBlank
	@Size(max = 10)
	private String dui;
	
	@NotBlank
	@Size(max = 18)
	private String nit;
	
	@NotBlank
	@Size(max = 12)
	private String afpNumber;
	
	@NotBlank
	@Size(max = 9)
	private String isssNumber;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_statusId")
	private EmployeeStatus status;
	
	
}
