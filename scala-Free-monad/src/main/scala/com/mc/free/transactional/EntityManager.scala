package com.mc.free.transactional

class EntityManager
{
	def rollback(): Unit = println("rollback")

	def commit(): Unit = println("committing")

	def start(): Unit = println("starting")

	def close(): Unit = println("close")

	def save(model: Model): Either[Error, Long] = Right(7L)
}