package rules

import api.AnalyserApi
import api.AnalyzerRule
import kotlinx.ast.common.ast.DefaultAstNode
import kotlinx.ast.grammar.kotlin.common.summary.Import
import java.nio.file.Path

class NoGenericImportsRule : AnalyzerRule() {

    override val ruleMessage: String
        get() = "NoGenericImports"

    override fun analyse(file: Path, api: AnalyserApi, errorCallback: (String) -> Unit) {
        api.ast()
            .filter { it is DefaultAstNode && it.description == "importList" }
            .forEach { imports ->
                (imports as DefaultAstNode).children.forEach { import ->
                    if ((import as? Import)?.starProjection == true) {
                        errorCallback("Generic import found in ${import.description}")
                    }
                }
            }
    }
}