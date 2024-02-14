package study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import study.common.JacksonObjectMapper;

import java.util.List;

/**
 * @author Persolute
 * @version 1.0
 * @description WebMvc Config
 * @email 1538520381@qq.com
 * @date 2024/2/14 16:28
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /*
     * @author Persolute
     * @version 1.0
     * @description 消息体处理器
     * @email 1538520381@qq.com
     * @date 2024/2/14 16:31
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(new JacksonObjectMapper());
        converters.add(0, mappingJackson2HttpMessageConverter);
    }
}
