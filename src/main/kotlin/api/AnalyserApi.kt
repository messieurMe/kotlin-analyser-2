package api

import kotlinx.ast.common.ast.Ast
import kotlin.String

/**
 * The interface through which you can
 * - access ast of current file
 * - callGraph (?)
 * - find ast tree for class using its name
 */
interface AnalyserApi {

    /**
     * Return ast of current file. (It's technically list, but actually tree)
     */
    fun ast(): List<Ast>

    /**
     * Not implemented
     */
    fun callGraph()

    /**
     * Finds ast in which class/function is declared. It uses imports, so both
     * a.b.c.TargetClass and TargetClass will find what you're looking for.
     *
     * Search's scope is only current module, so you will not be able to find any class from other modules.
     */
    fun search(name: String): List<List<Ast>>
}