package me.xyzlast.sadari.api.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ykyoon on 14. 11. 14.
 */
@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping(value = "hello/get")
    public Object getHello() {
        return "Hello";
    }
}
