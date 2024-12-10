# December 9th, 2024

file = open(f"{__file__}/../input.txt", "r")
grid = [ [ int(x) for x in line.strip() ] for line in file ]
file.close()

s = 0
for i, row in enumerate(grid):
	for j, x in enumerate(row):
		if x == 0:
			m = [ (i, j) ]
			for k in range(1, 10):
				n = []
				for (r, c) in m:
					for (x, y) in [ (r + 1, c), (r, c + 1), (r - 1, c), (r, c - 1) ]:
						if 0 <= x < len(grid) and 0 <= y < len(grid[0]) and grid[x][y] == k:
							n.append((x, y))
				m = n
			s += len(m)
print(s)
