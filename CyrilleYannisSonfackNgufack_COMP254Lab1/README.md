# COMP254 Lab 1 - Fundamental Data Structures

**Author:** Cyrille Yannis Sonfack Ngufack

This lab contains three exercises implementing operations on linked list data structures.

---

## Exercise 1: DoublyLinkedList Concatenation (3 marks)

**Location:** `src/exercise1/`

**Task:** Write a method to concatenate two doubly linked lists L and M into a single list L'.

### How it works:

The `concatenate(DoublyLinkedList M)` method joins list M to the end of list L:

1. Get the last node of L (node before trailer sentinel)
2. Get the first node of M (node after header sentinel)
3. Connect last node of L to first node of M
4. Connect last node of M to L's trailer sentinel
5. Update the size of L
6. Reset M to empty

```
Before: L = (A, B, C)  M = (D, E, F)
After:  L' = (A, B, C, D, E, F)  M = ()
```

**Run:** Execute `Exercise1Main.java`

---

## Exercise 2: SinglyLinkedList swapNodes (4 marks)

**Location:** `src/exercise2/`

**Task:** Write a method to swap two nodes (not just their contents) given references to the nodes.

### How it works:

The `swapNodes(Node node1, Node node2)` method:

1. Check if nodes are the same (no swap needed)
2. Check if nodes are null
3. Traverse the list to find predecessors of both nodes
4. Handle three cases:
   - Nodes are adjacent (node1 before node2)
   - Nodes are adjacent (node2 before node1)
   - Nodes are not adjacent
5. Update the next pointers to swap the nodes
6. Update tail if needed

```
Before: (A, B, C, D, E) - swap B and D
After:  (A, D, C, B, E)
```

**Run:** Execute `Exercise2Main.java`

---

## Exercise 3: CircularlyLinkedList clone (3 marks)

**Location:** `src/exercise3/`

**Task:** Write a clone() method for CircularlyLinkedList.

### How it works:

The `clone()` method creates a shallow copy of the list:

1. Call super.clone() to get a base copy
2. If list is empty, return the copy
3. Traverse the original list and create new nodes with same elements
4. Link each new node to the next
5. Connect the last node back to the first node (circular link)
6. Set the tail to the last node

```
Original: (A, B, C, D) -> points back to A
Clone:    (A, B, C, D) -> points back to A (separate nodes)
```

**Run:** Execute `Exercise3Main.java`

---

## How to Run

1. Open the project in Eclipse or your Java IDE
2. Navigate to the exercise folder you want to test
3. Run the corresponding Main class:
   - `exercise1/Exercise1Main.java`
   - `exercise2/Exercise2Main.java`
   - `exercise3/Exercise3Main.java`
