package com.hf.springboottest.controller

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {
    @GetMapping("/")
    fun showUser(model: Model): String {
        println("hoge")
        return "index"
    }
}