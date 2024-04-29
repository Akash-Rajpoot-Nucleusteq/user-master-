package in.nucleusteq.plasma.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import in.nucleusteq.plasma.constants.ResponceConstants;
import in.nucleusteq.plasma.dao.EmployeeRepository;
import in.nucleusteq.plasma.dto.common.TokenResponce;
import in.nucleusteq.plasma.dto.in.LoginInDTO;
import in.nucleusteq.plasma.dto.out.LoginOutDTO;
import in.nucleusteq.plasma.exception.InvalidCredentialsException;
import in.nucleusteq.plasma.exception.ResourceNotFoundException;
import in.nucleusteq.plasma.mapper.ModelMapperAdapter;
import in.nucleusteq.plasma.entity.Employee;
import in.nucleusteq.plasma.entity.Role;
import in.nucleusteq.plasma.service.UserService;

/**
 * UserServiceImpl provides implementation for user-related operations.
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * The ModelMapperAdapter for mapping objects.
     */
    @Autowired
    private ModelMapperAdapter mapper;
    /**
     * The EmployeeRepository for accessing employee data.
     */
    @Autowired
    private EmployeeRepository employeeRepository;
    /**
     * The logger object for creating system logs.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    /**
     * Authenticates a user and returns login details.
     *
     * @param loginInDTO The login input data.
     * @return The login output data.
     * @throws InvalidCredentialsException If the credentials provided are invalid.
     * @throws ResourceNotFoundException   If the user record is not found.
     */
    @Override
    public LoginOutDTO login(LoginInDTO loginInDTO) throws InvalidCredentialsException, ResourceNotFoundException {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(loginInDTO.getEmail().toLowerCase());
        if (optionalEmployee.isEmpty()) {
            LOGGER.error("Login failed, record not found : {}", loginInDTO);
            throw new ResourceNotFoundException(ResponceConstants.USER_NOT_FOUND);
        }
        Employee employee = optionalEmployee.get();
        if (!BCrypt.checkpw(String.valueOf(loginInDTO.getPassword()), String.valueOf(employee.getPassword()))) {
            LOGGER.error("Login failed for details : {}", loginInDTO);
            throw new InvalidCredentialsException(ResponceConstants.INVALID_EMAIL_OR_PASSWORD);
        }
        LoginOutDTO loginOutDTO = LoginOutDTO.builder()
                .email(employee.getEmail())
                .role(getHighestWeightRole(employee.getUserWorkDetail().getRoles()))
                .build();

        return loginOutDTO;

    }

    /**
     * Gets the highest weighted role from the set of roles.
     *
     * @param set The set of roles.
     * @return The highest weighted role.
     */
    @Override
    public final String getHighestWeightRole(Set<Role> set) {
        String highestWeightRole = null;

        Map<String, Integer> roleWeights = new HashMap<>();
        roleWeights.put("Super_Admin", 4);
        roleWeights.put("ADMIN", 3);
        roleWeights.put("MANAGER", 2);
        roleWeights.put("EMPLOYEE", 1);

        int maxWeight = Integer.MIN_VALUE;

        for (Role role : set) {
            String roleName = role.getName();
            if (roleWeights.containsKey(roleName)) {
                int weight = roleWeights.get(roleName);
                if (weight > maxWeight) {
                    maxWeight = weight;
                    highestWeightRole = roleName;
                }
            }
        }
        return highestWeightRole;
    }

    /**
     * Adds authentication headers to the response.
     *
     * @param tokenResponse The token response containing authentication
     *                      information.
     * @return HttpHeaders containing authentication headers.
     */
    @Override
    public HttpHeaders addAuthHeaders(TokenResponce tokenResponce) {
        HttpHeaders responceHeaders = new HttpHeaders();
        responceHeaders.set("access-control-expose-headers",
                "x-auth-plasma-code, x-auth-plasma-expiry, x-auth-plasma-refresh-code");
        responceHeaders.set("Access-Control-Allow-Headers", "Accept");
        responceHeaders.set("Access-Control-Allow-Methods", "OPTIONS,POST");
        responceHeaders.set("Content-type", "application/json; charset=utf-8");
        responceHeaders.set("x-auth-plasma-code", tokenResponce.getAccessCode());
        responceHeaders.set("x-auth-plasma-expiry", tokenResponce.getAccessCodeExpiry().toString());
        responceHeaders.set("x-auth-plasma-refresh-code", tokenResponce.getRefreshToken());
        return responceHeaders;
    }

}
