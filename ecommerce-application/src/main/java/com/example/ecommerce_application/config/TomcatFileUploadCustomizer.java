package com.example.ecommerce_application.config;

import org.apache.catalina.Context;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.apache.tomcat.util.http.fileupload.impl.FileCountLimitExceededException;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatFileUploadCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addContextCustomizers((Context context) -> {
            context.setAllowCasualMultipartParsing(true);
            context.addParameter("maxFileCount", "100"); // Allow up to 100 files per request
        });
    }
}
