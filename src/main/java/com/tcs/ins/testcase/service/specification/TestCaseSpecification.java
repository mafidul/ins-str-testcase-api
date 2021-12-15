package com.tcs.ins.testcase.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.tcs.ins.testcase.repository.entity.TestCase;
import com.tcs.ins.testcase.service.model.TestCaseSearchRequest;

public class TestCaseSpecification implements Specification<TestCase> {

	private static final long serialVersionUID = 1L;
	private final TestCaseSearchRequest testCaseSearchRequest;

	public TestCaseSpecification(TestCaseSearchRequest testCaseSearchRequest) {
		this.testCaseSearchRequest = testCaseSearchRequest;
	}

	@Override
	public Predicate toPredicate(Root<TestCase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();
		if (testCaseSearchRequest.idFilteringRequired()) {
			predicates.add(criteriaBuilder.equal(root.get("id"), testCaseSearchRequest.getId()));
		}
		if (testCaseSearchRequest.nameFilteringRequired()) {
			predicates.add(criteriaBuilder.like(root.get("name"), testCaseSearchRequest.getName() + "%"));
		}
		if (testCaseSearchRequest.descFilteringRequired()) {
			predicates.add(criteriaBuilder.like(root.get("desc"), testCaseSearchRequest.getDesc() + "%"));
		}
		if (testCaseSearchRequest.categoryFilteringRequired()) {
			predicates.add(criteriaBuilder.like(root.get("category"), testCaseSearchRequest.getCategory() + "%"));
		}
		if (testCaseSearchRequest.subCategoryFilteringRequired()) {
			predicates.add(criteriaBuilder.like(root.get("subCategory"), testCaseSearchRequest.getSubCategory() + "%"));
		}
		if (testCaseSearchRequest.subCategoryFilteringRequired()) {
			predicates.add(criteriaBuilder.like(root.get("subCategory"), testCaseSearchRequest.getSubCategory() + "%"));
		}
		if (testCaseSearchRequest.subCategoryFilteringRequired()) {
			predicates.add(criteriaBuilder.like(root.get("subCategory"), testCaseSearchRequest.getSubCategory() + "%"));
		}
		if (testCaseSearchRequest.subCategoryFilteringRequired()) {
			predicates.add(criteriaBuilder.like(root.get("subCategory"), testCaseSearchRequest.getSubCategory() + "%"));
		}
		return andTogether(predicates, criteriaBuilder);
	}

	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
		return cb.and(predicates.toArray(new Predicate[0]));
	}
}
