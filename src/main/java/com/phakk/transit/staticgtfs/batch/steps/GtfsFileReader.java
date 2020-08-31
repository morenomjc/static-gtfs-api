package com.phakk.transit.staticgtfs.batch.steps;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.util.Arrays;

@Slf4j
public class GtfsFileReader<T> extends FlatFileItemReader<T> {

    private String fileName;
    private Resource resource;
    private Class<T> target;
    private String[] fieldNames;

    public GtfsFileReader(String fileName, Class<T> target) {
        this.fileName = fileName;
        this.target = target;
        setupResource();
    }

    private void setupResource(){
        if (StringUtils.isEmpty(fileName)){
            throw new IllegalArgumentException("File name must not be null");
        }
        if (UrlValidator.getInstance().isValid(fileName)){
            try {
                resource = new UrlResource(fileName);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else{
            resource = new ClassPathResource(fileName);
        }
        log.info("resource=[{}]", fileName);
        setupFieldNames();
    }

    private void setupFieldNames(){
        try {
            Reader reader = new InputStreamReader(resource.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(reader);
            String header = bufferedReader.readLine();
            fieldNames = header.split(",");
            log.info("headers=[{}]", Arrays.toString(fieldNames));
            setupReader();
        }catch (IOException e){
            log.error("IOException encountered: {}", e.getMessage());
        }
    }

    private void setupReader(){
        setResource(resource);
        setStrict(false);
        setLinesToSkip(1);
        setLineMapper(new DefaultLineMapper<T>(){
            {
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames(fieldNames);
                        setStrict(false);
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<T>(){
                    {
                        setTargetType(target);
                    }
                });
            }
        });
    }
}
