package study.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Persolute
 * @version 1.0
 * @description MybatisPlus Config
 * @email 1538520381@qq.com
 * @date 2024/2/14 18:58
 */
@Configuration
public class MybatisPlusConfig {
    /*
     * @author Persolute
     * @version 1.0
     * @description 分页查询插件
     * @email 1538520381@qq.com
     * @date 2024/2/14 18:59
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
