package com.sridhar.socialapi.service;

import com.sridhar.socialapi.dto.PostDetails;
import com.sridhar.socialapi.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FackCheckService implements FackCheck{

    private final PostService postService;

    private final ChatClient chatClient;

    public FackCheckService(PostService postService, ChatClient.Builder chatClient) {
        this.postService = postService;
        this.chatClient = chatClient.build();
    }


    @Override
    public String aiFactCheck(Long id) {
        PostDetails post = postService.getPost(id);

        return chatClient.prompt("Here is the post message so tell if the information given here is fake or not : "+ post.getContent())
                .call()
                .content();

    }
}
