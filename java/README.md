# Utility in Java

## Package `it.fragri`

### Multiplications

#### `public int repeatedAdditions(int a, int b)`

Multiplicate two numbers by repeatedly adding `a` to the result, `b` times.

**Complexity:**  
$$T = \mathcal{O}(n)$$

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
$$T = \mathcal{O}(n)$$

**Parameters:**  
- `a`: multiplier  
- `b`: multiplicand

**Returns:**  
$$a \times b$$

---
---

### Searches:

---
---

### Sorting:
