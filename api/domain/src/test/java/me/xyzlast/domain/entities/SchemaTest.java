package me.xyzlast.domain.entities;

/**
 * Created by ykyoon on 14. 11. 6.
 */

import me.xyzlast.domain.DomainConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManagerFactory;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DomainConfiguration.class})
public class SchemaTest {
    @Autowired
    private Environment env;

    private Configuration buildConfiguration() {
        // create configuration using hibernate API
        Configuration configuration = new Configuration();
        configuration.setProperty("connection.driver_class", env.getProperty(DomainConfiguration.CONNECT_DRIVER));
        configuration.setProperty("hibernate.connection.url", env.getProperty(DomainConfiguration.CONNECT_URL));
        configuration.setProperty(DomainConfiguration.HIBERNATE_DIALECT, env.getProperty(DomainConfiguration.HIBERNATE_DIALECT));
        configuration.setProperty("hibernate.connection.username", env.getProperty(DomainConfiguration.CONNECT_USERNAME));
        configuration.setProperty("hibernate.connection.password", env.getProperty(DomainConfiguration.CONNECT_PASSWORD));

        configuration.addAnnotatedClass(Game.class);
        configuration.addAnnotatedClass(Player.class);
        configuration.addAnnotatedClass(PlayerResult.class);
        return configuration;
    }

    @Test
    public void generateSchema() throws Exception {
        Configuration configuration = buildConfiguration();
        SchemaExport schemaExport = new SchemaExport(configuration);

        final boolean printInConsole = true;
        final boolean executeToDb = false;
        schemaExport.setOutputFile("export.sql");

        System.out.println("\n=========== SCHEMA =============\n");
        schemaExport.create(false, false);
//        schemaExport.execute(printInConsole, executeToDb, true, true);
        System.out.println("\n========== END SCHEMA ===========\n");
    }
}
