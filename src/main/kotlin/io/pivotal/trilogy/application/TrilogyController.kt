package io.pivotal.trilogy.application

import io.pivotal.trilogy.testproject.TestBuildException
import io.pivotal.trilogy.testproject.TestProjectBuilder
import io.pivotal.trilogy.testproject.TestProjectResult
import io.pivotal.trilogy.testrunner.TestProjectRunner
import io.pivotal.trilogy.testrunner.UnrecoverableException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
open class TrilogyController {

    @Autowired
    lateinit var testProjectRunner: TestProjectRunner

    fun run(options: TrilogyApplicationOptions): TestProjectResult {
        val testProject = try {
            TestProjectBuilder.build(options)
        } catch (e: TestBuildException) {
            return TestProjectResult(emptyList(), failureMessage = e.localizedMessage)
        }

        return try {
            testProjectRunner.run(testProject)
        } catch(e: UnrecoverableException) {
            TestProjectResult(emptyList(), failureMessage = e.localizedMessage, unrecoverableFailure = true)
        }

    }
}

