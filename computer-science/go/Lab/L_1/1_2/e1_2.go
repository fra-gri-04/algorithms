package main

import (
	"fmt"
	"math/rand"
	"os"
	"strings"
	"time"
)

/*
Consider a simple program that reads a series of characters from an input file, separated by newline characters (\n), concatenates them into a single string (separated by spaces), and writes the resulting string to a new output file. Write two versions of the program: the first reads one character at a time using a for loop, concatenating each character individually to an initially empty string s using the + operator. The second reads all characters first and then concatenates them into a single string using the strings.Join function. Compare the execution time of the two programs for an increasing number of input characters (1000, 10000, 100000, and 1000000) using the time package.
Can you justify the difference in execution times in terms of asymptotic complexity? What is the complexity of each version of the algorithm?
*/

func join_concatenation(fp *os.File) string {
	var char string
	var s_list []string

	// read file lines
	_, err := fmt.Fscanln(fp, &char)
	for err == nil {
		s_list = append(s_list, char)
		_, err = fmt.Fscanln(fp, &char)
	}
	s := strings.Join(s_list, "")
	//fmt.Println(s)
	return s
}

func plus_concatenation(fp *os.File) string {
	var char string
	var s string

	// read file lines
	_, err := fmt.Fscanln(fp, &char)
	for err == nil {
		s += char
		_, err = fmt.Fscanln(fp, &char)
	}
	//fmt.Println(s)
	return s
}

func e2() {
	var fp *os.File
	var start time.Time
	fp, _ = os.Open("chars.txt")
	start = time.Now()
	_ = plus_concatenation(fp)
	fmt.Printf("plus concatenation used took: %.7f s.\n", time.Since(start).Seconds())
	fp.Close()

	fp, _ = os.Open("chars.txt")
	start = time.Now()
	_ = join_concatenation(fp)
	fmt.Printf("join concatenation used took: %.7f s.\n", time.Since(start).Seconds())
	fp.Close()
}

/*
Write a program that, given an integer input n > 0, generates n characters pseudo-randomly, selecting them uniformly from a, b, ..., y, z, 0, 1, ..., 9, and writes them to a file, one per line (thus separated by the newline character \n)
*/

func e1(n int) {
	var random_index int
	fp, _ := os.Create("chars.txt")
	defer fp.Close()

	for i := 0; i < n; i++ {
		random_index = rand.Intn(36)
		fp.WriteString(string(CHARS[random_index]) + "\n")
	}
}

const CHARS = "qwertyuioplkjhgfdsazxcvbnm1234567890"

func main() {
	var n int
	fmt.Scan(&n)

	e1(n)

	e2()

	/* On large numbers of characters, such as 1 000 000, the plus concatenation took ~67 seconds, and the join ~5. */
}
