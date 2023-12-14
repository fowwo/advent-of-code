# December 13th, 2023

file = open(f"{__file__}/../input.txt", "r")
grid = [ line.strip() for line in file ]

s = 0
for r, row in enumerate(grid):
	for c, x in enumerate(row):
		if x == 'O':
			i, o = r, 0
			while i > 0 and (v := grid[i - 1][c]) != '#':
				i -= 1
				if v == 'O':
					o += 1
			s += len(grid) - i - o
print(s)
