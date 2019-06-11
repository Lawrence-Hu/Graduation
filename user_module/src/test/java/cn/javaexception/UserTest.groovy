package cn.javaexception

import java.util.stream.Collectors

def a = { a, b -> int d =3; a + b }
println a(1,2)

def b = {->a(2,6) }
b()
