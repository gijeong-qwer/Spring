package com.ca.finalproject.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ca.finalproject.board.service.BoardServiceImpl;
import com.ca.finalproject.dto.ArticleDto;
import com.ca.finalproject.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    private BoardServiceImpl boardService;

    @RequestMapping("mainPage")
    public String mainPage(Model model){
        // Model이라는 API  org.springframework.ui 에서 사용 -> dataList를 mainPage에서 쓰기위함
        
        List<Map<String,Object>> dataList = boardService.getArticleList();

        // 출력결과는 타임리프를 통해 출력하는데 , 넘길방법이 없어서 Model 을 사용하게 되는것
        model.addAttribute("dataList", dataList);  // 모델에다 값을 넣는다는것은 타임리프에서 쓸 수 있다는것임!!! 
        // model에 굉장히 많은값을 넣을 수 있다 
        // model.addAttribute("yyyy","안녕하세요");
        // model.addAttribute("qqqq",3);
        // model.addAttribute("pppp",new UserDto());

        // Map<String,Object> qwer = new HashMap<>();
        // qwer.put("a", 8888);
        // qwer.put("b", "9999");
        // model.addAttribute("qwer", qwer); // 이러면 qwer도 타임리프에서 쓸 수 있음


        return "board/mainPage";
    }


    @RequestMapping("writeArticlePage")
    public String writeArticlePage(){

        return "board/writeArticlePage";
    }

    // 인자를 받을때는 RequestParam 과 ModelAttribute 중에 고민을 해야하는데
    // 이번에는 받아야할게 3개니까 ModelAttribute사용
    @RequestMapping("writeArticleProcess")
    public String writeArticleProcess(
        HttpSession session,  // session을 통해  안받아온 id의 값을 받아오려고함
        @ModelAttribute ArticleDto params
    ){ UserDto sessionUserDto = (UserDto)session.getAttribute("sessionUserInfo");  
                // ("key")  -> sessionUserInfo가 key임 , Dto 가 value
                // getAttribute는 return타입이 object임
                // 따라서 타입캐스팅 해줘야함 
        params.setUserId(sessionUserDto.getId());  // @Data가 set get 다 만들어놓음 

        boardService.writeArticle(params);

        return "redirect:/board/mainPage";
    }



}
