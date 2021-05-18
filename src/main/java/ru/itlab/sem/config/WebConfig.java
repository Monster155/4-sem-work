package ru.itlab.sem.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.itlab.sem.dto.ImageDTO;
import ru.itlab.sem.dto.userDTO.UserProfileDTO;
import ru.itlab.sem.models.Image;
import ru.itlab.sem.models.User;

import java.util.Base64;
import java.util.Locale;
import java.util.stream.Collectors;

@EnableWebMvc
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = {"ru.itlab.sem.repositories"})
@ComponentScan(basePackages = {"ru.itlab.sem"})
@EntityScan(basePackages = {"ru.itlab.sem"})
//public class WebConfig extends WebMvcConfigurerAdapter {
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setOrder(0);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setRedirectContextRelative(false);
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
        res.setBasenames("classpath:i18n/messages", "classpath:i18n/validation");
        res.setCacheSeconds(0);
        res.setDefaultEncoding("UTF-8");
        res.setUseCodeAsDefaultMessage(false);
        return res;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieName("lang");
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new AbstractConverter<User, UserProfileDTO>() {
            @Override
            protected UserProfileDTO convert(User user) {
                return new UserProfileDTO().builder()
                        .id(user.getId())
                        .photo(modelMapper.map(user.getPhoto(), ImageDTO.class))
                        .name(user.getName())
                        .surname(user.getSurname())
                        .fullname(user.getName() + " " + user.getSurname())
                        .nickname(user.getNickname())
                        .location(user.getLocation())
                        .images(
                                user.getImages().stream()
                                        .map(image -> modelMapper.map(image, ImageDTO.class))
                                        .collect(Collectors.toList()))
                        .followersCount(user.getFollowers().size())
                        .friendsCount(user.getFriends().size())
                        .build();
            }
        });
        modelMapper.addConverter(new AbstractConverter<Image, ImageDTO>() {
            @Override
            protected ImageDTO convert(Image image) {
                return new ImageDTO().builder()
                        .id(image.getId())
                        .photo(Base64.getEncoder().encodeToString(image.getPhoto()))
                        .build();
            }
        });
        return modelMapper;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/**").addResourceLocations("/WEB-INF/");
//        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }
}
