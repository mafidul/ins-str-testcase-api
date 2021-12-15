package com.tcs.ins.testcase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tcs.ins.testcase.exception.ApplicationException;
import com.tcs.ins.testcase.repository.TestCaseRepository;
import com.tcs.ins.testcase.repository.entity.TestCase;
import com.tcs.ins.testcase.service.mapper.TestCaseMapper;
import com.tcs.ins.testcase.service.model.TestCaseModel;
import com.tcs.ins.testcase.service.model.TestCaseSearchRequest;
import com.tcs.ins.testcase.service.specification.TestCaseSpecification;

@Service
public class DefaultTestCaseService implements TestCaseService {

	private final TestCaseRepository testCaseRepository;
	private final TestCaseMapper testCaseMapper;

	public DefaultTestCaseService(TestCaseRepository testCaseRepository, TestCaseMapper testCaseMapper) {
		this.testCaseRepository = testCaseRepository;
		this.testCaseMapper = testCaseMapper;
	}

	@Override
	public TestCaseModel getTestCaseById(Long id) {
		TestCase teatCase = getOrThrow(id);
		return testCaseMapper.toModel(teatCase);
	}

	private TestCase getOrThrow(Long id) {
		Optional<TestCase> optionalTestCase = testCaseRepository.findById(id);
		if (optionalTestCase.isEmpty()) {
			throw ApplicationException.noRecordFound("Test Case Not Found");
		}
		return optionalTestCase.get();
	}

	@Override
	public TestCaseModel createTestCase(TestCaseModel testCaseModel) {
		TestCase testCase = testCaseMapper.toEntity(testCaseModel);
		TestCase saveTestCase = testCaseRepository.save(testCase);
		return testCaseMapper.toModel(saveTestCase);
	}

	@Override
	public TestCaseModel updateTestCase(TestCaseModel testCaseModel) {
		TestCase teatCase = getOrThrow(testCaseModel.getId());
		if (StringUtils.hasText(testCaseModel.getName())) {
			teatCase.setName(testCaseModel.getName());
		}

		if (StringUtils.hasText(testCaseModel.getDesc())) {
			teatCase.setDesc(testCaseModel.getDesc());
		}
		if (StringUtils.hasText(testCaseModel.getCategory())) {
			teatCase.setCategory(testCaseModel.getCategory());
		}
		if (StringUtils.hasText(testCaseModel.getSubCategory())) {
			teatCase.setSubCategory(testCaseModel.getSubCategory());
		}
		TestCase saveTestCase1 = testCaseRepository.save(teatCase);
		return testCaseMapper.toModel(saveTestCase1);
	}

	@Override
	public void deleteTestCase(Long id) {
		testCaseRepository.deleteById(id);
	}

	public Page<TestCaseModel> search(TestCaseSearchRequest testCaseSearchRequest, PageRequest pageRequest) {
		Page<TestCase> page = testCaseRepository.findAll(new TestCaseSpecification(testCaseSearchRequest), pageRequest);
		List<TestCaseModel> pageContent = testCaseMapper.toModel(page);
		return new PageImpl<>(pageContent, pageRequest, page.getTotalElements());
	}
}