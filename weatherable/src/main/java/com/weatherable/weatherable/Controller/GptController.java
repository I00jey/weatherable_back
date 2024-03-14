package com.weatherable.weatherable.Controller;


import com.weatherable.weatherable.DTO.GptRequestDTO;
import com.weatherable.weatherable.DTO.GptResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gpt")
@RequiredArgsConstructor
public class GptController {

    @Value("${gpt.model}")
    private String model;

    @Value("${gpt.api.url}")
    private String apiUrl;
    private final RestTemplate restTemplate;


    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt){

        GptRequestDTO request = new GptRequestDTO(
                model,prompt,1,256,1,2,2);

        GptResponseDTO gptResponse = restTemplate.postForObject(
                apiUrl
                , request
                , GptResponseDTO.class
        );


        return gptResponse.getChoices().get(0).getMessage().getContent();


    }
}
