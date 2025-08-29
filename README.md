# Multi Type Binary Tree

A simple Java project that demonstrates how to:

- Work with mulitple types with **binary UTF-8 representation** in a binary tree
- Compress the binary data into an array of bit counts  

---

## Features

- `Tree.add(String text)` → inserts a string 
- `Tree.add(int number)` → inserts a number
- Binary conversion: `"hello"` → `01101000 01100101 01101100 01101100 01101111`  
- Compression: counts how many `1`s occur in each of the 8 bit positions  
- `Node` stores:
  - `compressedData` (int[8])  
  - `bits` (binary strings)  
  - `nodeText` (original input)  

---

## Example output

```java
[Node] -> Dinner]
    ├── [Node] -> Hei]
    │   ├── [Node] -> 5]
    │   │   ├── [Node] -> 3]
    │   │   └── [Node] -> 432432766]
    │   │       ├── [Node] -> 32]
    │   │       └── [Node] -> hi]
    │   └── [Node] -> wow]
    └── [Node] -> Massive thing]
        ├── [Node] -> Large step]
        └── [Node] -> Mini step for me]
