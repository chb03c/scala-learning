package com.mc.scalalearning

/**
 * @author ${user.name}
 */
object App 
{

  def creating()
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
  		case profile@Profile(_, 7L, _) => //do something else
  		case Profile(name@Name("Jim", _), _, _) => //do something again
  		case Profile(_, _, firstGroup :: rest) => //firstGroup is the group with index 0 rest is the remaining items in the list
  	  case _ => //wild card / catch all
    }
  	
  }

  def patternMatchingGroup()
  {
    	val group = Group(7L, "Blah")
    	group match{
    		case Group(_, "Blah") => //do something
    	}
  }

  def doubleCollon()
  {
      // variable to the left of the :: is the first item in the List to the right of the :: is the rest of the list
      //Nil is an empty List (not an empty <file in the blank collection>)
      val test1 = 1 :: Nil
      val test2 = List(1)

      test1.equals(test2)

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
          case first :: Nil => println(s"There is only one item left in the list $first")
  	    	case first :: rest => println(first)
  	    	case Nil => println("This list is empty")
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
        } yield (s"$firstName; $lastName")

        //formattedNames equals a List of Strings with the formatted name:  List(Jim; Bean, Johnny; Bean)

        //What would my result be if I had this list:

        val test = Name("Jim", "Bean") :: Group(8L, "Admin") :: Nil

        val example = for{
          _@Name(fName, lName) <- test
        } yield fName
  }

  case class Error()

  def patternMatchingOnEither()
  {
        val someEither: Either[Error, Long] = Left(Error())

        val left@Left(error) = someEither

        //The VariableLeft will be equal to someEither and the variable error will be equal to the Error obj inside the Left
        //What happens if someEither was really a right?

        val someOtherEither: Either[Error, Long] = Right(7L)

        val left@Left(error) = someOtherEither

        //You would get a scala.MatchError: Right(7) (of class scala.util.Right)
  }

}
