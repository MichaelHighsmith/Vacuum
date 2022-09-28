package com.accenture.michaelhighsmith.lintchecks

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UMethod

object NamingIssue {

    private const val ID = "MethodCheckerNamingIssue"
    private const val PRIORITY = 7
    private const val DESCRIPTION = "Wrong suffix in method name based on return type"
    private const val EXPLANATION = "Please correct the method name to end with the proper return type"
    private val CATEGORY = Category.CUSTOM_LINT_CHECKS
    private val SEVERITY = Severity.WARNING

    val ISSUE = Issue.create(
        ID,
        DESCRIPTION,
        EXPLANATION,
        CATEGORY,
        PRIORITY,
        SEVERITY,
        Implementation(CustomIssueDetector::class.java, Scope.JAVA_FILE_SCOPE)
    )

    class CustomIssueDetector : Detector(), Detector.UastScanner {
        override fun getApplicableUastTypes(): List<Class<out UElement>> =
            listOf(UMethod::class.java)

        override fun createUastHandler(context: JavaContext): UElementHandler =
            MethodChecker(context)
    }
}
