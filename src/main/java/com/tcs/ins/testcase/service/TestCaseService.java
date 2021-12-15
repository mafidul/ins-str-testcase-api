package com.tcs.ins.testcase.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.tcs.ins.testcase.service.model.TestCaseModel;
import com.tcs.ins.testcase.service.model.TestCaseSearchRequest;

public interface TestCaseService {

	TestCaseModel getTestCaseById(Long id);

	TestCaseModel createTestCase(TestCaseModel testCaseModel);

	TestCaseModel updateTestCase(TestCaseModel testCaseModel);

	Page<TestCaseModel> search(TestCaseSearchRequest testCaseSearchRequest, PageRequest pageRequest);

	void deleteTestCase(Long id);
}
