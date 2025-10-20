# Utility in java:
## Package it.fragri:
### Multiplications:
<ul>
    <li>
        <h4> public int repeatedAdditions(int a, int b) </h4>
        <p>
            Multiplicate two numbers by repeatedly adding \({@literal a}\) to the result, b times.
            $$ T = \O(n) $$
            Parameters:
            a multiplier
            b multiplicand
            return $$ a*b $$
        </p>
    </li>
    <li>
        <h4>public int russianPeasant(int a, int b)</h4>
        <p>
            Multiplicate two numbers following this rule until \({@literal b > 0}\):
            T = $\theta$(log_2(b))
            Theory: \({@literal a*b = 2a * \frac{1}{2}*b}\)
            With integers, the rule becomes as follows:
            \({@literal a*b =  \begin{cases} 2a * \frac{b}{2} \text{  if b is even}, \\ 2a * \frac{b-1}{2} + a \text{  if b is odd }\end{cases}} \)
            Basically it keeps doubling a and dividing by 2 b, only adding the current value of a to the result when b is odd.
            @param a multiplier
            @param b multiplicand
            @return product of a and b. \({@literal a*b}\)
        </p>
    </li>

### Searches:
### Sorting:
</ul>

# Utility in Java

## Package `it.fragri`

### Multiplications

- #### `public int repeatedAdditions(int a, int b)`

  Moltiplica due numeri sommando ripetutamente `a` al risultato, `b` volte.  

  **Complessità:**  
  \( T = \mathcal{O}(n) \)

  **Parametri:**  
  - `a`: moltiplicatore  
  - `b`: moltiplicando  

  **Ritorna:**  
  \( a \times b \)

---

- #### `public int russianPeasant(int a, int b)`

  Moltiplica due numeri seguendo questa regola fino a quando \( b > 0 \):  

  **Complessità:**  
  \( T = \Theta(\log_2 b) \)

  **Teoria:**  
  \( a \times b = 2a \times \frac{1}{2}b \)

  Con numeri interi, la regola diventa:

  \[
  a \times b =
  \begin{cases}
  2a \times \frac{b}{2} & \text{se } b \text{ è pari}, \\
  2a \times \frac{b - 1}{2} + a & \text{se } b \text{ è dispari}
  \end{cases}
  \]

  In pratica, raddoppia `a` e dimezza `b`, aggiungendo il valore corrente di `a` al risultato ogni volta che `b` è dispari.

  **Parametri:**  
  - `a`: moltiplicatore  
  - `b`: moltiplicando  

  **Ritorna:**  
  \( a \times b \)

---

### Searches

### Sorting
