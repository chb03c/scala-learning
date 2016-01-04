package com.mc.free.transactional

object Interpruters
{
	implicit class HibernateInterpruter[Result](transaction: Free[TransactionAction, Result]) extends AnyVal =
	{
		def lift(): Result =
		{
			val em = new EntityManager()

			@tailrec
			def interprut(transaction: Free[TransactionAction, Result]): Result = 
			{
				transaction match
				{
					case Get(id, onResult) => interprut(onResult(Some("Test")))
					case Update(model, onResult) => interprut(onResult(Right(7L)))
					case Delete(id, onResult) => interprut(onResult(Right(8L)))
					case Rollback(error, _) =>
						em.rollback()
						Left(error)
				}
			}

			em.start()
			val result = interprut(transaction)
			em.commit()
			em.close
			result
		}
			
	}
}