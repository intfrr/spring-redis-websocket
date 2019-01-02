package com.santander.service;

import com.santander.config.ApplicationProperties;
import com.santander.model.ChatMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class WebSocketMessageService {

	private final ApplicationProperties applicationProperties;
	private final SimpMessagingTemplate template;

	private static final Logger LOG = LoggerFactory.getLogger(WebSocketMessageService.class);

	public WebSocketMessageService(ApplicationProperties applicationProperties, SimpMessagingTemplate template) {
		this.applicationProperties = applicationProperties;
		this.template = template;
	}

	@Async
//    public void sendChatMessage(ChatMessage message) {
	public void sendChatMessage(ChatMessage message, String username) {
//		template.convertAndSend(applicationProperties.getTopic().getMessage(), message);
//		template.convertAndSendToUser(username, applicationProperties.getTopic().getMessage(), message);
		template.convertAndSendToUser(username, "/queue/notify", message);
	}

	@Async
//	public void sendMessageCount(Integer totalMessage) {
	public void sendMessageCount(Integer totalMessage, String username) {
		LOG.info("Total Messages: {}", totalMessage);
//		template.convertAndSendToUser(username, applicationProperties.getTopic().getCount(), totalMessage);
		template.convertAndSend(applicationProperties.getTopic().getCount(), totalMessage);
	}

}
