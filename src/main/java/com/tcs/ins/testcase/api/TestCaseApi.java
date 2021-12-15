package com.tcs.ins.testcase.api;

import static com.tcs.ins.testcase.support.Constant.REQUEST_PARAM_MAPPING;
import static com.tcs.ins.testcase.support.Constant.REQUEST_PARAM_PAGE_NUMBER;
import static com.tcs.ins.testcase.support.Constant.REQUEST_PARAM_PAGE_SIZE;
import static com.tcs.ins.testcase.support.Constant.REQUEST_PARAM_SORT_BY;
import static com.tcs.ins.testcase.support.Constant.REQUEST_PARAM_SORT_DIRECTION;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tcs.ins.testcase.service.AsynchronousService;
import com.tcs.ins.testcase.service.TestCaseService;
import com.tcs.ins.testcase.service.model.TestCaseModel;
import com.tcs.ins.testcase.service.model.TestCaseSearchRequest;

@RestController
@RequestMapping(REQUEST_PARAM_MAPPING)
class TestCaseApi {

	private static final String SORT_DIRECTION_ASC = "asc";
	private static final Logger logger = LoggerFactory.getLogger(TestCaseApi.class);

	private final TestCaseService testCaseService;

	public TestCaseApi(TestCaseService testCaseService) {
		this.testCaseService = testCaseService;
	}

	@Autowired
	private AsynchronousService asynchronousService;
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<TestCaseModel> getTestCaseById(@PathVariable Long id) {
		return ResponseEntity.ok(testCaseService.getTestCaseById(id));
	}

	@GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Page<TestCaseModel>> search(
			@RequestParam(name = REQUEST_PARAM_PAGE_NUMBER, required = true) Integer pageNumber,
			@RequestParam(name = REQUEST_PARAM_PAGE_SIZE, required = true) Integer pageSize,
			@RequestParam(name = REQUEST_PARAM_SORT_BY, required = false) String sortBy,
			@RequestParam(name = REQUEST_PARAM_SORT_DIRECTION, required = false) String sortDirection,
			@RequestParam Map<String, String> requestParam) {
		TestCaseSearchRequest testCaseSearchRequest = new TestCaseSearchRequest(requestParam);
		PageRequest pageRequest = pageRequest(pageNumber, pageSize, sortBy, sortDirection);
		Page<TestCaseModel> page= testCaseService.search(testCaseSearchRequest, pageRequest);
		return ResponseEntity.ok(page);

	}

	@GetMapping(path = "/async/", produces = MediaType.APPLICATION_JSON_VALUE)
	public void run(String... args) throws Exception {
		long start = System.currentTimeMillis();
		logger.info("Elapsed time: start " + (System.currentTimeMillis() - start));
		CompletableFuture<TestCaseModel> testCase1 = asynchronousService.findUser("PivotalSoftware");
		CompletableFuture<TestCaseModel> testCase2 = asynchronousService.findUser("CloudFoundry");
		CompletableFuture<TestCaseModel> testCase3 = asynchronousService.findUser("Spring-Projects");
		CompletableFuture<TestCaseModel> testCase4 = asynchronousService.findUser("PivotalSoftware");
		CompletableFuture<TestCaseModel> testCase5 = asynchronousService.findUser("CloudFoundry");
		CompletableFuture<TestCaseModel> testCase6 = asynchronousService.findUser("Spring-Projects");

		// Wait until they are all done
		CompletableFuture.allOf(testCase1, testCase2, testCase3, testCase4, testCase5, testCase6).join();

		// Print results, including elapsed time
		logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
		logger.info("--> " + testCase1.get());
		logger.info("--> " + testCase2.get());
		logger.info("--> " + testCase3.get());
		logger.info("--> " + testCase4.get());
		logger.info("--> " + testCase5.get());
		logger.info("--> " + testCase6.get());
	}
	
	private PageRequest pageRequest(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
		if (StringUtils.hasText(sortBy)) {
			Direction direction = StringUtils.hasText(sortDirection)
					&& SORT_DIRECTION_ASC.equalsIgnoreCase(sortDirection) ? Direction.ASC : Direction.DESC;
			return PageRequest.of(pageNumber, pageSize, Sort.by(new Order(direction, sortBy)));
		}
		return PageRequest.of(pageNumber, pageSize);
	}

	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<TestCaseModel> createTestCase(@RequestBody TestCaseModel testCaseModel, UriComponentsBuilder ucb) {
		TestCaseModel create = testCaseService.createTestCase(testCaseModel);
		return ResponseEntity.created(ucb
									  .path(REQUEST_PARAM_MAPPING + "/{id}")
									  .buildAndExpand(create.getId())
									  .toUri())
							  .body(create);
	}
	
	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<TestCaseModel> updateTestCase(@PathVariable Long id, @RequestBody TestCaseModel testCaseModel) {
		testCaseModel.setId(id);
		TestCaseModel update = testCaseService.updateTestCase(testCaseModel);
		return ResponseEntity.ok(update);
	}
	
	@DeleteMapping(path = "/{id}")
	ResponseEntity<Void> deleteTestCase(@PathVariable Long id) {
		testCaseService.deleteTestCase(id);
		return ResponseEntity.ok(null);
	}
}