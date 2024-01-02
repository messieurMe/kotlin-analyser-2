package rules

import api.AnalyserApi
import api.AnalyzerRule
import java.nio.file.Path

class AllIsGood : AnalyzerRule() {

    override val ruleMessage: String
        get() = "Impossible"

    override fun analyse(file: Path, api: AnalyserApi, errorCallback: (String) -> Unit) {
        println("Your code in following file is breathtaking!\n\tFile: $file")
    }
}
