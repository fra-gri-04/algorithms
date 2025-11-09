package main

import "fmt"

func rec_max(array []int, i, n int) int {
	if n-i == 2 {
		if array[i] < array[n] {
			return array[n]
		}
		return array[i]
	}
	max := rec_max(array, i+1, n)
	if max > array[i] {
		return max
	}
	return array[i]
}

/*
Write a recursive function that, without using loops, given as input an array of integers A[1,..., n] and an index i ∈ [1, n], returns the maximum value in A[i,..., n]
*/
func main() {
	var x int
	var array []int

	fmt.Println("array => ")

	_, err := fmt.Scan(&x)
	for err == nil {
		array = append(array, x)
		_, err = fmt.Scan(&x)
	}
	fmt.Print("index => ")
	var i int
	fmt.Scan(&i)
	max := rec_max(array, i, len(array)-1)
	fmt.Println("max =", max)
}
