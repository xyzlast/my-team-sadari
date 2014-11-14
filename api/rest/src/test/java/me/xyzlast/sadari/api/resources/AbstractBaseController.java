package me.xyzlast.sadari.api.resources;

import me.xyzlast.domain.DomainConfiguration;
import me.xyzlast.sadari.api.configs.ControllerConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by ykyoon on 14. 11. 15.
 */
@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DomainConfiguration.class, ControllerConfiguration.class })
@WebAppConfiguration
public abstract class AbstractBaseController {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    public MockMvc getMockMvc() {
        return this.mockMvc;
    }

    protected void init() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(characterEncodingFilter).build();
    }
}
