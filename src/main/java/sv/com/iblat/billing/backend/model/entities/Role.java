package sv.com.iblat.billing.backend.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.NoArgsConstructor;

import lombok.Setter;

import lombok.Getter;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor
public class Role extends AbstractEntity {
	
	private static final long serialVersionUID = 1451801111012407709L;

	@NotBlank
	@Size(max = 15)
	@Column(name = "description")
	private String description;
}
