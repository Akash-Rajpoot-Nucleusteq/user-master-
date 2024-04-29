package in.nucleusteq.plasma.service;

import java.io.IOException;

import in.nucleusteq.plasma.dto.common.TokenResponce;
import in.nucleusteq.plasma.exception.TokenServiceException;

/**
 * TokenService provides methods for generating custom tokens.
 */
public interface TokenService {

    /**
     * Generates a custom token for the specified email.
     *
     * @param email The email for which the token is to be generated.
     * @return The generated token response.
     * @throws IOException           If an I/O error occurs during token generation.
     * @throws TokenServiceException If an error occurs during token generation.
     */
    TokenResponce getGenerateCustomToken(final String email) throws IOException, TokenServiceException;
}
