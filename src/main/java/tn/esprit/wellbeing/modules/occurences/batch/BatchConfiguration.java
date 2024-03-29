package tn.esprit.wellbeing.modules.occurences.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import tn.esprit.wellbeing.modules.occurences.models.Activity;
import tn.esprit.wellbeing.modules.occurences.models.ActivityItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Bean
  public FlatFileItemReader<Activity> reader() {
    return new FlatFileItemReaderBuilder<Activity>()
      .name("activityItemReader")
      .resource(new ClassPathResource("sample-data.csv"))
      .delimited()
      .names(new String[]{"id","title", "description"})
      .fieldSetMapper(new BeanWrapperFieldSetMapper<Activity>() {{
        setTargetType(Activity.class);
      }})
      .build();
  }

  @Bean
  public ActivityItemProcessor processor() {
    return new ActivityItemProcessor();
  }

  @Bean
  public JdbcBatchItemWriter<Activity> writer(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Activity>()
      .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
      .sql("INSERT INTO activity (id,title, description) VALUES (:id, :title, :description)")
      .dataSource(dataSource)
      .build();
  }
  
  @Bean
  public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
    return jobBuilderFactory.get("importActivityJob")
      .incrementer(new RunIdIncrementer())
      .listener(listener)
      .flow(step1)
      .end()
      .build();
  }

  @Bean
  public Step step1(JdbcBatchItemWriter<Activity> writer) {
    return stepBuilderFactory.get("step1")
      .<Activity, Activity> chunk(10)
      .reader(reader())
      .processor(processor())
      .writer(writer)
      .build();
  }

}