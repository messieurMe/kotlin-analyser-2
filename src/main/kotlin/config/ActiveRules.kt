package config

import api.AnalyzerRule
import rules.AllIsGood
import rules.NoGenericImportsRule

enum class ActiveRules(val rule: AnalyzerRule) {
    NO_GENERIC_IMPORTS(NoGenericImportsRule()),
    ALL_IS_GOOD(AllIsGood())
}