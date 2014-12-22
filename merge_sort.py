def merge_sort(ll):
  
  print "merge_sort on %s" % ll
  
  lgt = len(ll)

  if lgt < 2:
    return ll
    
  return merge(merge_sort(ll[:lgt / 2]), merge_sort(ll[lgt / 2:]))
  
  
def merge(ll1, ll2):
    
    print "merge on %s and %s" % (ll1, ll2)
    
    tmp = []
    
    i1, i2 = 0, 0
    

    for i in range(len(ll1) + len(ll2)):
             
             if (i1 >= len(ll1)):
                 tmp.append(ll2[i2])
                 i2 += 1
                 continue
                 
             if (i2 >= len(ll2)):
                tmp.append(ll1[i1])
                i1 += 1
                continue
                 
             if ll1[i1] < ll2[i2]:
                 tmp.append(ll1[i1])
                 i1 += 1
                 continue
                          
             else :
                 tmp.append(ll2[i2])
                 i2 += 1
                 continue
       
             print "Buffer %s" % tmp
    return tmp


if __name__ == '__main__':
  print merge_sort([1,18,3,2,0,99,3])