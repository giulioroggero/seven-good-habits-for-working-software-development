package eu.miaplatform.dbsqlreader.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Configuration
public class ReaderConfig {
	final static Logger logger = LoggerFactory.getLogger(ReaderConfig.class);

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "reader.driver";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "reader.user";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "reader.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "reader.url";
	private static final String PROPERTY_NAME_PROFILES_DIR = "reader.profiles.dir";

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final String PROPERTY_NAME_DATE_FORMAT = "reader.dateFormat";


	@Resource
	private Environment env;

	public ReaderConfig() {

	}

	public ReaderConfig(Environment env) {
		this.env = env;
	}

	@ConfigurationProperties
	@Bean
	public DataSource dataSource() {

		DataSourceBuilder factory = DataSourceBuilder
						.create()
						.driverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER))
						.url(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL))
						.username(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME))
						.password(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		return factory.build();
	}

	@Bean
	public MappingJackson2HttpMessageConverter jsonConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		String dateFormat = null;

		try {
			dateFormat = env.getRequiredProperty(PROPERTY_NAME_DATE_FORMAT);
			Assert.notNull(dateFormat);
		} catch (Exception e) {
			logger.warn("Unable to retrieve custom date format, using UTC default format");
			logger.warn(e.getMessage());
			dateFormat = DEFAULT_DATE_FORMAT;
		}

		ReaderOutputMapper readerOutputMapper = new ReaderOutputMapper(dateFormat);
		converter.setObjectMapper(readerOutputMapper.getMapper());
		return converter;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
