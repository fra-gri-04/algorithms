package main

import (
	"fmt"
	"time"
)

/*
Write a recursive function that, without using loops, given two integers a, b, calculates the sum of all the numbers in the integer range [a, b]
*/
func recursive_sum_interval(a, b int) int {
	if b-a == 0 {
		return b
	}
	return b + a + recursive_sum_interval(a+1, b-1)
}

func main() {
	var a, b int
	fmt.Scan(&a)
	fmt.Scan(&b)

	start := time.Now()
	recursive_sum := recursive_sum_interval(a, b)

	fmt.Println("recursive_sum =", recursive_sum)
	/* To calculate gauss' sum, I calculated sum(b) - sum(a-1) */
	fmt.Println("Gauss sum =", (b*(b+1))/2-((a-1)*(a))/2)

	fmt.Printf("time taken: %.7fs\n", time.Since(start).Seconds())
}
