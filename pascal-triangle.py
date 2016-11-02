def pascal(row, col):
  pPyramid = []
  pPyramid.append([1])

  cur_row = []
  for i in range(1, row+1):
    for j in range(0, i+1):
      if i == j or j == 0:
        cur_row.append(1)
      else:
        cur_row.append(pPyramid[i-1][j] + pPyramid[i-1][j-1])
    pPyramid.append(cur_row)
    cur_row = []

  print pPyramid
  return pPyramid[row][col]

print pascal(5,3)
