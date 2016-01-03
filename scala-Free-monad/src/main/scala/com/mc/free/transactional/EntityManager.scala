package com.mc.free.transactional

class EntityManager
{
	def commit(): Unit = println("Committing")

	def rollback(): Unit = println("rolling back")

	def start(): Unit = println("start")

	def close(): Unit = println("closing")

	def execute(): Unit = println("executing")
}