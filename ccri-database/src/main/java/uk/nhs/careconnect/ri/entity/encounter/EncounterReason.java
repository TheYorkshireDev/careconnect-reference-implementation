package uk.nhs.careconnect.ri.entity.encounter;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import uk.nhs.careconnect.ri.entity.Terminology.ConceptEntity;

import javax.persistence.*;

@Entity
@Table(name="EncounterReason", uniqueConstraints= @UniqueConstraint(name="PK_ENCOUNTER_REASON", columnNames={"ENCOUNTER_REASON_ID"})
        ,indexes = { @Index(name="IDX_ENCOUNTER_REASON", columnList = "REASON_CONCEPT_ID")}
)
public class EncounterReason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ENCOUNTER_REASON_ID")
    private Long Id;

    @ManyToOne
    @JoinColumn (name = "ENCOUNTER_ID",foreignKey= @ForeignKey(name="FK_ENCOUNTER_REASON_ENCOUNTER_ID"))
    private EncounterEntity encounter;

    @ManyToOne
    @JoinColumn(name="REASON_CONCEPT_ID", nullable = false, foreignKey= @ForeignKey(name="FK_ENCOUNTER_REASON_CONCEPT_ID"))
    @LazyCollection(LazyCollectionOption.TRUE)
    private ConceptEntity reason;

    public void setId(Long id) {
        Id = id;
    }

    public Long getId() {
        return Id;
    }

    public void setEncounter(EncounterEntity encounter) {
        this.encounter = encounter;
    }

    public ConceptEntity getReason() {
        return reason;
    }

    public EncounterReason setReason(ConceptEntity reason) {
        this.reason = reason;
        return this;
    }

}
