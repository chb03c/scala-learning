package com.mc.free.example

import com.mc.free.example.PersistencedAction._
import com.mc.free._

class MemberTypeManager
{
	def doSomething(id: Long): Unit =
	{
		for{
			mt <- get(id)

			result <- put(mt.copy(name = "Blah2"))

		} yield result
	}

	private def put(mt: MemberType): Free[PersistencedAction, Unit] =
		More(Put(mt, Done(())))

	private def get(id: Long): Free[PersistencedAction, MemberType]  = 
		More(Get(id, Done(MemberType(7L, "Member"))))

	private def delete(id: Long): Free[PersistencedAction, Unit] =
		More(Delete(id, Done(())))
}