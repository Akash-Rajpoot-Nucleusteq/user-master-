package in.nucleusteq.plasma.service.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import feign.Response;
import feign.form.util.CharsetUtil;
import in.nucleusteq.plasma.constants.ResponceConstants;
import in.nucleusteq.plasma.dto.common.TokenResponce;
import in.nucleusteq.plasma.exception.TokenServiceException;
import in.nucleusteq.plasma.exception.UnauthorizedAccessException;
import in.nucleusteq.plasma.feignservice.TokenClientService;
import in.nucleusteq.plasma.feignservice.model.TokenRequest;
import in.nucleusteq.plasma.service.TokenService;

/**
 * TokenServiceImpl provides methods for generating custom tokens.
 */
@Service
public class TokenServiceImpl implements TokenService {

    /**
     * Gson object for JSON serialization and deserialization.
     */
    private static final Gson GSON = new Gson();

    /**
     * The TokenClientService for interacting with the token generation service.
     */
    @Autowired
    private TokenClientService tokenClientService;

    /**
     * Logger for logging messages.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

    /**
     * Generates a custom token for the given email.
     *
     * @param email The email for which the token is to be generated.
     * @return The generated token response.
     * @throws UnauthorizedAccessException If the email does not have a valid status.
     * @throws IOException                 If an I/O error occurs while processing the token response.
     * @throws TokenServiceException       If an error occurs during token generation.
     */
    @Override
    public TokenResponce getGenerateCustomToken(String email)
            throws UnauthorizedAccessException, IOException, TokenServiceException {
        Response tokenResponse = tokenClientService.getToken(new TokenRequest(email));
        if (tokenResponse.status() == HttpStatus.UNAUTHORIZED.value()) {
            LOGGER.error("Email id {} does not have a valid status {}. Please contact Plasma team"
                    + " support for more information.", email, tokenResponse.status());
            throw new UnauthorizedAccessException(String.format(ResponceConstants.INVALID_ACCOUNT_STATUS, email));
        }
        TokenResponce response = GSON.fromJson(tokenResponse.body().asReader(CharsetUtil.UTF_8), TokenResponce.class);
        return response;
    }
}