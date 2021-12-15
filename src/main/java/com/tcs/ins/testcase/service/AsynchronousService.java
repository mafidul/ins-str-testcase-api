package com.tcs.ins.testcase.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tcs.ins.testcase.service.model.TestCaseModel;

@Service
public class AsynchronousService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AsynchronousService.class);

	private final RestTemplate restTemplate;

	public AsynchronousService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Async("asyncExecutor")
	public java.util.concurrent.CompletableFuture<TestCaseModel> findUser(String user) throws InterruptedException {
		LOGGER.info("Looking up " + user);
		String url = String.format("https://api.github.com/users/%s", user);
		TestCaseModel results = restTemplate.getForObject(url, TestCaseModel.class);
		Thread.sleep(1000L);
		return CompletableFuture.completedFuture(results);
	}
}