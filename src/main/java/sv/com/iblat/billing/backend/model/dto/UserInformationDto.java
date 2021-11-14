package sv.com.iblat.billing.backend.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.Builder;
import lombok.Data;
import sv.com.iblat.billing.backend.model.entities.Role;

@Builder
@Data
public class UserInformationDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private long userId;
	
	private String userName;
	
	private String email;
	
	private LocalDateTime creationDate;
	
	private Set<Role> roles;
}
