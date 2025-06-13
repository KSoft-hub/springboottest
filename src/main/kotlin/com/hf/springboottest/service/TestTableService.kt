package com.hf.springboottest.service

//import com.hf.springboottest.controller.TestTableRestController.TestTableRequest
import com.hf.springboottest.controller.TestTableRestController
import com.hf.springboottest.entity.TestTable
import com.hf.springboottest.repository.TestTableRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
@Transactional(rollbackFor = [Exception::class])
class TestTableService(val testTableRepository: TestTableRepository) {

    fun getTestTables() = testTableRepository.findAll()

    fun getTestTables(id: Long) = testTableRepository.findById(id)

    fun addTestTable(payload: TestTableRestController.TestTableRequest): TestTable {
        val testTable = TestTable(
            null,
            payload.textField,
            payload.startedAt,
            payload.endedAt
        )
        testTableRepository.insert(testTable)
        return testTableRepository.findById(testTable.id!!)!!
    }

    fun updateTestTable(id: Long, payload: TestTableRestController.TestTableRequest) =
        testTableRepository.findById(id)?.let { testTable ->
            testTable.apply {
                name = payload.textField
                updatedAt = Instant.now()
            }
            testTableRepository.update(testTable)
        } ?: throw NoSuchElementException("testTable not found. id=$id")

    fun removeTestTable(id: Long) =
        testTableRepository.findById(id)?.let {
            testTableRepository.delete(id)
        } ?: throw NoSuchElementException("testTable not found. id=$id")
}
