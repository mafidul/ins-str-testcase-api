package com.tcs.ins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tcs.ins.testcase.messaging.DefaultBatchProcessTriggeredEventProducer;
import com.tcs.ins.testcase.service.model.TestCaseModel;

@SpringBootApplication
public class TestCaseApiApplication implements CommandLineRunner {
	@Autowired
	DefaultBatchProcessTriggeredEventProducer defaultTriggeredEventProducer;

	public static void main(String[] args) {
		SpringApplication.run(TestCaseApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		TestCaseModel testCaseModel = new TestCaseModel();
		testCaseModel.setName("Producer");
		testCaseModel.setCategory("Category-1");
		testCaseModel.setSubCategory("Sub Category-1");
		defaultTriggeredEventProducer.sendMessage(testCaseModel);
		System.out.println("sending message from producer : " +testCaseModel);
	}
}