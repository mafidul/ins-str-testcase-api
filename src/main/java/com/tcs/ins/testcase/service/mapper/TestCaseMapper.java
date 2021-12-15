package com.tcs.ins.testcase.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.tcs.ins.testcase.repository.entity.TestCase;
import com.tcs.ins.testcase.service.model.TestCaseModel;

@Mapper(componentModel = "spring")
public interface TestCaseMapper {

	TestCase toEntity(TestCaseModel source);

	TestCaseModel toModel(TestCase source);

	List<TestCaseModel> toModel(Page<TestCase> testCase);
}