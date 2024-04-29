package in.nucleusteq.plasma.feignservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import feign.RequestLine;
import feign.Response;
import in.nucleusteq.plasma.exception.TokenServiceException;
import in.nucleusteq.plasma.feignservice.model.TokenRequest;

/**
 * Feign client interface for interacting with the token microservice.
 */
@FeignClient(name = "tokenClient", url = "http://localhost:9099", fallback = FallbackTokenClientServiceClient.class, configuration = TokenClientConfiguration.class)
@Component
public interface TokenClientService {

    /**
     * Sends a request to generate a custom token.
     *
     * @param tokenRequest The token request containing user email.
     * @return The response containing the generated token.
     * @throws TokenServiceException if there is an error in obtaining the token.
     */
    @RequestLine("POST /plasma/token/generate-custom-token")
    Response getToken(TokenRequest tokenRequest) throws TokenServiceException;
}
