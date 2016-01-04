package com.mc.free.transactional

class StringManager
{
	val stringPersistence = new StringPersistence()

	def doTheThing(id: Long): Either[Error, Long] = process(id).lift()

	def get(id: Long): Option[String] = stringPersistence.get(id).lift()

	def update(model: String): Either[Error, Long] = stringPersistence.update(model).lift()

	def delete(id: Long): Either[Error, Long] = stringPersistence.delete(id).lift()

	private def process(id: Long): Free[TransactionAction, Either[Error, Long]] =
		for{
			item <- for(i <- stringPersistence.get(id)) yield i

			result <- stringPersistence.update(item)

			delResult <- stringPersistence.delete(id)

		} yield delResult
}