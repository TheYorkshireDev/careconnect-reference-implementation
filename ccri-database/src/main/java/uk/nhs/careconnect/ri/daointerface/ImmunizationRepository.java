package uk.nhs.careconnect.ri.daointerface;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.ConditionalUrlParam;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.param.DateRangeParam;
import ca.uhn.fhir.rest.param.ReferenceParam;
import ca.uhn.fhir.rest.param.TokenParam;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.Immunization;
import org.hl7.fhir.dstu3.model.Patient;
import uk.nhs.careconnect.ri.entity.immunisation.ImmunisationEntity;

import java.util.List;

public interface ImmunizationRepository extends BaseDao<ImmunisationEntity, Immunization>  {
    void save(FhirContext ctx,ImmunisationEntity immunisation);

    Immunization read(FhirContext ctx, IdType theId);

    ImmunisationEntity readEntity(FhirContext ctx, IdType theId);

    Immunization create(FhirContext ctx,Immunization immunisation, @IdParam IdType theId, @ConditionalUrlParam String theImmunizational);


    List<Immunization> search(FhirContext ctx,

            @OptionalParam(name = Immunization.SP_PATIENT) ReferenceParam patient
            , @OptionalParam(name = Immunization.SP_DATE) DateRangeParam date
            , @OptionalParam(name = Immunization.SP_STATUS) TokenParam status
            , @OptionalParam(name = Immunization.SP_IDENTIFIER) TokenParam identifier
            ,@OptionalParam(name= Immunization.SP_RES_ID) TokenParam id

    );

    List<ImmunisationEntity> searchEntity(FhirContext ctx,
            @OptionalParam(name = Immunization.SP_PATIENT) ReferenceParam patient
            , @OptionalParam(name = Immunization.SP_DATE) DateRangeParam date
            , @OptionalParam(name = Immunization.SP_STATUS) TokenParam status
            , @OptionalParam(name = Immunization.SP_IDENTIFIER) TokenParam identifier
            ,@OptionalParam(name= Immunization.SP_RES_ID) TokenParam id
    );
}
