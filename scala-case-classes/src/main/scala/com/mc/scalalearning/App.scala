package com.mc.scalalearning

/**
 * @author ${user.name}
 */
object App {
  

  def main(args : Array[String]) {
    println( "Hello World!" )
    println("concat arguments = " + foo(args))
  }

  def create()
  {
  	val jim = Name(firstName = "Jim", lastName = "Bean")
  	val jimsGroups = List(Group(id = 8L, name = "Admin"))
  	val profile = Profile(id = 7L, name = jim, groups = jimsGroups)
  }

  def copying()
  {
  	val jim = Name(firstName = "Jim", lastName = "Bean")
  	val johnny = jim.copy(firstName = "Johnny")
  	val jimsGroups = List(Group(id = 8L, name = "Admin"))
  	val jimsProfile = Profile(id = 7L, name = jim, groups = jimsGroups)

  	val johnnysProfile = profile.copy(name = johnny, id = 9L)
  }

  def patternMatching()
  {
  	val jim = Name(firstName = "Jim", lastName = "Bean")
  	val johnny = jim.copy(firstName = "Johnny")
  	val jimsGroups = List(Group(id = 8L, name = "Admin"))
  	val jimsProfile = Profile(id = 7L, name = jim, groups = jimsGroups)

  	val johnnysProfile = profile.copy(name = johnny, id = 9L)

  	jimsProfile match 
  	{
  		case Profile(name, id, groups) => //do something
  		case Profile(_, 7L, _) => //do something else
  		case Profile(Name("Jim", _), _, _) => //do something again
  		case Profile(_, _, firstGroup :: rest) => //firstGroup is the group with index 0 rest is the remaining items in the list
  	  	case _ => //wild card / catch all
    }
  	
  }

  def patternMatchingGroup()
  {
    	val group = Group("Blah" 7L)
    	group match{
    		case Group("Blah", _) => //do something
    	}
  }

  def patternMatchingList()
  {
  	    val list = "Foo", "bar", "baz"

  	    list match{
  	    	case first :: rest => print(first)
  	    	case first :: Nil => print(s"There is only one item left in the list $first")
  	    	case Nil => print("This list is empty")
  	    }

  	    //or

  	    list match{
  	    	case List(first, last@_*) => print(first)
  	    	case List(first, Nil) => print(s"There is only one item left in the list $first")
  	    	case List(Nil) => print("This list is empty")
  	    }
  }

}
