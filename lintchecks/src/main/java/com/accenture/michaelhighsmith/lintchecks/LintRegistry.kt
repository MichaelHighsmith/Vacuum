package com.accenture.michaelhighsmith.lintchecks

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API

class LintRegistry : IssueRegistry() {
    override val issues = listOf(NamingIssue.ISSUE)

    override val api: Int = CURRENT_API

    //Match current gradle plugin in Api.kt, otherwise custom rules are skipped
    override val minApi: Int = 10
}