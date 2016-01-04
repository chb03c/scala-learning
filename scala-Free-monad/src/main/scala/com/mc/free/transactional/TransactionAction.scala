package com.mc.free.transactional

import scala.annotation.tailrec

sealed trait TransactionAction[Next]

case class Error()

case class Get[Model, Next](id: Long, onValue: Model => Next) extends TransactionAction[Next]

case class Rollback[Next](error: Error, next: Next) extends TransactionAction[Next]

case class Update[Model, Next](model: Model, onValue: Either[Error, Long] => Next) extends TransactionAction[Next]

case class Delete[Next](id: Long, onResult: Either[Error, Long] => Next) extends TransactionAction[Next]

object TransactionAction
{
	implicit val transactionAction = new Functor[TransactionAction]{
		def map[A, B](transaction: TransactionAction[Next])(f: A => B): TransactionAction[Next] =
		{
			transaction match
			{
				case Update(model, onResult) => Update(model, onResult andThen f)
				case Get(model, onResult) => Get(model, onResult andThen f)
				case Delete(model, onResult) => Delete(model, onResult andThen f)
				case r@Rollback(error, next) => r
			}
		}
	}

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




