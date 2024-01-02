package api

import java.nio.file.Path
import kotlin.String

abstract class AnalyzerRule {

    abstract val ruleMessage: String

    /**
     * Called each time analyzer opens new file.
     *
     * @param file
     *      file which is currently opened
     * @param api
     *      AnalyzerApi for current file
     * @param errorCallback
     *      In case you found error this callback should be called to notify about it
     */
    abstract fun analyse(file: Path, api: AnalyserApi, errorCallback: (String) -> Unit)
}