package com.packtpub.yummy.reverseproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.post.LocationRewriteFilter;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class ReverseproxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReverseproxyApplication.class, args);
	}

	@Bean
	public LocationRewriteFilter locationRewriteFilter() {
		return new LocationRewriteFilter();
	}

}
