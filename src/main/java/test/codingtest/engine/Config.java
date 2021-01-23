package test.codingtest.engine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("test.codingtest.engine")
public class Config {
	
	@Bean
	public PromotionConfig getPromotionConfig() {
		return new PromotionConfig();
	}

}
