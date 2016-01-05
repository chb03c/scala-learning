package com.mc.free.transactional

import com.mc.free._


sealed trait TransactionAction[Next]

case class Error()

abstract class Model(id: Long)

case class MemberType(id: Long, name: String) extends Model(id)

case class Get[Next](id: Long, onValue: Option[Model] => Next) extends TransactionAction[Next]

case class Rollback[Next](error: Error, next: Next) extends TransactionAction[Next]

case class Update[Next](model: Model, onValue: Either[Error, Long] => Next) extends TransactionAction[Next]

case class Delete[Next](id: Long, onResult: Either[Error, Long] => Next) extends TransactionAction[Next]

object TransactionAction
{
	implicit val transactionAction = new Functor[TransactionAction]{
		def map[A, B](transaction: TransactionAction[A])(f: A => B): TransactionAction[B] =
		{
			transaction match
			{
				case Update(model, onResult) => Update(model, onResult andThen f)
				case Get(id, onResult) => Get(id, onResult andThen f)
				case Delete(id, onResult) => Delete(id, onResult andThen f)
				case Rollback(error, next) => Rollback(error, f(next))
			}
		}
	}
}




