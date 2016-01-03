package com.mc.free

object Pure {
  sealed trait IO[A] {
    def flatMap[B](f: A => IO[B]): IO[B] =
      Suspend(() => f(this.run))

    def map[B](f: A => B): IO[B] =
      Return(() => f(this.run))

    def run: A =
      this match {
        case Return(a) => a()
        case Suspend(s) => s().run
      }
  }
  
  final case class Return[A](a: () => A) extends IO[A] 
  final case class Suspend[A](s: () => IO[A]) extends IO[A]

  object IO {
    def point[A](a: => A): IO[A] =
      Return(() => a)
  } 

  def println(msg: String): IO[Unit] =
    IO.point(Predef.println(msg))
}