package com.thc.sprboot.schedule;

import com.thc.sprboot.socket.WsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final int intervalSecond = 3;

	private final WsHandler wsHandler;
	public ScheduleTask(
		WsHandler wsHandler
	) {
		this.wsHandler = wsHandler;
	}

	// 개발 중 정지
	//@Scheduled(fixedDelay = intervalSecond * 1000) // intervalSecond
	public void taskByInterval() throws Exception {
		logger.info("00. AUTO // taskSecondCheck // " + intervalSecond);
	}
}
