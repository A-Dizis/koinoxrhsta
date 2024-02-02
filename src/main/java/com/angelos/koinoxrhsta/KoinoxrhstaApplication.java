package com.angelos.koinoxrhsta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.angelos.koinoxrhsta.def.op.Executable;

import lombok.Setter;

@SpringBootApplication
public class KoinoxrhstaApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
      .getLogger(KoinoxrhstaApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(KoinoxrhstaApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }
 
    @Setter
    @Autowired
	Executable executable;
    
    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");
 
        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}", i, args[i]);
        }
 
        
        executable.execute();
    }

//	public static void main(String[] args) {
//		SpringApplication.run(KoinoxrhstaApplication.class, args);
//	}

}
