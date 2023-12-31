import kotlinx.ast.common.AstSource
import kotlinx.ast.common.ast.Ast
import kotlinx.ast.common.print
import kotlinx.ast.grammar.kotlin.common.summary
import kotlinx.ast.common.klass.*
import kotlinx.ast.grammar.kotlin.target.antlr.kotlin.KotlinGrammarAntlrKotlinParser

fun main() {
    val source = AstSource.File(
        "./src/main/kotlin/Main.kt"
    )
    val kotlinFile = KotlinGrammarAntlrKotlinParser.parseKotlinFile(source)
    kotlinFile.summary(attachRawAst = false)
        .onSuccess { astList ->
            astList.forEach(Ast::print)


            println(astList.last()::class.java.`package`)

            when (val x = astList.last()) {
                is KlassDeclaration -> {
                     println(x)


                }
            }

        }.onFailure { errors ->
            errors.forEach(::println)
        }
}

class Lol{
    fun lol(){
        println(1)
    }
}