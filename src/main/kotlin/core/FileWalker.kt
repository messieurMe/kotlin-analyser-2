package core

import config.ActiveRules
import kotlinx.ast.common.AstSource
import kotlinx.ast.common.ast.Ast
import kotlinx.ast.grammar.kotlin.common.summary
import java.nio.file.FileVisitResult
import kotlin.io.path.*
import kotlinx.ast.grammar.kotlin.target.antlr.kotlin.KotlinGrammarAntlrKotlinParser.parseKotlinFile
import java.nio.file.Path

@OptIn(ExperimentalPathApi::class)
class FileWalker {

    private val errors = mutableListOf<String>()

    private fun rememberError(path: Path, rule: String, message: String) {
        errors.add(
            "\"${rule}\":" +
                    "\n\tWith error: $message" +
                    "\n\tFor path: $path" +
                    "\n"
        )
    }

    private fun showErrors() {
        println("==========FOUND ERRORS==========")
        errors.forEach(::println)
        if(errors.isNotEmpty()){
            error("Validation failed")
        }
    }

    fun start() {
        val rules = ActiveRules.values().map { it.rule }

        val base = Path("./")

        base.visitFileTree {
            onVisitFile { file, _ ->
                if (file.name.endsWith(".kt")) {
                    val source = AstSource.File(file.toUri().path)

                    val kotlinFile: Ast = parseKotlinFile(source)
                    kotlinFile.summary(attachRawAst = false)
                        .onSuccess { astList ->
                            val analyserApi = AnalyserApiImpl(astList)
                            rules.forEach { rule ->
                                rule.analyse(
                                    file,
                                    analyserApi
                                ) { message -> rememberError(file, rule.ruleMessage, message) }
                            }
                        }.onFailure { errors ->
                            errors.forEach(::println)
                        }
                    FileVisitResult.CONTINUE
                } else {
                    FileVisitResult.CONTINUE
                }
            }
        }
        showErrors()
    }
}