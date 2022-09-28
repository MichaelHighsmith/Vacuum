package com.accenture.michaelhighsmith.lintchecks

import com.accenture.michaelhighsmith.lintchecks.NamingIssue.ISSUE
import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.JavaContext
import com.intellij.psi.PsiClassType
import org.jetbrains.uast.UMethod

class MethodChecker(private val context: JavaContext) : UElementHandler() {

    override fun visitMethod(node: UMethod) {
        val returnType = node.returnClassName()
        checkMethod(node)
    }

    private fun UMethod.returnClassName(): String {
        return (returnTypeReference?.type as? PsiClassType)?.className ?: ""
    }

    private fun checkMethod(node: UMethod) {
        checkMethodSuffix(node.name).also { matches ->
            if (!matches) {
                reportIssue(node)
            }
        }
    }

    private fun checkMethodSuffix(methodName: String): Boolean =
        methodName.endsWith("String")

    private fun reportIssue(method: UMethod) {
        val message = "Method suffix should match return type"

        context.report(
            issue = ISSUE,
            scopeClass = method,
            location = context.getNameLocation(method),
            message = message
        )
    }


}