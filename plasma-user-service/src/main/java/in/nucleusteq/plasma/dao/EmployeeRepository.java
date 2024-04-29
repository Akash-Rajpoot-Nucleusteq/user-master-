/**
 * Repository interface for accessing and managing Employee entities in the database.
 */
package in.nucleusteq.plasma.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nucleusteq.plasma.entity.Employee;

/**
 * EmployeeRepository.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{
    /**
     * Retrieves an Employee entity by the specified employee ID.
     *
     * @param employeeId The ID of the employee to retrieve.
     * @return Optional containing the Employee entity if found, otherwise empty.
     */
    Optional<Employee> findByUserId(String employeeId);
    /**
     * Retrieves an Employee entity by the specified email address.
     *
     * @param userName The email address of the employee to retrieve.
     * @return The Employee entity corresponding to the email address.
     */
    Employee getByEmail(String userName);
        /**
     * Finds an Employee entity by the specified email address.
     *
     * @param email The email address of the employee to find.
     * @return Optional containing the Employee entity if found, otherwise empty.
     */
    Optional<Employee> findByEmail(String email);

}
