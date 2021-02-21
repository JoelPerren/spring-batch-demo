package com.example.demo.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.demo.batch.SyntheticData;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class LoadSyntheticDataJobConfig {
	
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final EntityManagerFactory entityManagerFactory;
	
	@Bean
	public FlatFileItemReader<SyntheticData> csvReader() {
		return new FlatFileItemReaderBuilder<SyntheticData>()
				.name("csvItemReader")
				.resource(new ClassPathResource("/syntheticData.csv"))
				.delimited()
				.names(new String[]{"id", "geometry", "someBoolean", "someDate", "someNumber", "changeType"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<SyntheticData>() {{
					setTargetType(SyntheticData.class);
				}})
				.linesToSkip(1)
				.build();
	}
	
	@Bean
	public JpaItemWriter<SyntheticData> jpaItemWriter() {
		return new JpaItemWriterBuilder<SyntheticData>()
				.entityManagerFactory(entityManagerFactory)
				.build();
	}
	
	@Bean
	public Job importSyntheticDataJob(Step writeToDatabaseStep) {
		return jobBuilderFactory.get("importSyntheticDataJob")
				.incrementer(new RunIdIncrementer())
				.flow(writeToDatabaseStep)
				.end()
				.build();
	}
	
	@Bean
	public Step writeToDatabase(JpaItemWriter<SyntheticData> writer) {
		return stepBuilderFactory.get("writeToDatabase")
				.<SyntheticData, SyntheticData> chunk(100)
				.reader(csvReader())
				.writer(jpaItemWriter())
				.build();
	}

}
