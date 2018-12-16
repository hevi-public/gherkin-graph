package hu.hevi.gherkingraph;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

    @Value("${feature.location}")
    private String featureLocation;

    public List<String> getFeatureLocations() {
        return Arrays.asList(featureLocation.split(","));
    }
}
