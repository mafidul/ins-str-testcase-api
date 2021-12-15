package com.tcs.ins.testcase.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BatchProcessMQConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(BatchProcessMQConsumer.class);

	@RabbitListener(queues = "#{'${rabbitmq.batch_process.triggered.queue}'}")
	public void consumeMessage(String message) {
		try {
			LOGGER.trace("Message received: {} ", message);
			LOGGER.debug("Message processed successfully with status {} ");
		} catch (Exception e) {
			LOGGER.error("Unnable to process the Message", e);
		}
	}
}