package sv.com.iblat.billing.backend.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.google.gson.GsonBuilder;

import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7718185424808270343L;

	@Id
	@GeneratedValue
	private Long id;
	
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	

	@Basic(optional = false)
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@PrePersist
	public void prePersist() {
		this.updatedAt = LocalDateTime.now();
		this.createdAt = LocalDateTime.now();
	}
	
	
	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
	
	@Override
	public String toString() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}
	

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		
		return false;
	}
	
}
