# Utility in Java

## Package `it.fragri`

### Multiplications

#### `public int repeatedAdditions(int a, int b)`

Multiplicate two numbers by repeatedly adding `a` to the result, `b` times.

**Complexity:**  
$$T = \mathcal{O}(b)$$

**Parameters:**  
- `a`: multiplier.
- `b`: multiplicand.

**Return:**  
$$a \times b$$

---

#### `public int russianPeasant(int a, int b)`
Multiplicate two numbers following this rule until `b > 0`:

**Idea:**  
  $$a \times b = 2a \times \frac{b}{2}$$
  
With integers, the rule becomes as follows:

$$2a \times \frac{b}{2} \ \ if\ b\ is\ even.$$

$$2a \times \frac{b-1}{2} + a \ \ \ if\ b\ is\ odd. $$

Basically it keeps doubling a and dividing by 2 b, only adding the current value of a to the result when b is odd.

**Complexity:**  
$$T = \mathcal{O}(b)$$

**Parameters:**  
- `a`: multiplier.
- `b`: multiplicand.

**Returns:**  
$$a \times b$$

---
---

### Searches:

#### `public int linearSearch(int[] array, int element)`

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

#### `public int binarSearchRecursive(int[] array, int element)`

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

#### `public int binarySearch(int[] array, int element)`

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

#### `public void selectionSort(int[] array)`

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

#### `public void insertionSort(int[] array)`

Sorts the given array.

**Idea:**
Until the array has been fully traversed with index i, takes the i-th element and inserts it in the position in the positions less than i, which represent the ordered side of the array. 

**Complexity:**  
$$T_best = \mathcal{\theta}(array.length-1)$$
$$T_worst = \mathcal{\theta}(array.length^2)$$

**Parameters:**
- `array`: list of elements.

**Returns:**
Nothing.

---

#### `public void bubbleSort(int[] array)`

Sorts the given array.

**Idea:**
Until the array has been sorted, it swaps neighbours if one is less than the other.
Greater elements end up in the end of the array, like bubbles float up to the surface. Thanks to this observation, it does not search always until the end of the array, but until the length of the array minus the number of already checked positions.

**Complexity:**  
$$T_best = \mathcal{\theta}(array.length-1)$$
$$T_worst = \mathcal{\theta}(array.length^2)$$

**Parameters:**
- `array`: list of elements.

**Returns:**
Nothing.

---

