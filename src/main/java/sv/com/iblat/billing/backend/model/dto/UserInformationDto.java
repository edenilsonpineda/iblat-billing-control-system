package sv.com.iblat.billing.backend.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserInformationDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String userName;
	
	private String email;
	
	private LocalDateTime creationDate;
}
