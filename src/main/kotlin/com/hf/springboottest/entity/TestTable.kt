package com.hf.springboottest.entity

import com.hf.springboottest.annotation.NoArgs
import java.time.Instant

@NoArgs
data class TestTable (
    val id: Long? = null,
    var name: String = "",
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant = Instant.now()
)
