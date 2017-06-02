package org.vintegris.nebula.sign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@SpringBootApplication
@EnableEurekaClient
public class SignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SignApplication.class, args);
	}

}

@RefreshScope
@RestController
class MessageRestController {

	@Value("${message:I dont know who am I}")
	private String message;

	@RequestMapping("/")
	String getMessage() {
		return this.message;
	}

	@RequestMapping("/base64/encode")
	String convertFromStringToBase64(String data) {
        try {
            return new String(Base64.encode(data.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }

    @RequestMapping("/base64/decode")
	String convertFromBase64ToString(String data) {
        try {
            return new String(Base64.decode(data.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }
}