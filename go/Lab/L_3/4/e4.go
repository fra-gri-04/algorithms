package main

import (
	"fmt"
	"math/rand"
	"time"
)

/*
	Write a Go program that, given an integer n > 1 as input, generates a slice of n polynomials of degree 49. Such a polynomial is represented by an array C of 50 doubles containing the coefficients of the polynomial: C[i] specifies the coefficient of xⁱ.
	Moreover, write a function that compares two polynomials P₁ and P₂ according to the following criterion.
	P₁ and P₂ are evaluated for all integers in the interval [1, 1000]; let sum₁ be the sum of the results of these 1000 evaluations on P₁, and similarly sum₂ for P₂. We define P₁ < P₂ if and only if sum₁ < sum₂.

	Hint: to evaluate a polynomial, use Horner’s rule — https://it.wikipedia.org/wiki/Regola_di_Horner
	
	Finally, implement the Insertion Sort algorithm to reorder an array of polynomials using the comparison function defined above. Test the execution time of Insertion Sort for an increasing number n of polynomials; compare it with the execution time of Insertion Sort on the same number n of integers (explicitly implement Insertion Sort for integers as well).
*/

func generate_array_int() []int {
	var array []int
	for i := 0; i < N; i++ {
		var signum int = 0
		negative := rand.Intn(2) == 1
		if negative {
			signum = 1
		} else {
			signum = -1
		}
		array = append(array, rand.Int()*10*signum)
	}
	return array
}

func generate_array_polynomial() [][]float64 {
	var array [][]float64
	for i := 0; i < N; i++ {
		array = append(array, generate_polynomial())
	}
	return array
}

func generate_polynomial() []float64 {
	var polinomio []float64
	for i := 0; i < POLYNOMIAL_GRADE; i++ {
		var signum float64 = 0
		negative := rand.Intn(2) == 1
		if negative {
			signum = 1
		} else {
			signum = -1
		}
		polinomio = append(polinomio, (rand.Float64() * 10 * signum)) // math.Round(rand.Float64()*10*signum)/1
	}
	return polinomio
}

func horner(p []float64, x float64, i int) float64 {
	// valutati da 1 a 1000

	// Pn(x) = an + x(an-1 + x(an-2 + x(an-3 + ... + x(a1 + a0x))))

	if i == len(p)-1 {
		return p[i]
	}
	return p[i] + x*horner(p, x, i+1)
}

func test_generation() {
	var polynomial []float64
	fmt.Print("Do you want to insert data manually? \n=>")
	choice := "n"
	fmt.Scan(&choice)
	if choice == "y" {
		var a float64
		for i := 0; i < 3; i++ {
			fmt.Scan(&a)
			polynomial = append(polynomial, a)
		}
	} else {
		polynomial = generate_polynomial()
	}
	fmt.Print("Valuta per x = ? \n=>")
	var x float64
	fmt.Scan(&x)
	fmt.Println(polynomial)
	fmt.Println(horner(polynomial, x, 0))
}

func test_confronti() {
	var p1 []float64
	var p2 []float64

	p1 = generate_polynomial()
	p2 = generate_polynomial()

	fmt.Println("p1 < p2 =", compare_polynomial(p1, p2))
}

func compare_polynomial(p1, p2 []float64) bool {
	var sum1 float64
	var sum2 float64
	var i float64
	for i = 0; i < EVALUATION_VALUE; i++ {
		sum1 += horner(p1, i, 0)
		sum2 += horner(p2, i, 0)
	}
	return sum1 < sum2
}

func insertion_sort_polynomial(array_p [][]float64) [][]float64 {
	var x []float64
	x = make([]float64, POLYNOMIAL_GRADE)
	for k := 1; k < N; k++ {
		// insert a[k] in the right place in a[0...k-1]
		copy(x, array_p[k])
		j := k - 1
		// ... and x < a[j]
		for j >= 0 && compare_polynomial(x, array_p[j]) {
			copy(array_p[j+1], array_p[j]) // assign a[j+1] = a[j]
			j = j - 1
		}
		copy(array_p[j+1], x)
	}
	return array_p
}

func insertion_sort(a []int) []int {
	for k := 1; k < N; k++ {
		// insert a[k] in the right place in a[0...k-1]
		x := a[k]
		j := k - 1
		for j >= 0 && a[j] > x {
			a[j+1] = a[j]
			j = j - 1
		}
		a[j+1] = x
	}
	return a
}

const POLYNOMIAL_GRADE = 50
const EVALUATION_VALUE = 1000
const N = 500

func main() {
	var start time.Time
	var end time.Duration

	array_n := generate_array_int()
	start = time.Now()
	array_n = insertion_sort(array_n)
	end = time.Since(start)
	fmt.Printf("array of ints sorted in:%.7fs\n", end.Seconds())

	array_polynomials := generate_array_polynomial()
	start = time.Now()
	array_polynomials = insertion_sort_polynomial(array_polynomials)
	end = time.Since(start)
	fmt.Printf("array of polynomials sorted in: %.7fs\n", end.Seconds())
}
