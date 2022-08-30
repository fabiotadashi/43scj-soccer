package br.com.fiap.soccerbatch;

import br.com.fiap.soccerbatch.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.File;
import java.nio.file.Paths;

@SpringBootApplication
@EnableBatchProcessing
public class SoccerBatchChunkApplication {

    Logger logger = LoggerFactory.getLogger(SoccerBatchChunkApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(SoccerBatchChunkApplication.class, args);
    }

    @Bean
    public ItemReader<User> itemReader(@Value("${file.resource}") Resource resource) {
        return new FlatFileItemReaderBuilder<User>()
                .name("User item reader")
                .delimited().delimiter(";").names("name", "cpf")
                .resource(resource)
                .targetType(User.class)
                .build();
    }

    @Bean
    public ItemProcessor<User, User> itemProcessor() {
        return user -> {
            user.setName(user.getName().toUpperCase());
            user.setCpf(user.getCpf().replaceAll("[^\\d]", ""));
            return user;
        };
    }

    @Bean
    public ItemWriter<User> itemWriter(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<User>()
                .dataSource(dataSource)
                .beanMapped()
                .sql("insert into TB_USER(name, cpf) values (:name, :cpf)")
                .build();
    }

    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory,
                     ItemReader<User> itemReader,
                     ItemProcessor<User, User> itemProcessor,
                     ItemWriter<User> itemWriter){
        return stepBuilderFactory.get("csv to database step")
                .<User, User>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   Step step){
        return jobBuilderFactory.get("csv2db job")
                .start(step)
                .build();
    }

}
