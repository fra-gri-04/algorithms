<h1>Testing Algorithms in Java</h1>

# Package `it.modules`

## Sorting

### Comparison-Based Algorithms

##### `void selectionSort(int[] array)`

Sorts the given array.

**Idea:**

Until the array has been fully traversed with index i, starting from position 0 finds the minimum value in the remaining positions and swaps the minimum with the i-th element.

**Complexity:**  

$$T = \mathcal{\theta}(array.length^2)$$

**Parameters:**
- `array`: list of elements.

**Returns:**
Nothing.

---

#### `void insertionSort(int[] array)`

Sorts the given array.

**Idea:** 

Until the array has been fully traversed with index i, takes the i-th element and inserts it in the position in the positions less than i, which represent the ordered side of the array. 

**Complexity:**  

$$T_{best} = \mathcal{\theta}(array.length-1)$$

$$T_{worst} = \mathcal{\theta}(array.length^2)$$

**Parameters:**
- `array`: list of int numbers.

**Returns:**
Nothing.

---

#### `void bubbleSort(int[] array)`

Sorts the given array.

**Idea:** 

Until the array has been sorted, it swaps neighbours if one is less than the other.
Greater elements end up in the end of the array, like bubbles float up to the surface. Thanks to this observation, it does not search always until the end of the array, but until the length of the array minus the number of already checked positions.

**Complexity:**  

$$T_{best} = \mathcal{\theta}(array.length-1)$$

$$T_{worst} = \mathcal{\theta}(array.length^2)$$

**Parameters:**
- `array`: list of int numbers.

**Returns:**
Nothing.

---

#### `void mergeSortUsingSpace(int[] array)`

Sorts the given array using Divide Et Impera concept.

**Idea:**

Splits in half the array, creates two sections, sorts them and then merges the result, picking the minimum value from the two halves until both have been traversed.
It uses more space than the other version by allocating as new arrays the two parts of the original.

**Complexity:**

With an array of length `n`:
$$T = \mathcal{\theta}(n \cdot log(n))$$

Uses $\mathcal{\theta}(n)$ of space.

**Parameters:**
- `array`: list of int numbers.

**Returns:**
Nothing.

---

#### `void mergeSort(int[] array)`

Sorts the given array using Divide Et Impera concept.

**Idea:**

Splits in half the array, sorts the two halves and then merges the result, picking the minimum value from the two halves until both have been traversed.
Instead of creating multiple arrays, it uses indices to indicate the start `s` and the end `e` of the current section inspected, so it does not need more space. 

**Complexity:**

With an array of length `n`:
$$T = \mathcal{\theta}(n \cdot log(n))$$

Uses $\mathcal{\theta}(n)$ of space.

**Parameters:**
- `array`: list of int numbers.

**Returns:**
Nothing.

---

#### `void quickSortUsingSpace(int[] array)`

Sorts the given array using Divide Et Impera concept.

**Idea:**

Splits the array in two parts, in order to have bigger elements to the right and smaller on the left and the one in the middle.
Then sorts the two halves and puts them back together using recursion on both parts.

**Complexity:**

With an array of length n:
$$T_{best} = \mathcal{\theta(n \cdot log(n))}$$

$$T_{worst} = \mathcal{\theta(n^2)}$$

Uses $\mathcal{\theta}(n)$ of space.

**Parameters:**
- `array`: list of int numbers.

**Returns:**
Nothing.

---

#### `void quickSort(int[] array)`

Sorts the given array using Divide Et Impera concept.

**Idea:**

Splits the array in two parts, in order to have bigger elements to the right and smaller on the left and the one in the middle.
Then sorts the two halves and puts them back together using recursion on just the shortest part of the two.

**Complexity:**

With an array of length n:
$$T_{best} = \mathcal{\theta(n \cdot log(n))}$$

$$T_{worst} = \mathcal{\theta(n^2)}$$

Uses $\mathcal{\theta}(n)$ of space.

**Parameters:**
- `array`: list of int numbers.

**Returns:**
Nothing.

---

### Non Comparison-Based Algorithms

#### `void integerSort(int[] array, int k)`
Also called countingSort, it needs to know the range of the numbers present in the array.
Sorts the given array without using comparisons.

**Idea:**

Utilizes a helping array `Y` of length equals to `k`, in order to save recurrencies of elements.
Then iterates trough `Y` and inserts into the result the index `i` repeated `Y[i]` times. 

**Complexity:**

With an array of length `n`:

$$T = \mathcal{O(\max(n, k))}$$

**Parameters:**
- `array`: list of int numbers.
- `k`: max value present in the array to sort.

---

#### `void bucketSort(int[] array, int b)`

Sorts the given array without using comparisons.

**Idea:**
Utilizes a helping array of buckets (queues), to save each element with the key corresponding to the index position.
It then iterates through the array of buckets and through the queues to fill the resulting array.

**Complexity:**

With an array of length `n`:

$$ \text{if b is in the order of \,} \mathcal{O(n) \Rightarrow \;}  T = \mathcal{O(n)}$$

$$ \text{if b is in the order of \,} \mathcal{O(n^2) \Rightarrow \;} T = \mathcal{O(n^2)}$$

$$\text{in general: }T = \mathcal{O(n + b)}$$

**Parameters:**
- `array`: list of int numbers.
- `b`: max value present in the array to sort.

