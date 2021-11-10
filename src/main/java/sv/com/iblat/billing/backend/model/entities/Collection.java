package sv.com.iblat.billing.backend.model.entities;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "collections")
public class Collection extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
	@NotBlank	
	@Column(name = "invoiceNumber")
	private int invoiceNumber;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "customerId", nullable = false)
	private Customer customer;
	
	@NotBlank
	@Basic(optional = false)
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "remainNumber")
	private int remainNumber;
	
	@NotBlank
	@Basic(optional = false)
	@Column(name = "paymentDate")
	private LocalDateTime paymentDate;
	
	@NotBlank
	@Basic(optional = false)
	@Column(name = "issue_date")
	private LocalDateTime issueDate;
	
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "statusId", nullable = false)
	private Status statusId;
	
}
