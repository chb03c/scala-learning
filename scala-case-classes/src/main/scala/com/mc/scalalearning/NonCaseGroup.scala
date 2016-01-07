package com.mc.scalalearning

class NonCaseGroup
{
    var name: String = ""

    var id: Long = 0L

    var test: Int = 0
}

object NonCaseGroup
{
    def unapply(group: NonCaseGroup): Option[(String, Long)] =
    {
        if(group.id != 0)
            Some((group.name, group.id))
        else None
    }

    def apply(name: String, id: Long): NonCaseGroup =
    {
        val result = new NonCaseGroup()
        result.name = name
        result.id = id
        result
    }
}

object GroupNameAdmin
{
    def unapply(group: NonCaseGroup): Option[(String, Long)] =
    {
        if(group.name.eq("Admin")) Some.... else None
    }
}