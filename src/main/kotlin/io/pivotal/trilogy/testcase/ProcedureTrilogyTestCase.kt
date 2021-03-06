package io.pivotal.trilogy.testcase

data class ProcedureTrilogyTestCase(val procedureName: String, override val description: String,
                                    override val tests: List<ProcedureTrilogyTest>,
                                    override val hooks: TestCaseFixtures,
                                    override val malformedTests: List<MalformedTrilogyTest> = emptyList()) : TrilogyTestCase