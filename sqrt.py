import sys

sys.setrecursionlimit(10000)

epsilon = 0.001

def sqrt(n, x = 1.0, iter = 0):

    if iter == 1000: return x
    else: iter += 1
     
    t = (n / x) - x
        
    if  (abs(t) < epsilon):
        return x
    else:
        return sqrt(n, (n/x + x)/2, iter)
        
print str(sqrt(9))
print str(sqrt(.009))
print str(sqrt(2))
print str(sqrt(144))
