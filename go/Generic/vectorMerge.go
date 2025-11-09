package main

import (
	"fmt"
	"math/rand/v2"
	"sort"
)

/* ---------- Data generation ---------- */
func randomArray() []int {
	// generate array of length n with random values
	var array = []int{}
	for i := 0; i < M; i++ {
		array = append(array, rand.IntN(MAX_INT))
	}
	return array
}

func generateVector() [][]int {
	var vector = [][]int{}
	for i := 0; i < N; i++ {
		x := randomArray()
		sort.Ints(x)
		vector = append(vector, x)
	}
	return vector
}

/* ------------------------------------- */

func merge(A []int, B []int) (C []int) {
	// sorted A and B, both of length M
	ia := 0
	ib := 0
	lA := len(A)
	lB := len(B)
	for ia < lA && ib < lB {
		if A[ia] <= B[ib] {
			C = append(C, A[ia])
			ia++
		} else if B[ib] < A[ia] {
			C = append(C, B[ib])
			ib++
		}
	}
	for ; ia < lA; ia++ {
		C = append(C, A[ia])
	}
	for ; ib < lB; ib++ {
		C = append(C, B[ib])
	}
	return C

}

/*
Merges an array of arrays into a single array.
*/
func vectorMerge(V [][]int) []int {
	var l int = len(V)
	if l == 1 {
		return V[0]
	}
	if l == 2 {
		return merge(V[0], V[1])
	}
	var k int = l / 2

	// Divide Et Impera
	x := vectorMerge(V[0:k])
	y := vectorMerge(V[k:l])

	return merge(x, y)
}

const MAX_INT int = 100 // max int generated
const M int = 5         // length of individual arrays
const N int = 10        // length of vector of arrays

/*
You are given n arrays. Each array is sorted in ascending order and consists of m elements. Using the divide-and-impera technique, design an algorithm to merge these arrays. Estimate the total number of comparisons as a function of `n` and `m`, derive the corresponding recurrence equation, and solve it.
*/
func main() {
	// the given vector of arrays
	var vector [][]int = generateVector()
	fmt.Println(vector)
	fmt.Println(vectorMerge(vector))
}
