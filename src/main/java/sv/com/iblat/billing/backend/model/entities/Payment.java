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
@Table(name = "payments")
public class Payment extends AbstractEntity{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "supplierId", nullable = false)
	private Supplier supplierId;
	
	@Basic(optional = false)
	@NotBlank	
	@Column(name = "invoiceNumber")
	private int invoiceNumber;
	
	@NotBlank
	@Basic(optional = false)
	@Column(name = "paymentAmount")
	private Double paymentAmount;
	
	@Column(name = "remainNumber")
	private int remainNumber;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "statusId", nullable = false)
	private Status statusId;
	
	@NotBlank
	@Basic(optional = false)
	@Column(name = "paymentDate")
	private LocalDateTime paymentDate;
	
	@NotBlank
	@Basic(optional = false)
	@Column(name = "issue_date")
	private LocalDateTime issueDate;
}
