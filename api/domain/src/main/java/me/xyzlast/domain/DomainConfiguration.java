package me.xyzlast.domain;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.NonTypedScalarSerializerBase;
import com.zaxxer.hikari.HikariDataSource;
import me.xyzlast.domain.utils.ExternalEhCacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.aspectj.AnnotationTransactionAspect;

import javax.sql.DataSource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * Created by ykyoon on 14. 11. 6.
 */
@Configuration
@ComponentScan(basePackages = {
        "me.xyzlast.domain.utils",
        "me.xyzlast.domain.repositories",
        "me.xyzlast.domain.services"
})
@EnableJpaRepositories(basePackages = {"me.xyzlast.domain.repositories"}, transactionManagerRef = "transactionManager")
@PropertySources(value = {
        @PropertySource(value = "classpath:connection.properties"),
})
@EnableTransactionManagement
public class DomainConfiguration {
    public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    public static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    public static final String HIBERNATE_DIALECT = "hibernate.dialect";

    public static final String CONNECT_DRIVER = "connect.driver";
    public static final String CONNECT_USERNAME = "connect.username";
    public static final String CONNECT_PASSWORD = "connect.password";
    public static final String CONNECT_DATASOURCE = "connect.dataSource";
    public static final String CONNECT_URL = "connect.url";
    public static final String CONNECT_MAX = "connect.max";
    public static final String CONNECT_MIN = "connect.min";

    public static final String HIBERNATE_CACHE_USE_QUERY_CACHE = "hibernate.cache.use_query_cache";
    public static final String HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
    public static final String HIBERNATE_CACHE_REGION_FACTORY_CLASS = "hibernate.cache.region.factory_class";
    public static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    public static final String CREATE_DROP = "create-drop";
    public static final String CREATE = "create";
    public static final String GRID_CACHE_NAME = "hibernate-grid";

    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        System.setProperty("org.jboss.logging.provider", "slf4j");
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setFileEncoding("UTF-8");

        return propertySourcesPlaceholderConfigurer;
    }

    @Bean(destroyMethod = "shutdown")
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setUsername(env.getProperty(CONNECT_USERNAME));
        dataSource.setPassword(env.getProperty(CONNECT_PASSWORD));

        dataSource.setDataSourceClassName(env.getProperty(CONNECT_DATASOURCE));
        dataSource.addDataSourceProperty("url", env.getProperty(CONNECT_URL));
        int maxConnection = Integer.parseInt(env.getProperty(CONNECT_MAX));
        dataSource.setMaximumPoolSize(maxConnection);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPackagesToScan("me.xyzlast.domain.entities");
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        entityManagerFactory.setJpaProperties(getHibernateProperties());
        return entityManagerFactory;
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        boolean showSql = Boolean.valueOf(env.getProperty(HIBERNATE_SHOW_SQL));
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(showSql);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource());
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        AnnotationTransactionAspect.aspectOf().setTransactionManager(transactionManager);
        AnnotationTransactionAspect.aspectOf().setTransactionManagerBeanName("transactionManager");
        return transactionManager;
    }

    protected Properties getHibernateProperties() throws Exception {
        final Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT, env.getProperty(HIBERNATE_DIALECT));
        properties.put(HIBERNATE_SHOW_SQL, env.getProperty(HIBERNATE_SHOW_SQL));
        properties.put(HIBERNATE_FORMAT_SQL, env.getProperty(HIBERNATE_SHOW_SQL));

        ExternalEhCacheProvider.setCacheManager(ehCacheManagerFactoryBean().getObject());
        properties.put(HIBERNATE_CACHE_REGION_FACTORY_CLASS, "me.xyzlast.domain.utils.ExternalEhCacheProvider");
        properties.put(HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE, true);
        properties.put(HIBERNATE_CACHE_USE_QUERY_CACHE, true);

//        properties.put("hibernate.generate_statistics", true);
//        properties.put("hibernate.cache.use_structured_entries", true);

        return properties;
    }

    @Bean(name = "ehcache")
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() throws Exception {
        EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
        bean.setShared(Boolean.TRUE);
        bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        bean.afterPropertiesSet();

        return bean;
    }

    @Bean(name = "cacheManager")
    public EhCacheCacheManager cacheManager() throws Exception {
        EhCacheCacheManager ehCacheManager = new EhCacheCacheManager();
        ehCacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
        return ehCacheManager;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module = new SimpleModule("BooleanAsString", new com.fasterxml.jackson.core.Version(1, 0, 0, null, null, null));
        module.addSerializer(new NonTypedScalarSerializerBase<Boolean>(Boolean.class) {
            @Override
            public void serialize(Boolean value, JsonGenerator jgen, SerializerProvider provider)
                    throws IOException, JsonGenerationException {
                jgen.writeString(value.toString());
            }
        });
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return objectMapper;
    }
}
