package main

import "fmt"

func is_in_order(array []int, i int, e int) bool {
	if e-i == 0 {
		return true
	}
	if e-i == 1 {
		return array[i] < array[e]
	}
	if array[i] < array[e] {
		return is_in_order(array, i+1, e-1)
	}
	return false
}

/*
Write a recursive function that, without using loops, given as input an array of integers A[1,..., n], returns TRUE if A is sorted in ascending order, FALSE otherwise.
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
	fmt.Println(is_in_order(array, 0, len(array)-1))
}
