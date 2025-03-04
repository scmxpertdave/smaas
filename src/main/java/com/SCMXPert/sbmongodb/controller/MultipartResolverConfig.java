//package com.SCMXPert.sbmongodb.controller;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//
//@Configuration
//public class MultipartResolverConfig {
//
//    @Bean(name = "multipartResolver")
//    public CommonsMultipartResolver createMultipartResolver() {
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        
//        // Configure resolver properties if needed
//        resolver.setDefaultEncoding("utf-8");
//        resolver.setMaxUploadSize(10 * 1024 * 1024); // Set the maximum upload size in bytes (e.g., 10 MB)
//        // Add more configuration as needed
//        
//        return resolver;
//    }
//}
