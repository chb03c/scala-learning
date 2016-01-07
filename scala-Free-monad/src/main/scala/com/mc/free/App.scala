package com.mc.free

import com.mc.free.transactional._

object App
{
	def main(args: Array[String])
	{
		val stringManager = new StringManager()
		stringManager.doTheThing(7L)
	}
}