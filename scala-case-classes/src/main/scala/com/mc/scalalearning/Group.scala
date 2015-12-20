package com.mc.scalalearning

class Group 
{
	var name: String = ""

	var id: Long = 0L
}

object Group
{
	def unapply(group: Group): Option[(String, Long))] =
	{
		Some((group.name, group.id))
	} 

	def apply(name: String, id: Long): Group = 
	{
		val result = new Group()
		result.name = name
		result.id = id
		result
	}
}