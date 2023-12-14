# December 13th, 2023

file = open(f"{__file__}/../input.txt", "r")
grid = [ list(line.strip()) for line in file ]

def cycle(grid):
	for _ in range(4):
		for r, row in enumerate(grid):
			for c, x in enumerate(row):
				if x == 'O':
					i = r
					while i > 0 and grid[i - 1][c] == '.':
						i -= 1
					grid[r][c] = '.'
					grid[i][c] = 'O'
		grid = [ list(row[::-1]) for row in zip(*grid) ]
	return grid

i = 0
memo = dict()
while (k := str(grid)) not in memo:
	memo[k] = i
	i += 1
	grid = cycle(grid)

n = (1000000000 - memo[k]) - ((1000000000 - memo[k]) % (i - memo[k])) + memo[k]
for _ in range(1000000000 - n): grid = cycle(grid)

s = 0
for r, line in enumerate(grid):
	for x in line:
		if x == 'O':
			s += len(grid) - r
print(s)
