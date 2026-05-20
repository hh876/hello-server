package com.yjj.helloserver.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yjj.helloserver.service.ChatService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {

    // 你的 API Key 已经填好了！
    private static final String API_KEY = "sk-82fb02933cbd4394a431b1b321380a6e";

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String chat(String message) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + API_KEY);

            // JDK8 兼容
            Map<String, Object> userMsg = new HashMap<>();
            userMsg.put("role", "user");
            userMsg.put("content", message);

            List<Map<String, Object>> messages = new ArrayList<>();
            messages.add(userMsg);

            Map<String, Object> body = new HashMap<>();
            body.put("model", "qwen-turbo");
            body.put("messages", messages);

            // 转 JSON 字符串
            String jsonBody = objectMapper.writeValueAsString(body);
            HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

            ResponseEntity<String> resp = restTemplate.postForEntity(
                    "https://dashscope.aliyuncs.com/compatible-mode/v1/chat/completions",
                    request,
                    String.class
            );

            Map<String, Object> result = objectMapper.readValue(resp.getBody(), Map.class);
            List<Map<String, Object>> choices = (List<Map<String, Object>>) result.get("choices");
            Map<String, Object> choice = choices.get(0);
            Map<String, Object> msg = (Map<String, Object>) choice.get("message");
            return (String) msg.get("content");

        } catch (Exception e) {
            e.printStackTrace();
            return "AI调用失败：" + e.getMessage();
        }
    }
}