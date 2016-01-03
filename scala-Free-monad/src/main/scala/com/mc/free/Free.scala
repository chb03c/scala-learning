package com.mc.free

/*
Definitions: Functor - has a mathmatical definition but for the purpuses of programming 
						it is anything with a map method with the following method signature
			  Monad - has a mathmatical definition but for the purpuses of programming 
			  			it is anything with a map method witht eh following method signature 
			  			and a flatMap method with the following method signature
*/

trait Functor[F[_]]{
	def map[A, B](a: F[A])(f: A => B): F[B] 
}

class Free[F[_], A](implicit F: Functor[F]){
	def flatMap[B](f: A => Free[F, B]): Free[F, B] = 
		this match{
			case Done(a) => f(a)
			case More(k) => More(F.map(k)(_ flatMap f))
		}
	def map[B](f: A => B): Free[F, B] =
		flatMap(x => Done(f(x)))
}

case class Done[F[_]: Functor, A](a: A) extends Free[F, A]
case class More[F[_]: Functor, A](k: F[Free[F, A]]) extends Free[F, A]