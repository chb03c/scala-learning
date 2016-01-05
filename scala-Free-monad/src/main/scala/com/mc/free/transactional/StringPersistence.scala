package com.mc.free.transactional

import com.mc.free._

class StringPersistence
{	
	def update(model: Model): Free[TransactionAction, Either[Error, Long]]=
		More(Update(model, result => Done(result)))

	def get(id: Long): Free[TransactionAction, Option[Model]] =
		More(Get(id, str=>Done(str)))

	def delete(id: Long): Free[TransactionAction, Either[Error, Long]] = 
		More(Delete(id, result => Done(result)))
}