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
import java.util.Base64;

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
        final boolean executeToDb = true;
        schemaExport.setOutputFile("export.sql");

        System.out.println("\n=========== SCHEMA =============\n");
        schemaExport.create(false, true);
        schemaExport.execute(printInConsole, executeToDb, false, true);
        System.out.println("\n========== END SCHEMA ===========\n");
    }

    @Test
    public void convertStringFromBase64() throws Exception {
        String base64String = "PCFET0NUWVBFIGh0bWwgUFVCTElDICItLy9XM0MvL0RURCBYSFRNTCAxLjAgVHJhbnNpdGlvbmFs" +
                "Ly9FTiIgImh0dHA6Ly93d3cudzMub3JnL1RSL3hodG1sMS9EVEQveGh0bWwxLXRyYW5zaXRpb25h" +
                "bC5kdGQiPgo8aHRtbCB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94aHRtbCIgbGFuZz0n" +
                "ZW5fdXMnPgo8aGVhZD4KPG1ldGEgaHR0cC1lcXVpdj0iQ29udGVudC1UeXBlIiBjb250ZW50PSJ0" +
                "ZXh0L2h0bWw7IGNoYXJzZXQ9VVRGLTgiIC8+Cjx0aXRsZT5UZXN0IFRyYWNrZXIgdGVtcGxhdGU8" +
                "L3RpdGxlPgo8L2hlYWQ+Cjxib2R5Pgo8cD5IaSzCoDwvcD4KPHA+6rCA64KY64ukPC9wPgo8cD48" +
                "L3A+CjxwPuudvOuniOuwlDwvcD4KPGJyIC8+PHNwYW4gc3R5bGU9J2ZvbnQtc2l6ZTowLjhlbSc+" +
                "VG8gcmVtb3ZlIHlvdXJzZWxmIGZyb20gdGhpcyBlbWFpbCBsaXN0ICA8YSBocmVmPSdodHRwczov" +
                "L3ZkZ3dsYzM4NTcudHJpYWwuc3VnYXJjcm0uZXUvaW5kZXgucGhwP2VudHJ5UG9pbnQ9cmVtb3Zl" +
                "bWUmaWRlbnRpZmllcj1kNzg1MmVmNy0xOGYwLWRiMTktMjM3NS01NDYwM2ZjYjlhZDEnPmNsaWNr" +
                "IGhlcmU8L2E+PC9zcGFuPjxiciAvPjxpbWcgYWx0PScnIGhlaWdodD0nMScgd2lkdGg9JzEnIHNy" +
                "Yz0naHR0cHM6Ly92ZGd3bGMzODU3LnRyaWFsLnN1Z2FyY3JtLmV1L2luZGV4LnBocD9lbnRyeVBv" +
                "aW50PWltYWdlJmlkZW50aWZpZXI9ZDc4NTJlZjctMThmMC1kYjE5LTIzNzUtNTQ2MDNmY2I5YWQx" +
                "JyAvPgo8L2JvZHk+CjwvaHRtbD4=";
        String decodedString = new String(Base64.getDecoder().decode(base64String), "UTF-8");
        System.out.println(decodedString);
    }
}
