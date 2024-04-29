package in.nucleusteq.plasma.feignservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.Response;
import in.nucleusteq.plasma.exception.TokenServiceException;
import in.nucleusteq.plasma.feignservice.model.TokenRequest;

/**
 * Fallback implementation of the TokenClientService interface.
 * This class provides a response when the token microservice is unable to
 * provide the token for a particular user.
 */
@Component
public class FallbackTokenClientServiceClient implements TokenClientService {

    /**
     * Logger for logging messages.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FallbackTokenClientServiceClient.class);

    /**
     * Provides a fallback response when the token microservice is unable to provide
     * the token.
     *
     * @param tokenRequest The token request.
     * @return A response indicating that the service is unable to connect.
     * @throws TokenServiceException if there is an error in obtaining the token.
     */
    @Override
    public Response getToken(TokenRequest tokenRequest) throws TokenServiceException {
        LOGGER.info("Unable to connect to service");
        throw new TokenServiceException("Unable to connect to service");
    }
}
