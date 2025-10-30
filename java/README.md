# Testing Algorithms in Java

## Package `it.modules`

### MathFunctions

#### `int repeatedAdditions(int a, int b)`

Multiplicate two numbers by repeatedly adding `a` to the result, `b` times.

**Complexity:**  

$$T = \mathcal{O}(b)$$

**Parameters:**  

- `a`: multiplier.
- `b`: multiplicand.

**Return:**  

$$a \cdot b$$

---

#### `int russianPeasant(int a, int b)`
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
---

### Searches:

#### `int linearSearch(int[] array, int element)`

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

#### `int binarSearchRecursive(int[] array, int element)`

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

#### `int binarySearch(int[] array, int element)`

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

### Sorting:

#### `void selectionSort(int[] array)`

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
- `array`: list of elements.

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
- `array`: list of elements.

**Returns:**
Nothing.

---

#### `int[] matMultiply(int[] A , int[] B, int dim)`

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

#### `int[] strassenMatMultiply(int[] A, int[] B, int dim)`

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
