package com.thc.sprboot.controller.page;

import com.thc.sprboot.dto.TbuserDto;
import com.thc.sprboot.service.TbuserService;
import com.thc.sprboot.util.FileUpload;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.UUID;

@RequestMapping("")
@Controller
public class GameController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbuserService tbuserService;
    public GameController(TbuserService tbuserService) {
        this.tbuserService = tbuserService;
    }

    @GetMapping("/homealone")
    public String homealone(@RequestParam String token, Model model) throws IOException, ParseException {
        logger.info("token : " + token);

        /*
        //커넥션으로 유저정보 받아왔다고 보고..
        URL url = new URL("url....." + "/authenticate");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("x-operator-id", "1");
        connection.setRequestProperty("x-operator-signature", "2");
        //connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));
        int code = connection.getResponseCode();
        boolean isSuccess = code == 200 ? true : false;
        InputStream responseStream = isSuccess? connection.getInputStream(): connection.getErrorStream();
        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        responseStream.close();
        String userId = jsonObject.get("user.id") + "";
         */

        String userId = UUID.randomUUID().toString().replace("-", "");

        TbuserDto.SnsLoginResDto resDto = tbuserService.sns(
        TbuserDto.CreateServDto.builder().username(userId).password("homealone_" + userId).route("game").build()
        );
        model.addAttribute("token", resDto);

        return "homealone";
    }

}
