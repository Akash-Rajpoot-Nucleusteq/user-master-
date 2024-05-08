package in.nucleusteq.plasma.configration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
    
    /**
     * Configures and provides the DataSource bean for the application.
     *
     * @return DataSource bean configured with PostgreSQL driver, URL, username, and password.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/plasma");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");
        return dataSource;
    }
    /**
     * Configures and provides the EntityManagerFactory bean for the application.
     *
     * @return LocalContainerEntityManagerFactoryBean configured with DataSource, JpaVendorAdapter,
     *         and packages to scan for entity classes.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        em.setPackagesToScan("in.nucleusteq.plasma");
        return em;
    }
    /**
     * Configures and provides the PlatformTransactionManager bean for the application.
     *
     * @param emf EntityManagerFactory bean for transaction management.
     * @return PlatformTransactionManager bean for managing JPA transactions.
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    /**
     * Provides additional Hibernate properties for configuring the EntityManagerFactory.
     *
     * @return Properties object containing Hibernate properties.
     */
    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.format_sql", "false");
        properties.setProperty("hibernate.type.descriptor.sql.BasicBinder", "TRACE");
        return properties;
    }
}
