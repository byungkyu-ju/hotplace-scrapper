package com.juinsight.scrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(value = "com.juinsight.scrapper.Mapper")
public class ScrapperApplication {

	public static void main(String[] args) {

		SpringApplication.run(ScrapperApplication.class, args);
	}
}
