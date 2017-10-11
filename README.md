# Paw's Path Assignment

In fact, a given task is a variation of the Knight's Tour problem (https://en.wikipedia.org/wiki/Knight%27s_tour) and it is an instance of the more general Hamiltonian path problem in graph theory. The problem of finding a closed knight's tour is similarly an instance of the Hamiltonian cycle problem.
https://en.wikipedia.org/wiki/Hamiltonian_path_problem

In the mathematical field of graph theory, the Hamiltonian path problem and the Hamiltonian cycle problem are problems of determining whether a Hamiltonian path (a path in an undirected or directed graph that visits each vertex exactly once) or a Hamiltonian cycle exists in a given graph (whether directed or undirected). Both problems are NP-complete.[1]

So, let's apply the following algorithm from Knight's Tour problem:

### Backtracking
Backtracking works in an incremental way to attack problems. Typically, we start from an empty solution vector and one by one add items (Meaning of item varies from problem to problem. In the context of current task problem, an item is a paw's move). When we add an item, we check if adding the current item violates the problem constraint, if it does then we remove the item and try other alternatives. If none of the alternatives work out then we go to the previous stage and remove the item added in the previous stage. If we reach the initial stage back then we say that no solution exists. If adding an item doesn’t violate constraints then we recursively add items one by one. If the solution vector becomes complete then we print the solution.

There is a pseudocode for backtracking solution:
```
If all squares are visited 
    return the solution
Else
   a) Add one of the next moves to solution vector and recursively 
   check if this move leads to a solution. (A Paw can make maximum eight moves. We choose one of the 8 moves in this step using rotation of the moves if necessary).
   b) If the move chosen in the above step doesn't lead to a solution
   then remove this move from the solution vector and try other 
   alternative moves.
   c) If none of the alternatives work then remove the previously added item in recursion and if false is 
   returned by the initial call of recursion then "no solution exists" 
   ```


### Warnsdorf's rule
Warnsdorf's rule is a heuristic for finding a knight's tour. The knight is moved so that it always proceeds to the square from which the knight will have the fewest onward moves. When calculating the number of onward moves for each candidate square, we do not count moves that revisit any square already visited. It is, of course, possible to have two or more choices for which the number of onward moves is equal; there are various methods for breaking such ties.
This rule may also more generally be applied to any graph. In graph-theoretic terms, each move is made to the adjacent vertex with the least degree. Although the Hamiltonian path problem is NP-hard in general, on many graphs that occur in practice this heuristic is able to successfully locate a solution in linear time.

## Usage

This solution is using SBT for package management (scalatest).

To run the program from terminal use the following arguments:
[B|W] [dimensions]
where B - Backtracking or W - Warnsdorff and dimensions is a dimension of the board (ex 10)
Examples:
```
sbt "run W 10"
```
The result should look like:
```
 iterations=99 path.size=100
   1  58  31   2  59  72  23  44  67  22
  92  18  15  93  19  14  61  20  13  46
  32   3  96  71  30  95  68  73  24  43
  16  57  89  17  60  88  28  45  66  21
  91  80  33  94  81  76  62  82  12  47
  37   4  97  70  29  98  69  74  25  42
  34  56  90  77  53  87  27  50  65   8
 100  79  36  99  84  75  63  83  11  48
  38   5  52  39   6  51  40   7  26  41
  35  55  85  78  54  86  10  49  64   9
```
or
```
sbt "run B 10"
```
and result should be:
```
 iterations=121041 path.size=100
   1  43  22   2  44  23   3  45  24   4
  32  59  13  33  60  14  34  61  15  35
  21  79  72  92  85  73  54  84  74  46
  12  42  95  65  88  94  66  89  25   5
  31  58  86  80  53  91  75  62  16  36
  20  78  71  93  96  64  55  83  67  47
  11  41  52  99  87  81 100  90  26   6
  30  57  19  77  56  18  76  63  17  37
  51  98  70  50  97  69  49  82  68  48
  10  40  29   9  39  28   8  38  27   7
```

to run the tests use 
```
sbt test
```
a result should look like:
```
[info] pawntourBaseSpec:
[info] A Tile
[info] BacktrackingSpec:
[info] - should be on board if coords are lower than dimensions
[info] movesInDirection
[info] - should be not on board if coords are greater than board dimensions
[info] - should return moves starting from the given direction
[info] - should be visited if exists in path earlier
[info] Run completed in 466 milliseconds.
[info] Total number of tests run: 4
[info] Suites: completed 2, aborted 0
[info] Tests: succeeded 4, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
```

