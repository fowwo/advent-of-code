# December 3rd, 2024

file = open(f"{__file__}/../input.txt", "r")
grid = [ line.strip() for line in file ]
file.close()

n = 0
for r in range(len(grid)):
	for c in range(len(grid[0])):
		if r + 3 < len(grid):
			s = grid[r][c] + grid[r + 1][c] + grid[r + 2][c] + grid[r + 3][c]
			if s == "XMAS" or s == "SAMX": n += 1
		if c + 3 < len(grid[0]):
			s = grid[r][c] + grid[r][c + 1] + grid[r][c + 2] + grid[r][c + 3]
			if s == "XMAS" or s == "SAMX": n += 1
		if r + 3 < len(grid) and c + 3 < len(grid[0]):
			s = grid[r][c] + grid[r + 1][c + 1] + grid[r + 2][c + 2] + grid[r + 3][c + 3]
			if s == "XMAS" or s == "SAMX": n += 1
			s = grid[r + 3][c] + grid[r + 2][c + 1] + grid[r + 1][c + 2] + grid[r][c + 3]
			if s == "XMAS" or s == "SAMX": n += 1
print(n)
