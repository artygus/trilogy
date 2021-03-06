package io.pivotal.trilogy.parsing

import io.pivotal.trilogy.test_helpers.ResourceHelper
import org.jetbrains.spek.api.Spek
import kotlin.test.expect

class MarkdownTableTests : Spek({

    it("returns empty lists for empty input") {
        val table = MarkdownTable("")
        expect(emptyList()) { table.getHeaders() }
        expect(emptyList()) { table.getValues() }
    }

    it("returns the header for a table with a single header") {
        val header = "THE_STARLIGHT"
        val table = MarkdownTable("|$header|")
        expect(listOf(header)) { table.getHeaders() }
    }

    it("trims the spaces from the header names") {
        val header = "HEU_AXONA"
        val table = MarkdownTable("|    $header |")
        expect(listOf(header)) { table.getHeaders() }
    }

    it("returns the values from a table with single line") {
        val singleTable = ResourceHelper.getResourceAsText("/tables/singleColumnSingleRow.md")
        val table = MarkdownTable(singleTable)
        expect(listOf(listOf("FOO"))) { table.getValues() }
    }

    it("returns the values from a table with few lines") {
        val singleTableFewRows = ResourceHelper.getResourceAsText("/tables/singleColumnFewRows.md")
        val table = MarkdownTable(singleTableFewRows)
        expect(listOf(listOf("FOO"), listOf("BAR"), listOf("BAZ"))) { table.getValues() }
    }

    it("returns the header for a table with a set of headers") {
        val fewColumnsFewRows = ResourceHelper.getResourceAsText("/tables/fewColumnsFewRows.md")
        val table = MarkdownTable(fewColumnsFewRows)
        expect(listOf("PARAM1", "PARAM2", "=ERROR=")) { table.getHeaders() }
    }

    it("returns the values from a table with few columns and rows") {
        val singleTable = ResourceHelper.getResourceAsText("/tables/fewColumnsFewRows.md")
        val table = MarkdownTable(singleTable)
        expect(listOf(listOf("FOO", "FOO2", ""), listOf("BAR", "BAR2", ""), listOf("BAZ", "BAZ2", ""))) { table.getValues() }
    }

})