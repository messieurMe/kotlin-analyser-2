import core.FileWalker

class StaticAnalyzer {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            FileWalker().start()
        }
    }
}
