package sv.com.iblat.billing.backend.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "customers")
public class Customer extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Column(name = "name")
	@Size(max = 60)
	private String name;
	
	@NotBlank
	@Column(name = "phoneNumber")
	@Size(max = 8, message = "{billing.phone.numer.invalid}")
	@Pattern(regexp = "(6|7|2)[ -]*([0-9][ -]*){7}", message = "{billing.phone.numer.invalid}")
	private String phoneNumber;
	
	@NotBlank
	@Column(name = "contactName")
	@Size(max = 50)
	private String contactName;

}
