import numpy as np

a = [  
      [-3, -2, 1], 
      [-1, 0, 3], 
      [4, 7, 8] 
    ] 

a = np.array(a)
# print(M.shape[0])

n = a.shape[0]
m = a.shape[1]

dem = 0

i = 0 
j = m - 1 

while j >= 0 and i < n: 
    if a[i][j] < 0: 
        dem = dem + j + 1  
        i = i + 1
    else: 
        j = j - 1

print("so phan tu am cua ma tran la", dem) 