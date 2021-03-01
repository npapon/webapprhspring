package fr.papounworld.webapprh.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.Getter;

@Data
@Configuration
@ConfigurationProperties(prefix="fr.papounworld.yoda")
public class CustomProperties {
	
	
	private String apiUrl;

}
