package com.mc.free.transactional

class StringPersistence
{	
	def update(model: String): Free[TransactionAction, Either[Error, Long] =
		Update(model, result => ())

	def get(id: Long): Free[TransactionAction, Option[String]] =
		Get(id, str=>())

	def delete(id: Long): Free[TransactionAction, Either[Error, Long]] = 
		Delete(id, result => ())
}