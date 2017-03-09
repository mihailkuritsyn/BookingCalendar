package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import domain.BookingService;
import dto.BookingCalendar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import serialization.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeanConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer());
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());

        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    @Bean
    @Primary
    public RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(objectMapper());
        converters.add(jsonConverter);
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }


    @Bean
    public BookingCalendar bookingCalendar() {
        return new BookingCalendar();
    }

    @Bean
    public BookingService bookingService() {
        return new BookingService();
    }
}
