package com.mc.scalalearning

case class Profile(name: Name, id: Long, groups: List[Group])

case class Name(firstName: String, lastName: String)

case class Group(id: Long, name: String)