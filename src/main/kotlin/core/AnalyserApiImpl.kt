package core

import api.AnalyserApi
import kotlinx.ast.common.AstSource.File
import kotlinx.ast.common.ast.Ast
import kotlinx.ast.common.ast.DefaultAstNode
import kotlinx.ast.grammar.kotlin.common.summary
import kotlinx.ast.grammar.kotlin.common.summary.Import
import kotlinx.ast.grammar.kotlin.target.antlr.kotlin.KotlinGrammarAntlrKotlinParser.parseKotlinFile

class AnalyserApiImpl(
    private val astList: List<Ast>
) : AnalyserApi {

    override fun ast(): List<Ast> {
        return astList
    }

    override fun callGraph() {
        TODO("Not yet implemented")
    }

    override fun search(name: String): List<List<Ast>> {
        val paths = astList
            .filter { it is DefaultAstNode && it.description == "importList" }
            .mapNotNull {
                (it as? Import)
                    ?.identifier
                    ?.fold("") { acc, next -> acc + next.identifier }
            }.filter {
                it.contains(name)
            }

        val basePath = "./src/main/kotlin/"

        val foundResults = mutableListOf<List<Ast>>()

        paths.forEach { path ->
            val kotlinFile: Ast = parseKotlinFile(File("$basePath/$path"))
            kotlinFile.summary(attachRawAst = false)
                .onSuccess { astList ->
                    foundResults.add(astList)
                }.onFailure { println("Unable to access $path") }
        }
        return foundResults
    }
}