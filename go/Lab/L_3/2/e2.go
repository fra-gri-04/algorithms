/*
Consider two sets, X and Y, each consisting of n distinct integers and represented by a sorted array (note that the same number may appear in both X and Y, but not twice within the same set).
Write the pseudocode of an algorithm that, in O(n) time, takes as input the two sorted arrays representing X and Y, and returns a sorted array Z representing the set (X ∪ Y) \ (X ∩ Y).
Implement it in Go.

	PSEUDO:

	algorithm sum_array(X[0...n-1], Y[0...n-1])
		// sorted X and Y
		// returns Z = (X U Y) \ (X ∩ Y)
		ix = 0
		iy = 0
		iz = 0
		for k=0 to k == n-1 do
			if X[ix] < Y[iy]
				Z[iz] = X[ix]
				ix++
			else if Y[iy] < X[ix]
				Z[iz] = Y[iy]
				iy++
			else
				// elements are equal
				ix++
				iy++
			iz++
		if ix < n-1
			while ix < n-1
				Z[iz] = x[ix]
				iz++
				ix++
		if iy < n-1
			while iy < n-1
				Z[iz] = y[iy]
				iz++
				iy++
*/

package main

import "fmt"

func direct_sum(X []int, Y []int, n int) (Z []int) {
	// sorted X and Y
	// returns Z = (X U Y) \ (X ∩ Y)
	ix := 0
	iy := 0
	iz := 0
	for ix < n && iy < n {
		if X[ix] < Y[iy] {
			Z = append(Z, X[ix])
			ix++
		} else if Y[iy] < X[ix] {
			Z = append(Z, Y[iy])
			iy++
		} else {
			ix++
			iy++
		}
		iz++
	}
	if ix < n {
		for ; ix < n; ix++ {
			Z = append(Z, X[ix])
			iz++
		}
	}
	if iy < n {
		for ; iy < n; iy++ {
			Z = append(Z, X[iy])
			iz++
		}
	}

	return Z

}

func main() {
	var X, Y []int

	X = []int{1, 3, 5, 7, 9}
	Y = []int{2, 3, 4, 5, 6}

	Z := direct_sum(X, Y, 5)

	fmt.Println(Z)
}
