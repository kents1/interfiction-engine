package uk.co.engine.interfiction.module.account.config;

import org.jdbi.v3.spring4.JdbiFactoryBean;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static java.util.Collections.singletonList;

@Configuration
@PropertySource("classpath:module-account-infrastructure.yaml")
@ConfigurationProperties(prefix = "spring.datasource.account")
public class DataConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataConfig.class);

    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${url}")
    private String url;

    @Primary
    @Bean
    public DriverManagerDataSource driverManagerDataSource() {
        LOGGER.debug("Connecting to {}", url);
        return new DriverManagerDataSource(url, username, password);
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DriverManagerDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JdbiFactoryBean dbi(DriverManagerDataSource dataSource) {
        final JdbiFactoryBean factoryBean = new JdbiFactoryBean(dataSource);
        factoryBean.setAutoInstallPlugins(true);
        factoryBean.setPlugins(singletonList(new SqlObjectPlugin()));
        return factoryBean;
    }

}
