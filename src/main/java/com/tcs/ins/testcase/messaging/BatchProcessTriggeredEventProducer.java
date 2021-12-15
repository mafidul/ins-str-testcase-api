package com.tcs.ins.testcase.messaging;

import com.tcs.ins.testcase.service.model.TestCaseModel;

public interface BatchProcessTriggeredEventProducer {

	void sendMessage(TestCaseModel event);
}