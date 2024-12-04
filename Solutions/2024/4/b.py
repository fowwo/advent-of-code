# December 3rd, 2024

file = open(f"{__file__}/../input.txt", "r")
grid = [ line.strip() for line in file ]
file.close()

n = 0
for r in range(len(grid) - 2):
	for c in range(len(grid[0]) - 2):
		s = grid[r][c] + grid[r][c + 2] + grid[r + 1][c + 1] + grid[r + 2][c] + grid[r + 2][c + 2]
		if s in [ "MSAMS", "MMASS", "SMASM", "SSAMM" ]: n += 1
print(n)
