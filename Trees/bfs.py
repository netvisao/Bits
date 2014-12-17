from collections import deque as queue

class Node(object):
  def __init__(self, val):
    self.left = None
    self.right = None
    self.val = val
    
  def __repr__(self):
    return str(self.val)


def process(node):
  print "Processing", node


def bfs(queue):
  while len(queue):
    node = queue.popleft()
    process(node)
    
    if node.left:
      queue.append(node.left)
    if node.right:
      queue.append(node.right)

def main():

  root = Node(0)
  n1 = Node(1)
  n2 = Node(2)
  n3 = Node(3)
  n4 = Node(4)
  n5 = Node(5)
  n6 = Node(6)
  n7 = Node(7)
  n8 = Node(8)
  
  root.left = n1
  root.right = n5
  
  n1.left = n2
  n1.right = n3
  n3.left = n4
  
  n5.left = n6
  n5.right = n7
  n7.left = n8


  q = queue()
  q.append(root)
  bfs(q)
  
if __name__ == '__main__':
    main()