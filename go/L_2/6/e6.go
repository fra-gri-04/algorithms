package main

import "fmt"

/*
Write a recursive function that, without using loops, given as input an integer, returns the number of zeros in its base-10 representation (e.g., if the input is 10350, the function returns the value 2)
*/

func number_of_zeros(n int) int {
	if n > 0 {
		val := 0
		if n%10 == 0 {
			val = 1
		}
		return val + number_of_zeros(n/10)
	}
	return 0
}

func main() {
	var n int
	fmt.Scan(&n)

	fmt.Println(number_of_zeros(n))
}
