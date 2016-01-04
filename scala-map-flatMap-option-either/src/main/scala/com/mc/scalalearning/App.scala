package com.mc.scalalearning

/**
 * @author ${user.name}
 */
object App {
  
  def procedurealToFunction()
  {
  	case class Foo(bar: Bar)
  	case class Bar(baz: Baz)
  	case class Baz(name: String)
  	
  	val item = Foo(Bar(Baz("Blah")))

  	if(item != null)
  	{
  		  val bar = item.bar
  		  if(bar != null)
  		  {
  			   val baz = bar.baz
  			   if(bar.baz != null)
  			   {
  				    return baz.name

  			   }
  			   else
  			   {
  				    return null
  			   }
  		  }
  		  else
  		  {
  			   return null
  		  } 
  	}
  	else
  	{
  		  return null
  	}


  	//
  	Option(item).flatMap{foo=>
  		Option(foo.bar).flatMap{bar=>
  			Option(bar.baz).map{baz=>
  				baz.name
  			}
  		}
  	}

  	for{
  		foo <- Option(item)
  		bar <- Option(foo.bar)
  		baz <- Option(bar.baz)
  	} yield baz.name
  }

  def mapFlatMap() {
   		val list = "1" :: "2" :: "3" :: "4" :: Nil

   		val result = list.map(i=>i.toInt)

   		result.foreach(i=>println(i))

   		val list2 = List(List("1", "2", "3"), List("4", "5", "6"))

   		list2.flatMap(i=>i.map(j=>j.toInt))
  }
}


