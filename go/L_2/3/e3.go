package main

import "fmt"

func rec_palindrome(s string, i, f int) bool {
	if f-i <= 1 {
		return s[f] == s[i]
	}
	if s[i] == s[f] {
		return rec_palindrome(s, i+1, f-1)
	}
	return false
}

/*
Write a recursive function that, without using loops, given as input a string s[1,..., n], returns TRUE if s is a palindrome (that is, it reads the same from right to left and from left to right), and FALSE otherwise. Hint: there are two base cases, depending on whether the length n of the string is even or odd!
*/

func main() {
	var s string

	fmt.Scan(&s)

	pal := rec_palindrome(s, 0, len(s)-1)

	fmt.Println(pal)
}
