package uk.nhs.careconnect.ri.fhirserver.provider;


import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.ReferenceParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import org.hl7.fhir.dstu3.model.DocumentReference;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.OperationOutcome;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.nhs.careconnect.ri.daointerface.DocumentReferenceRepository;
import uk.nhs.careconnect.ri.lib.OperationOutcomeFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class DocumentReferenceProvider implements ICCResourceProvider {



    @Autowired
    private DocumentReferenceRepository documentReferenceDao;

    @Autowired
    FhirContext ctx;

    @Override
    public Long count() {
        return documentReferenceDao.count();
    }

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return DocumentReference.class;
    }


    @Update
    public MethodOutcome update(HttpServletRequest theRequest, @ResourceParam DocumentReference documentReference, @IdParam IdType theId, @ConditionalUrlParam String theConditional, RequestDetails theRequestDetails) {

        MethodOutcome method = new MethodOutcome();
        method.setCreated(true);
        OperationOutcome opOutcome = new OperationOutcome();
        method.setOperationOutcome(opOutcome);

        DocumentReference newDocumentReference = documentReferenceDao.create(ctx,documentReference, theId, theConditional);
        method.setId(newDocumentReference.getIdElement());
        method.setResource(newDocumentReference);

        return method;
    }

    @Create
    public MethodOutcome create(HttpServletRequest theRequest, @ResourceParam DocumentReference documentReference) {

        MethodOutcome method = new MethodOutcome();
        method.setCreated(true);
        OperationOutcome opOutcome = new OperationOutcome();
        method.setOperationOutcome(opOutcome);

        DocumentReference newDocumentReference = documentReferenceDao.create(ctx,documentReference, null,null);
        method.setId(newDocumentReference.getIdElement());
        method.setResource(newDocumentReference);

        return method;
    }

    @Search
    public List<DocumentReference> search(HttpServletRequest theRequest,
                                  @OptionalParam(name = DocumentReference.SP_PATIENT) ReferenceParam patient
            , @OptionalParam(name = DocumentReference.SP_IDENTIFIER) TokenParam identifier
            , @OptionalParam(name = DocumentReference.SP_RES_ID) TokenParam resid
                                  ) {
        return documentReferenceDao.search(ctx,patient,identifier,resid);
    }

    @Read()
    public DocumentReference get(@IdParam IdType documentReferenceId) {

        DocumentReference documentReference = documentReferenceDao.read(ctx,documentReferenceId);

        if ( documentReference == null) {
            throw OperationOutcomeFactory.buildOperationOutcomeException(
                    new ResourceNotFoundException("No DocumentReference/ " + documentReferenceId.getIdPart()),
                    OperationOutcome.IssueSeverity.ERROR, OperationOutcome.IssueType.NOTFOUND);
        }

        return documentReference;
    }


}
