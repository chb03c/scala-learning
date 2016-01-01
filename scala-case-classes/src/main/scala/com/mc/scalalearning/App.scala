package com.mc.scalalearning

/**
 * @author ${user.name}
 */
object App 
{

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

  	val johnnysProfile = jimsProfile.copy(name = johnny, id = 9L)
  }

  def patternMatching()
  {
  	val jim = Name(firstName = "Jim", lastName = "Bean")
  	val johnny = jim.copy(firstName = "Johnny")
  	val jimsGroups = List(Group(id = 8L, name = "Admin"))
  	val jimsProfile = Profile(id = 7L, name = jim, groups = jimsGroups)

  	val johnnysProfile = jimsProfile.copy(name = johnny, id = 9L)

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

  def doubleCollon()
  {
      // variable to the left of the :: is the first item in the List to the right of the :: is the rest of the list
      //Nil is an empty List (not an empty <file in the blank collection>)
      val test1 = 1 :: Nil
      val test2 = List(1)

      test.equals(test2)

      val example = 1 :: 2 :: 3 :: 4 :: Nil

      //You can exstract items from a list and assign to vars or vals like so
      val first :: second :: rest = example
      /*
        first: Int = 1
        second: Int = 2
        rest: List[Int] = List(3, 4)
      */
  }

  def patternMatchingList()
  {
  	    val list = List("Foo", "bar", "baz")

  	    list match{
  	    	case first :: rest => print(first)
  	    	case first :: Nil => print(s"There is only one item left in the list $first")
  	    	case Nil => print("This list is empty")
  	    }
  }

  def patternMatchingForComprensions()
  {
    val jim = Name(firstName = "Jim", lastName = "Bean")
    val johnny = jim.copy(firstName = "Johnny")
    val jimsGroups = List(Group(id = 8L, name = "Admin"))
    val jimsProfile = Profile(id = 7L, name = jim, groups = jimsGroups)

    val johnnysProfile = jimsProfile.copy(name = johnny, id = 9L)

    val profiles = jimsProfile :: johnnysProfile :: Nil

    val names = for{
      profile@Profile(name, id, groups) <- profiles
    } yield name

    //names equals a List of Name objs: List(Name(Jim, Bean), Name(Johnny, Bean))

    val formattedNames = for{
      _@Profile(Name(firstName, lastName), _, _) <- profiles
    } yield s"$firstName; $lastName"

    //formattedNames equals a List of Strings with the formatted name:  List(Jim; Bean, Johnny; Bean)

    //What would my result be if I had this list:

    val test = Name("Jim", "Bean") :: Group(8L, "Admin") :: Nil

    val example = for{
      _@Name(fName, lName) <- test
    } yield fName
  }

}
