package com.hf.springboottest.controller

import com.hf.springboottest.annotation.NoArgs
import com.hf.springboottest.service.TestTableService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.util.concurrent.Callable
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("api/testTables")
class TestTableRestController(val testTablesService: TestTableService) {


    @GetMapping
    fun getTestTables() = Callable {
        testTablesService.getTestTables()
    }

    @GetMapping("{id}")
    fun getTestTable(
        @PathVariable("id") id: Long
    ) = Callable {
        testTablesService.getTestTables(id)
    }

    @NoArgs
    data class TestTableRequest(
        @field:NotBlank
        val textField: String,
        @field:NotNull
        val startedAt: Instant,
        @field:NotNull
        val endedAt: Instant
    )

    @PostMapping
    fun add(
        @Valid @RequestBody payload: TestTableRequest
    ) = Callable {
        testTablesService.addTestTable(payload)
    }

    @PutMapping("{id}")
    fun update(
        @PathVariable("id") id: Long,
        @Valid @RequestBody payload: TestTableRequest
    ) = Callable {
        testTablesService.updateTestTable(id, payload)
    }

    @DeleteMapping("{id}")
    fun delete(
        @PathVariable("id") id: Long
    ) = Callable {
        testTablesService.removeTestTable(id)
    }
}

