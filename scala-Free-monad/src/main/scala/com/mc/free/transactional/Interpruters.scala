package com.mc.free.transactional

import com.mc.free._
import scala.annotation.tailrec

object Interpruters
{
	implicit class HibernateInterpruter[Result](val transaction: Free[TransactionAction, Result]) extends AnyVal
	{
		def lift(): Result =
		{
			val em = new EntityManager()

			@tailrec
			def interprut(transaction: Free[TransactionAction, Result]): Result = 
			{
				transaction match{
					case More(action) =>
						action match
						{
							case Get(id, onResult) => interprut(onResult(Some(MemberType(7L, "Member"))))
							case Update(model, onResult) => em.save(model) match{
									case success@Right(_) => interprut(onResult(success))
									case failure@Left(error) => interprut(More(Rollback(error, Done(failure.asInstanceOf[Result]))))
							}
							case Delete(id, onResult) => interprut(onResult(Right(8L)))
							case Rollback(error, _) =>
								em.rollback()
								Left[Error, Long](error).asInstanceOf[Result]
						}
					case Done(result) => result
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