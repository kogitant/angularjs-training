package funmarket.front;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class FrontController {

    @RequestMapping("/")
    public ModelAndView index() {
        FrontpageModel frontpage = new FrontpageModel();
        frontpage.title = "Funmarket API";
        return new ModelAndView("index", "frontpage", frontpage);
    }

}
