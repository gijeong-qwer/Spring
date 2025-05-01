package com.ca.finalproject.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

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

    @RequestMapping("readArticlePage")
    public String readArticlePage(
        Model model,
        @RequestParam("id")  int id
    ){
        Map<String,Object> articleData = boardService.getArticle(id);

        // html escape  : 자바스크립트에서 렌더링 할때는 알아서 전환해줌 그냥 지금 쓰라고 알려주는거
        // 이거 안하면 스크립트 공격을 받음 ..
        // 차후 js로 작업시 자동으로 다 되서 신경 안써도됨..
       
        ArticleDto articleDto = (ArticleDto)articleData.get("articleDto");   // get으로 가져오는건 Object여서 타입캐스팅해야함
        String content = articleDto.getContent();
        content = HtmlUtils.htmlEscape(content);  // html 문법(특수문자를) 자동으로바꿔줌
        // 단 개행만 안해줌 그래서 아래코드가 필요한것
         // 사실이건 html escape가 아님 개행을 br로 바꾼것 
        content = content.replaceAll("\n","<br>");
        articleDto.setContent(content);

        model.addAttribute("articleData", articleData);

        return "board/readArticlePage";
    }

    @RequestMapping("deleteArticleProcess")
    public String deleteArticleProcess(
        @RequestParam("id") int id    // 글번호
    ){
        boardService.deleArticle(id);

        return "redirect:/board/mainPage";
    }

    @RequestMapping("updateArticlePage")
    public String updateArticlePage(
        Model model,
        @RequestParam("id") int id
    ){
        Map<String,Object> articleData = boardService.getArticle(id);
        model.addAttribute("articleData", articleData);

        return "board/updateArticlePage";
    }

    @RequestMapping("updateArticleProcess")
    public String  updateArticleProcess(
        @ModelAttribute ArticleDto params
    ){
        boardService.updateArticle(params);

        return "redirect:/board/readArticlePage?id="+params.getId();
        // return "redirect:/board/readArticlePage"; id 파라미터가 안날라감 redirect:/ 문자열 
        // 이거 어려워했음 String 타입으로 return을 하니까 
        //문자열 return이니까 그냥 이런식으로 가능한거임 ..
        // 뒤로가기는 request를 하지않음.. 얘 때문에 발생하는 일이있음
    }
}
