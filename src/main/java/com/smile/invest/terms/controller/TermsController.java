package com.smile.invest.terms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/terms")
public class TermsController {

    @GetMapping("/terms_service")
    public String termsService(){

        return "content/terms/terms_service";
    }

    @GetMapping("/privacy_policy")
    public String privacyPolicy() {

        return "content/terms/privacy_policy";

    }

    @GetMapping("/judging_criteria")
    public String judgingCriteria() {

        return "content/terms/judging_criteria";
    }

    @GetMapping("/fee_policy")
    public String feePolicy() {

        return "content/terms/fee_policy";
    }

    @GetMapping("/creator_guide")
    public String creatorGuide() {

        return "content/terms/creator_guide";
    }



}
