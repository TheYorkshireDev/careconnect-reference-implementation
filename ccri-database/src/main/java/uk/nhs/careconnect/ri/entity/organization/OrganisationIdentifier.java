package uk.nhs.careconnect.ri.entity.organization;

import uk.nhs.careconnect.ri.entity.BaseIdentifier;

import javax.persistence.*;


@Entity
@Table(name="OrganisationIdentifier", uniqueConstraints= @UniqueConstraint(name="PK_ORGANISATION_IDENTIFIER", columnNames={"ORGANISATION_IDENTIFIER_ID"}),indexes =
		{
				@Index(name = "IDX_ORGANISATION_IDENTIFER", columnList="value,SYSTEM_ID")

		})
public class OrganisationIdentifier extends BaseIdentifier {

	public OrganisationIdentifier() {

	}

	public OrganisationIdentifier(OrganisationEntity organisationEntity) {
		this.organisationEntity = organisationEntity;
	}
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "ORGANISATION_IDENTIFIER_ID")
	private Integer identifierId;


	@ManyToOne
	@JoinColumn (name = "ORGANISATION_ID",foreignKey= @ForeignKey(name="FK_ORGANISATION_ORGANISATION_IDENTIFIER"))
	private OrganisationEntity organisationEntity;


    public Integer getIdentifierId() { return identifierId; }
	public void setIdentifierId(Integer identifierId) { this.identifierId = identifierId; }

	public OrganisationEntity getOrganisation() {
	        return this.organisationEntity;
	}
	public void setOrganisation(OrganisationEntity organisationEntity) {
	        this.organisationEntity = organisationEntity;
	}


}
