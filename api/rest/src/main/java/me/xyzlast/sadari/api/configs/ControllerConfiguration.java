package me.xyzlast.sadari.api.configs;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.NonTypedScalarSerializerBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ykyoon on 14. 11. 7.
 */
@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc
@ComponentScan(basePackages = {
        "me.xyzlast.sadari.api.resources",
        "me.xyzlast.sadari.api.aop",
        "me.xyzlast.sadari.api.utils"
})
public class ControllerConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public BeanNameViewResolver beanNameViewResolver() {
        return new BeanNameViewResolver();
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
