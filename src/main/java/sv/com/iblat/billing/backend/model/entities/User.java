package sv.com.iblat.billing.backend.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 30)
	@Basic(optional = false)
	@Column(name = "userName")
	private String userName;

	@NotBlank
	@Basic(optional = false)
	@Size(min = 4, max = 256)
	@Column(name = "password")
	private String password;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name  = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private Set<Role> roles = new HashSet<>();

	@NotBlank
	@Size(max = 256)
	@Column(name = "email")
	private String email;

	@Basic(optional = false)
	@Column(name = "enabled")
	private boolean isEnabled;

}
