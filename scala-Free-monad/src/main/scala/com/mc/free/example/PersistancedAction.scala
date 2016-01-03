package com.mc.free.example

sealed trait PersistencedAction[Action]{

}

case class Put[Model, Action](item: Model, action: Action) extends PersistenceAction[Action]

case class Get[Model, Action](id: Long, action: Action) extends PersistencedAction[Action]

case class Delete[Action](id: Long, action: Action) extends PersistencedAction[Action]

object PersistencedAction
{
	implicit val persistenceFunctor = new Functor[PersistencedAction] {
		def map[A, B](a: PersistencedAction[A])(f: A => B) = a match {
			case Put(item, h) => 
				println("Putting item")
				Put(item, f(h))
			case Get(id, h) => 
				println("Getting item")
				Get(id, f(h))
			case Delete(id, h) => 
				println("Deleting Item")
				Delete(id, f(h)) 
		}
	}	
}