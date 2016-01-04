package com.mc.free.transactional

class EntityManager
{
	def rollback(): Unit = println("rollback")

	def commit(): Unit = println("commt")

	def start(): Unit = println("starting")

	def close(): Unit = println("close")
}