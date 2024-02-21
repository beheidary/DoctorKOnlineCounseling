package com.doctork.doctorkonlinecounseling.configuration;

import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.ssl.SSLContexts;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.doctork.doctorkonlinecounseling.database.searchEngineRepositories")
//@ComponentScan(basePackages = { "com.doctork.doctorkonlinecounseling.boundary.in.searchEngine" })
//
//public class ElasticsearchConfig extends ElasticsearchConfiguration {
//
//
//        @Override
//        public ClientConfiguration clientConfiguration() {
//            SSLContext sslContext;
//            try {
//                sslContext = SSLContexts.custom()
//                        .loadTrustMaterial(null, TrustAllStrategy.INSTANCE)
//                        .build();
//            } catch (NoSuchAlgorithmException e) {
//                throw new RuntimeException(e);
//            } catch (KeyManagementException e) {
//                throw new RuntimeException(e);
//            } catch (KeyStoreException e) {
//                throw new RuntimeException(e);
//            }
//            return ClientConfiguration.builder()
//                    .connectedTo("https://localhost:9200")
//                    .usingSsl(sslContext)
//                    .withBasicAuth("elastic", "PX1eQez0nGi9-9Br*Heo")
//                    .build();
//        }
//
//
//
//    }



//                    .usingSsl()
//                    .withProxy("localhost:8888")
//                    .withPathPrefix("ela")
//                    .withConnectTimeout(Duration.ofSeconds(5))
//                    .withSocketTimeout(Duration.ofSeconds(3))
//                    .withDefaultHeaders(defaultHeaders)
//                    .withBasicAuth(username, password)
//                    .withHeaders(() -> {
//                        HttpHeaders headers = new HttpHeaders();
//                        headers.add("currentTime", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//                        return headers;
//                    })
//                    .withClientConfigurer(
//                            ElasticsearchClientConfigurationCallback.from(clientBuilder -> {
//                                // ...
//                                return clientBuilder;
//                            }))
