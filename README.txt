1.) case classes
2.) Pattern Matching
3.) map/flatMap
	- for-yield
	- Option
	- Either
4.) Collections lib
5.) Functional Programming
	- Functions as Data structors
	- PartialFunctions
	- Function Composition
6.) Scala-Java Byte Code and Debugging with intellij
7.) String Interpilation
8.) implicits
	- Pimp my lib
	- TypeClasses

Each Project was created with the following maven command: 
mvn archetype:generate -B \
 -DarchetypeGroupId=pl.org.miki -DarchetypeArtifactId=scala-quickstart-archetype -DarchetypeVersion=0.8.2 \
 -DgroupId=<groupId> -DartifactId=<project name> -Dversion=1.0 -Dpackage=<base package>