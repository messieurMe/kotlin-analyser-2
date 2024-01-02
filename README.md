Pipeline failed to prove that it can find problems. You can see reason in logs.

API info:
- /api/AnalyserApi - interface through which you can access ast and all required info
- /api/AnalyzerRule - abstract class which you have to implement if you want to add new rule

All api classes are explain their methods with javadocs (or kotlindocs?)

How to add rule?
- Create class in /rules
- Inherit from AnalyzerRule
- Add your class in enum in /config/ActiveRules (you also can turn it off by commenting)

Existing rules:
- AllIsGood: never fails and prints message on each file.
- NoGenericImports: restricts using star projection in imports. For example `import a.b.c.*`. You should specify path to each imported class or function
