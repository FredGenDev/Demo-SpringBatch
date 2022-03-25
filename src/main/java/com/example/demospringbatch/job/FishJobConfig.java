package com.example.demospringbatch.job;

import com.example.demospringbatch.domain.Fish;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class FishJobConfig {
    // BEAN READER
    // CSV READER
    @Bean
    public FlatFileItemReader<Fish> reader() {
        return new FlatFileItemReaderBuilder<Fish>()
                        .name("fishItemReader")
                        .resource(new ClassPathResource("input/fish.csv"))
                        .delimited()
                        .names(new String[]{"name", "sizeMin", "sizeMax"})
                        .targetType(Fish.class)
                        .build();
    }

    // BEAN PROCESSOR
    @Bean
    public FishItemProcessor processor(){
        return new FishItemProcessor();
    }

    // BEAN WRITER
    @Bean
    public FlatFileItemWriter<String> writer(){
        return new FlatFileItemWriterBuilder<String>()
                .name("fishItemWriter")
                .resource(new FileSystemResource("src/main/resources/output/fishOut.csv"))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }

    // BEAN STEP
    @Bean
    public Step fishJobStep(StepBuilderFactory stepBuilderFactory){
        return stepBuilderFactory.get("fishJobStep")
                .<Fish,String>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    // BEAN JOB
    @Bean
    public Job fishJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory){
        return jobBuilderFactory.get("myFishJob")
                .start(fishJobStep(stepBuilderFactory))
                .build();
    }



    // BEAN JOB PARAMETERS (OPTIONAL)
}
