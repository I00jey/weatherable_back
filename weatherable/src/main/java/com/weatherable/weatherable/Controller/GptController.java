package com.weatherable.weatherable.Controller;


import com.weatherable.weatherable.DTO.GptRequestDTO;
import com.weatherable.weatherable.DTO.GptResponseDTO;
import com.weatherable.weatherable.enums.DefaultRes;
import com.weatherable.weatherable.enums.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DefaultRes<String>> chat(@RequestParam("prompt") String prompt) {
        try {

            GptRequestDTO request = new GptRequestDTO(
                    model, prompt, 1, 256, 1, 2, 2);

            GptResponseDTO gptResponse = restTemplate.postForObject(
                    apiUrl
                    , request
                    , GptResponseDTO.class
            );

            return new ResponseEntity<>(
                    DefaultRes.res(StatusCode.OK, "gpt 답변 fetching 완료", gptResponse.getChoices().get(0).getMessage().getContent()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    DefaultRes.res(StatusCode.BAD_REQUEST, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
