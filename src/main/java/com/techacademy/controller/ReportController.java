package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("reports")
public class ReportController {

    // 従業員一覧画面
    @GetMapping
    public String list() {
        // 後でクラス間のメソッド呼び出し追加

        return "reports/list";
    }

}
