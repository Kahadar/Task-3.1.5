package ru.itmentor;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.itmentor.model.User;

import java.util.Arrays;

public class Consumer {
    public static void main(String[] args) {

        String url = "http://94.198.50.185:7081/api/users";
        String result = "";


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        ResponseEntity responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>("", headers),
                String.class);

        System.out.println("Response entity:   " + responseEntity.getHeaders());
        System.out.println();
        String cookie  = responseEntity.getHeaders().get("Set-Cookie").toString();
        cookie = cookie.substring(1, cookie.length() - 1);
        System.out.println("Cookie   " + cookie);

        headers.set("Cookie", cookie);
        User user = new User(3L, "James", "Brown", (byte) 64);

        HttpEntity<User> entityUser = new HttpEntity<>(user, headers);

//        операция пост
        responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(user, headers),
                String.class);

        result += responseEntity.getBody();

        System.out.println("Промежуточный результат 1:   " + result);
        System.out.println();

//        переименовать
        user.setName("Thomas");
        user.setLastName("Shelby");

//        убедиться, что получилось
        System.out.println(user.toString());
        System.out.println();


//        пут
        responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(user, headers),
                String.class);

        result += responseEntity.getBody();
        System.out.println("Промежуточный результат 2:   " + result);
        System.out.println();

//        делет
        responseEntity = restTemplate.exchange(
                url + "/" + user.getId(),
                HttpMethod.DELETE,
                new HttpEntity<>(user, headers),
                String.class
        );

        result += responseEntity.getBody();
        System.out.println("Финальный результат:  " + result);
    }
}
