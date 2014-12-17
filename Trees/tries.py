import sys 
from time import time

class Node:
  def __init__(self, str):
    self.children = {}
    self.str = str

  def __repr__(self):
    return self.str

words = [] 
counts = {}
counts_map = {}


def insertWordIntoTriesLinear(w, root = Node('')):
  counts_map[w] = 1 if w not in counts_map else counts_map[w] + 1



def insertWordIntoTries(w, root = Node('')):
 
  if not len(w):
    if root in counts:
      counts[root] += 1
    else:
      counts[root] = 1
    return
                          
  head, tail = w[0], w[1:]
 

  if head not in root.children:
    root.children[head] = Node(str(root) + head)

  insertWordIntoTries(w[1:], root.children[head])


if __name__ == '__main__':
  with open(sys.argv[1], "r") as f:
    for l in f:
        words.append(l.strip())      

    for w in words: insertWordIntoTries(w)
    for w in words: insertWordIntoTriesLinear(w)

    t0 = time() 
    for w in words: insertWordIntoTries(w)
    t1 = time() 
    for w in words: insertWordIntoTriesLinear(w)
    t2 = time()
    print len(counts), len(counts_map), len(words), "t1", (t1 - t0), "t2", (t2 - t1)

#insertWordIntoTries('amine')
#insertWordIntoTries('rounak')
#insertWordIntoTries('am')
#insertWordIntoTries('am')

