package com.bachmanity.bchain;

import org.springframework.web.bind.annotation.RequestMapping;


class MyErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError() {

        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

