package ua.epam.spring.hometask;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ua.epam.spring.hometask.domain.Auditorium;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by dish on 15.10.17.
 */

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages="ua.epam.spring")
@PropertySource(
    value={"classpath:application.properties", "classpath:auditorium_a.properties", "classpath:auditorium_b.properties", "classpath:auditorium_o.properties"})
public class Config implements ApplicationContextAware {
    
    private ApplicationContext applicationContext;
    @Autowired
    DataSource dataSource;
    @Bean(name="auditoriumMap")
    public Map<String, Auditorium> auditoriumMap(){
        Environment env = applicationContext.getEnvironment();
        env.getProperty("b.name");
        env.getProperty("b.number_of_seats");
        env.getProperty("b.vip_seats");
        env.getProperty("o.name");
        env.getProperty("o.number_of_seats");
        env.getProperty("o.vip_seats");
        Set<Long> aSeats = getSeats(env, "a.vip_seats");
        Set<Long> bSeats = getSeats(env, "b.vip_seats");
        Set<Long> oSeats = getSeats(env, "o.vip_seats");
        Auditorium a = new Auditorium(env.getProperty("a.name"), Long.parseLong(env.getProperty("a.number_of_seats")), aSeats);
        Auditorium b = new Auditorium(env.getProperty("b.name"), Long.parseLong(env.getProperty("b.number_of_seats")), bSeats);
        Auditorium o = new Auditorium(env.getProperty("o.name"), Long.parseLong(env.getProperty("o.number_of_seats")), oSeats);
        Map<String, Auditorium> auditoriumMap = new HashMap<>();
        auditoriumMap.put("Alfa", a);
        auditoriumMap.put("Beta", b);
        auditoriumMap.put("Omega", o);
        return auditoriumMap;
    }
    
    private Set<Long> getSeats(Environment env, String pName) {
        return Arrays.stream(env.getProperty(pName).split(","))
            .map(e -> Long.parseLong(e))
            .collect(Collectors.toSet());
    }
    
    @Bean
    public PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public NamedParameterJdbcTemplate getNamedJdbcTemplate(DataSource dataSource) {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        return jdbcTemplate;
    }
    
    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
          jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }
    @Bean
    public DataSource dataSource() {
        Environment env = applicationContext.getEnvironment();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
        dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
        return dataSource;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
/**
 Initial 100
 -1 redundant Autowired in App clas
 -0 why do you have getters for beans fields in ApplicationLogic?
 -10 still 1 strategy and is no 2nd one, which is required and needed to well do other HWs
 -5 you have the simplest strategy and event this one doesn't work correct :(
 -5 as you do not have 2nd strategy I do not see sense in Discount Service, at least it should be done with possibility to easy add 2nd strategy (you could add at least some fake strategy which return 0 discount always)

 aspects:
 -10 nothinh in discount aspect
 -5 no aspect to count how many events were ACCESSED by its names
 -5 incorrect aspect for count how many tickets were booked for certain event

 -5 as whole work looks not well, contains missed/ignored parts I have to apply at least little deadline penalty

 sum:
 100 -1 -10 -5 -5 -10 -5 -5 -5=
 100 -1 -10*2 -5*5 = 54
 */