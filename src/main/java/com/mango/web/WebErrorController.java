package com.mango.web;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WebErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object responseStatus = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        model.addAttribute("status", responseStatus.toString());
        model.addAttribute("errror", errorMessage.toString());
        model.addAttribute("exception", exception.toString());

       /* if (responseStatus != null) {
            Integer responseStatusInt = Integer.parseInt(responseStatus.toString());
            if (responseStatusInt == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            } else if (responseStatusInt == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            }
        }*/
        return "error";

    }


}


