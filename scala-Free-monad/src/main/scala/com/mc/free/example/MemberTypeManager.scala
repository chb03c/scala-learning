package com.mc.free.example

import com.mc.free.example.Persistence._

class MemberTypeManager
{
	def doSomething(id: Long): Unit =
	{
		for{
			mt <- get(id)

			result <- put(mt.copy(name = "Blah2"))

		} yield result
	}

	private def put(mt: MemberType): Free[Persistence, Unit] =
		More(Put(mt, Done(())))

	private def get(id: Long): Free[Persistence, MemberType]  = 
		More(Get(id, Done()))

	private def delete(id: Long): Free[Persistence, Unit] =
		More(Delete(id, Done(())))
}