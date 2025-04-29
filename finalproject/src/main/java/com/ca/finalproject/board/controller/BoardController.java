package com.ca.finalproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board")
public class BoardController {

    @RequestMapping("mainPage")
    public String mainPage(){

        return "board/mainPage";
    }

}