---

Sorts the array without using comparisons.
     * Sorts each element using only one digit of the number itself, repeating bucket sort until all digits are covered
     * @param array
     * @param max
     */
#### `void radixSort(int[] array, int max)`

Sorts the given array without using comparisons.

**Idea:**
Sorts each element using only a group of digit of the number itself at a time, repeating bucket sort until all digits are covered.
Thanks to bucket sort's stability, it can operate on just the single partitions of digits and have the array sorted.

**Complexity:**

With an array of length `n`:

$$\mathcal{T = O(n)}$$


**Parameters:**
- `array`: list of int numbers.
- `b`: max value present in the array to sort.


---
---

## MathFunctions

### `int repeatedAdditions(int a, int b)`

Multiplicate two numbers by repeatedly adding `a` to the result, `b` times.

**Complexity:**  

$$T = \mathcal{O}(b)$$

**Parameters:**  

- `a`: multiplier.
- `b`: multiplicand.

**Return:**  

$$a \cdot b$$

---

### `int russianPeasant(int a, int b)`
Multiplicate two numbers following this rule until `b > 0`:

**Idea:**  

  $$a \times b = 2a \cdot \frac{b}{2}$$
  
With integers, the rule becomes as follows:

$$2a \cdot \frac{b}{2} \ \ if\ b\ is\ even.$$

$$2a \cdot \frac{b-1}{2} + a \ \ \ if\ b\ is\ odd. $$

Basically it keeps doubling a and dividing by 2 b, only adding the current value of a to the result when b is odd.

**Complexity:**  

$$T = \mathcal{O}(b)$$

**Parameters:**  

- `a`: multiplier.
- `b`: multiplicand.

**Returns:**

$$a \cdot b$$

---

### `int[] matMultiply(int[] A , int[] B, int dim)`

**Idea:**

Standard matrix multiply rows per columns for square matrices of dimension n.
Every cell C[i,j] contains the sum of the multiplication between the i-th row and the j-th column elements such as

$$C_{ij} = a_{i0} * b_{0j} + a_{i1} * b_{1j} + \dots + a_{in} * b_{nj}$$

The matrices are implemented in a single dimension array of integers `int[n * n]`.
In order to reach a position on the i-th row and on the j-th column, the array index is calculated by multiplying the rows index by the dimension of the matrix and then adding the column index.

$$ M_{ij} ⇔ M[i \cdot n +j] $$

**Complexity:**

$$T = \mathcal{\theta}(n^3)$$


**Parameters:**

- `A` square matrix $n \times n$ 
- `B` square matrix $n \times n$ 

**Returns:**

$$C = A \times B$$

---

### `int[] strassenMatMultiply(int[] A, int[] B, int dim)`

**Idea:**

Matrix multiply rows per columns for square matrices of dimension n.
It tries to reduce complexity from $n^3$ of the standard matrix multiply to something better.
It uses the _Divide Et Impera_ concept, creating 4 matrices for each input matrix, in order to calculate m1, m2, ... m7 matrices and then recombine everything in the result matrix.
It is explained much better <a href="https://en.wikipedia.org/wiki/Strassen_algorithm" target="blank" title="Learn the idea of the algorithm">here</a>.

The matrices are implemented in a single dimension array of integers `int[n * n]`.
In order to reach a position on the i-th row and on the j-th column, the array index is calculated by multiplying the rows index by the dimension of the matrix and then adding the column index.

$$ M_{ij} ⇔ M[i \cdot n +j] $$

**Complexity:**

$$ T = \mathcal{\theta}(n^{log_2{7}}) \simeq \mathcal{\theta}(n^{2.81})$$

**Parameters:**

- `A` square matrix $n \times n$ 
- `B` square matrix $n \times n$

**Returns:**

$$C = A \times B$$

---
---

## Searches:

### `int linearSearch(int[] array, int element)`

**Idea:**

Checks element per element the whole array and stops if it founds the wanted element.
Returns -1 if the element is not present in the array.

**Complexity:**  

$$T = \mathcal{\theta}(array.length)$$

**Parameters:**
- `array`: list of elements.
- `element`: element to find.

**Returns**

The index of the wanted element, -1 if not found.

---   

### `int binarSearchRecursive(int[] array, int element)`

Find the index of an element in an array of elements of the same type recursively calling itself. Returns -1 if the element is not present.

**Idea:**

Divides the array in half, checks if the element is the one wanted. If found, returns. If not, recursively checks in the created halves.

**Complexity:**  

$$T = \mathcal{\theta}(\log_2 (array.length))$$

**Parameters:**

- `array`: list of elements.
- `element`: element to find.

**Returns**

The index of the wanted element, -1 if not found.

---

### `int binarySearch(int[] array, int element)`

Find the index of an element in an array of elements of the same type using iterating on the array using two indices and no recursion. If the element is not present, it returns an insertion point to add the element to the array without breaking the order.

**Idea:**

Divides the array in half, checks if the element is the one wanted. If found, returns. If not, updates start and end indices to check in the created halves.

**Complexity:**  

$$T = \mathcal{\theta}(\log_2 (array.length))$$

**Parameters:**
- `array`: list of elements.
- `element`: element to find.

**Returns**
The index of the wanted element, an insertion point if not found.

---
---