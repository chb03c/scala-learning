package com.mc.scalalearning

class NonCaseGroup 
{
	var name: String = ""

	var id: Long = 0L
}

object NonCaseGroup
{
	def unapply(group: NonCaseGroup): Option[(String, Long)] =
	{
		Some((group.name, group.id))
	} 

	def apply(name: String, id: Long): NonCaseGroup = 
	{
		val result = new NonCaseGroup()
		result.name = name
		result.id = id
		result
	}
}