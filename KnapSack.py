
def ks(v, w, W, i):
  if (i == 0): 
    return 0;
  if (W == 0):
    return 0;

  print("v %d w %d W %d i %d" % (v[i-1],w[i-1],W,i))


  if (w[i-1] > W): 
    return ks(v, w, W, i - 1)

  return max(v[i-1] + ks(v,w,W-w[i-1], i-1), ks(v,w,W, i-1))


mem = {}
def ksmem(v, w, W, i):

  if (i == 0): 
    return 0;
  if (W == 0):
    return 0;



  global mem;
  currVal, currWeight = (v[i-1], w[i-1])

  if (currVal in mem and currWeight in mem[currVal]):
    return mem[currVal][currWeight]

  print("v %d w %d W %d i %d" % (v[i-1],w[i-1],W,i))


  if (w[i-1] > W): 
    res = ksmem(v, w, W, i - 1)
  else:
    res = max(v[i-1] + ksmem(v,w,W-w[i-1], i-1), ksmem(v,w,W, i-1))
  
  if currVal not in mem: mem[currVal] = {} 
  mem[currVal][currWeight] = res
  return res;




print "\nks"
ks([10,2,3],[1, 4, 5], 15, 3)
print "\nksmem"
ksmem([10,2,3],[1, 4, 5], 15, 3)

