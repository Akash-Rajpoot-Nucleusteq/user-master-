/**
 * Controller class for handling user-related HTTP requests and responses.
 */
package in.nucleusteq.plasma.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonSyntaxException;

import in.nucleusteq.plasma.dto.common.TokenResponce;
import in.nucleusteq.plasma.dto.in.LoginInDTO;
import in.nucleusteq.plasma.dto.out.LoginOutDTO;
import in.nucleusteq.plasma.exception.InvalidCredentialsException;
import in.nucleusteq.plasma.exception.ResourceNotFoundException;
import in.nucleusteq.plasma.exception.TokenServiceException;
import in.nucleusteq.plasma.exception.UnauthorizedAccessException;
import in.nucleusteq.plasma.service.TokenService;
import in.nucleusteq.plasma.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/plasma")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;
    /**
     * The logger object created for generating system logs.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    /**
     * Handles HTTP POST requests for user login.
     *
     * @param loginInDTO The login details provided by the user.
     * @return ResponseEntity containing the login response and authentication headers.
     * @throws InvalidCredentialsException   if the provided credentials are invalid.
     * @throws ResourceNotFoundException    if the requested resource is not found.
     * @throws JsonSyntaxException           if an error occurs during JSON parsing.
     * @throws UnauthorizedAccessException   if the user is not authorized to access the resource.
     * @throws IOException                   if an I/O error occurs.
     * @throws TokenServiceException         if an error occurs during token generation.
     */
    @PostMapping(path = "/login")
    public ResponseEntity<LoginOutDTO> doLogin(@RequestBody @Valid final LoginInDTO loginInDTO) throws InvalidCredentialsException, ResourceNotFoundException, JsonSyntaxException, UnauthorizedAccessException,
            IOException, TokenServiceException {
        LOGGER.info("Login started for email : {}", loginInDTO.getEmail());
        LoginOutDTO loginOutDTO = userService.login(loginInDTO);
        TokenResponce tokenResponce  = tokenService.getGenerateCustomToken(loginOutDTO.getEmail());
        HttpHeaders headers = userService.addAuthHeaders(tokenResponce);
        LOGGER.info("Login completed for email : {}", loginInDTO.getEmail());
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(loginOutDTO);
    }
}
