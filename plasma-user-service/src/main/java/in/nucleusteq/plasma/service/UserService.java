package in.nucleusteq.plasma.service;

import java.util.Set;

import org.springframework.http.HttpHeaders;

import in.nucleusteq.plasma.dto.common.TokenResponce;
import in.nucleusteq.plasma.dto.in.LoginInDTO;
import in.nucleusteq.plasma.dto.out.LoginOutDTO;
import in.nucleusteq.plasma.exception.InvalidCredentialsException;
import in.nucleusteq.plasma.exception.ResourceNotFoundException;
import in.nucleusteq.plasma.entity.Role;

/**
 * UserService provides methods for user-related operations.
 */
public interface UserService {
    /**
     * Authenticates a user and returns login details.
     *
     * @param loginInDTO The login input data.
     * @return The login output data.
     * @throws InvalidCredentialsException If the credentials provided are invalid.
     * @throws ResourceNotFoundException   If the user record is not found.
     */
    LoginOutDTO login(LoginInDTO loginInDTO)
            throws InvalidCredentialsException, ResourceNotFoundException;

    /**
     * Adds authentication headers to the response.
     *
     * @param tokenResponse The token response containing authentication
     *                      information.
     * @return HttpHeaders containing authentication headers.
     */
    HttpHeaders addAuthHeaders(TokenResponce tokenResponce);

    /**
     * Gets the highest weighted role from the set of roles.
     *
     * @param set The set of roles.
     * @return The highest weighted role.
     */
    String getHighestWeightRole(Set<Role> set);

}
