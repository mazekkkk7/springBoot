package cn.mazekkkk.product.controller;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = DispatcherServletAutoConfiguration.class)
@EnableScheduling
@EnableWebMvc
@Configuration
@EnableAutoConfiguration
//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(value = "cn.mazekkkk.product")
public class MazekkkkApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MazekkkkApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Bean(name = "root")
	public ServletRegistrationBean root() {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(ControllerConfig.class);
		dispatcherServlet.setApplicationContext(applicationContext);
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet, "/*");
		servletRegistrationBean.setLoadOnStartup(1);
		servletRegistrationBean.setName("root");
		return servletRegistrationBean;
	}

}