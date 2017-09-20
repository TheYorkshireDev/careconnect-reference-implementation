package uk.nhs.careconnect.ri.provider;

import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.method.RequestDetails;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import org.hl7.fhir.instance.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.nhs.careconnect.ri.OperationOutcomeFactory;
import uk.nhs.careconnect.ri.dao.Organisation.OrganisationRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class OrganizationResourceProvider implements IResourceProvider {

    @Autowired
    private OrganisationRepository organisationDao;

    @Override
    public Class<Organization> getResourceType() {
        return Organization.class;
    }


    @Update
    public MethodOutcome updateOrganization(HttpServletRequest theRequest,@ResourceParam Organization organization, @IdParam IdType theId, @ConditionalUrlParam String theConditional, RequestDetails theRequestDetails) {


        MethodOutcome method = new MethodOutcome();
        method.setCreated(true);
        OperationOutcome opOutcome = new OperationOutcome();

        method.setOperationOutcome(opOutcome);


        Organization newOrganization = organisationDao.create(organization, theId, theConditional);
        method.setId(newOrganization.getIdElement());
        method.setResource(newOrganization);



        return method;
    }

    @Read()
    public Organization getOrganizationById(@IdParam IdType organisationId) {
        Organization organisation = organisationDao.read(organisationId);

        if ( organisation == null) {
            throw OperationOutcomeFactory.buildOperationOutcomeException(
                    new ResourceNotFoundException("No Organization/" + organisationId.getIdPart()),
                    OperationOutcome.IssueSeverity.ERROR, OperationOutcome.IssueType.NOTFOUND);
        }
        return organisation;
    }

    @Search
    public List<Organization> searchOrganisation(@RequiredParam(name = Organization.SP_IDENTIFIER) TokenParam identifier,
                                                 @OptionalParam(name = Location.SP_NAME) StringParam name) {
       return organisationDao.searchOrganization(identifier,name);
    }


    

}
