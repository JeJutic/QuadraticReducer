# Quadratic Reducer

Implementation of Lagrange method for reducing a quadratic form.\
The case where variable change is needed because no squares left isn't covered yet.

## Requirements

* Java [JDK 17+]
* Maven [3.0+]

## Setup

Building artifact:
```
 mvn clean compile assembly:single
```

Running:
```
 java -jar QuadraticReducer-1.0.jar -input [variable count] ["polynomial"]
```
\
\
For example:

```
 java -jar QuadraticReducer-1.0.jar -input 3 "5x1d2+16x1x2+-14x1x3+13x2d2+-22x2x3+10x3d2" 
```

Output:

```
 1 0 0 
 0 1 0
 0 0 0
 1 2.236x1+3.578x2+-3.130x3
 2 0.447x2+0.447x3
 3 x3
```
which represents quadratic form in canonical basis and how this basis is evaluated from implicitly given. 

## Input and output of polynomials

Term is represented as `[coefficient]x[variable id]d[variable power]x[]d[]x[]d[]...`.\
`[coefficient]` and `d[variable power]` can be omitted if they are equal to 1.\
At the moment only integer coefficients can be parsed whereas output coefficients are always floating point.

Polynomial is represented as list of terms concatenated with `+`. 
Note that if not a first coefficient starts with `-` it will look like `+-`.
