package uk.nhs.careconnect.ri.entity.procedure;

import uk.nhs.careconnect.ri.entity.BaseIdentifier;

import javax.persistence.*;


@Entity
@Table(name="ProcedureIdentifier", uniqueConstraints= @UniqueConstraint(name="PK_PROCEDURE_IDENTIFIER", columnNames={"PROCEDURE_IDENTIFIER_ID"})
		,indexes =
		{
				@Index(name = "IDX_PROCEDURE_IDENTIFER", columnList="value,SYSTEM_ID")

		})
public class ProcedureIdentifier extends BaseIdentifier {

	public ProcedureIdentifier() {

	}

	public ProcedureIdentifier(ProcedureEntity procedure) {
		this.procedure = procedure;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "PROCEDURE_IDENTIFIER_ID")
	private Long identifierId;

	@ManyToOne
	@JoinColumn (name = "PROCEDURE_ID",foreignKey= @ForeignKey(name="FK_PROCEDURE_PROCEDURE_IDENTIFIER"))
	private ProcedureEntity procedure;


    public Long getIdentifierId() { return identifierId; }
	public void setIdentifierId(Long identifierId) { this.identifierId = identifierId; }

	public ProcedureEntity getProcedure() {
	        return this.procedure;
	}

	public void setProcedure(ProcedureEntity procedure) {
	        this.procedure = procedure;
	}




}
