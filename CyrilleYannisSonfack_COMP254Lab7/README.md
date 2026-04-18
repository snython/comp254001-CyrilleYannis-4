# COMP254 Lab 7 — Search Trees and Sorting
**Student:** Cyrille Yannis Sonfack

---

## What This Project Contains

This project has two exercises, each in its own Java package inside the `src/` folder:

```
src/
  exercise1/
    BinarySearchTree.java   <- the BST with iterative search
    Exercise1.java          <- test / demo program
  exercise2/
    BottomUpMergeSort.java  <- the sorting algorithm
    Exercise2.java          <- test / demo program
  common/                   <- original course helper classes (unchanged)
```

---

## Exercise 1 — Iterative Tree Search

### The Problem
The textbook's `treeSearch` method (Code Fragment 11.3) finds a key in a Binary Search Tree using **recursion** — it calls itself over and over, going left or right at each node. On a very large, lopsided (unbalanced) tree this can cause a **StackOverflowError** because Java only has a limited amount of space for recursive calls.

### The Solution
Replace the recursion with a **`while` loop**.

The idea is simple: instead of calling `treeSearch` again from inside itself, we just move a pointer (`current`) down the tree step by step until we either find the key or fall off the bottom.

**Original recursive version (Code Fragment 11.3):**
```
treeSearch(p, key):
    if p is a leaf  →  return p           (not found)
    if key == p.key →  return p           (found!)
    if key <  p.key →  return treeSearch(LEFT child,  key)
    if key >  p.key →  return treeSearch(RIGHT child, key)
```

**New iterative version (our implementation):**
```
treeSearch(key):
    current = root
    while current is not null:
        if   key == current.key  →  return current   (found!)
        elif key <  current.key  →  current = left child
        else                     →  current = right child
    return null                                       (not found)
```

Both do exactly the same work; the loop version just does not grow the call stack.

### How to Run
```
javac exercise1/BinarySearchTree.java exercise1/Exercise1.java
java exercise1.Exercise1
```

### Expected Output
```
=== Exercise 1: Iterative treeSearch ===

Searching for existing keys:
  treeSearch(6) -> (6, six)
  treeSearch(2) -> (2, two)
  ...

Searching for missing keys:
  treeSearch(5) -> null (not found)
  ...
```

---

## Exercise 2 — Bottom-Up Merge Sort

### The Problem
Sort a collection of items from scratch using the **merge-sort** strategy, but built from the bottom up using queues instead of splitting an array recursively.

### The Algorithm (Step by Step)

Imagine you have 7 playing cards face-down on a table: `38 27 43 3 9 82 10`.

**Step 1 — One card per pile**
Put each card in its own little pile (queue):
```
[38]  [27]  [43]  [3]  [9]  [82]  [10]
```

**Step 2 — Collect all piles into a "big queue of queues"**
You now have a queue that holds 7 queues.

**Step 3 — Merge pairs**
Take the first two piles, merge them into one sorted pile. Take the next two, merge them. Keep going:
```
Round 1:  [27,38]  [3,43]  [9,82]  [10]    (7 → 4 piles)
Round 2:  [3,27,38,43]  [9,10,82]           (4 → 2 piles)
Round 3:  [3,9,10,27,38,43,82]              (2 → 1 pile)
```

**Step 4 — Done!**
When only one pile is left it is fully sorted.

### The Merge Step
Merging two sorted queues is easy: repeatedly peek at the front of each queue, take whichever is smaller, and add it to a new queue. When one queue is empty, pour the rest of the other one in.

### How to Run
```
javac exercise2/BottomUpMergeSort.java exercise2/Exercise2.java
java exercise2.Exercise2
```

### Expected Output
```
=== Exercise 2: Bottom-Up Merge Sort ===

Original : [38, 27, 43, 3, 9, 82, 10]
Sorted   : [3, 9, 10, 27, 38, 43, 82]

Original : [banana, apple, mango, cherry, date]
Sorted   : [apple, banana, cherry, date, mango]
...
```

---

## Key Concepts Glossary (for Beginners)

| Term | Plain-English Meaning |
|------|----------------------|
| **Binary Search Tree (BST)** | A tree where every left child is smaller than its parent, and every right child is larger. Makes searching fast. |
| **Recursion** | A method that calls itself. Works like Russian nesting dolls — each call opens one more doll. |
| **Iteration** | Using a loop (`while`/`for`) instead of calling yourself again. |
| **StackOverflowError** | Java crashes when recursion goes too deep — too many dolls to open. |
| **Queue** | A line of items — first in, first out (like a checkout line). |
| **Merge Sort** | Sort by splitting into smaller pieces, sorting each, then combining. |
| **Bottom-up** | Start from the smallest pieces (single items) and build up, instead of breaking down from the top. |
