package com.hf.springboottest.repository

import com.hf.springboottest.entity.TestTable
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Repository
@Mapper
interface TestTableRepository {

    fun findAll(): List<TestTable>

    fun findById(@Param("id") id: Long): TestTable?

    fun insert(testTable: TestTable): Long

    fun update(testTable: TestTable): Int

    fun delete(@Param("id") id: Long): Int
}
