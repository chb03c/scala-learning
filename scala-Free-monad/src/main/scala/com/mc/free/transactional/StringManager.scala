package com.mc.free.transactional

import com.mc.free._
import com.mc.free.transactional.Interpruters._

class StringManager
{
	val stringPersistence = new StringPersistence()

	def doTheThing(id: Long): Either[Error, Long] = process(id).lift()

	def get(id: Long): Option[Model] = stringPersistence.get(id).lift()

	def update(model: Model): Either[Error, Long] = stringPersistence.update(model).lift()

	def delete(id: Long): Either[Error, Long] = stringPersistence.delete(id).lift()

	private def process(id: Long): Free[TransactionAction, Either[Error, Long]] =
		for{
			item <- (for(i <- stringPersistence.get(id).lift()) yield i) orElse (Rollback(Error(), Done(None)))

			result <- stringPersistence.update(item)

			delResult <- stringPersistence.delete(id)

		} yield delResult
}