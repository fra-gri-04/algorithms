package main

import "fmt"

/*
Write a recursive function that, without using loops, given as input a string s[1,..., n], a character c, and an index i ∈ [1, n], returns the number of occurrences of c in s[i,..., n]
*/

func how_many(s string, c string, i int, e int) int {
	if e-i == 0 {
		if string(s[i]) == c {
			return 1
		}
		return 0
	}
	val := 0
	if string(s[i]) == c {
		val = 1
	}
	return val + how_many(s, c, i+1, e)
}

func main() {
	var s string
	var c string
	var i int
	fmt.Print("string => ")
	fmt.Scan(&s)

	fmt.Print("char => ")
	fmt.Scan(&c)

	fmt.Print("index => ")
	fmt.Scan(&i)

	count := how_many(s, c, i, len(s)-1)

	fmt.Println(count)
}
