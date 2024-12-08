# December 7th, 2024

from collections import defaultdict

file = open(f"{__file__}/../input.txt", "r")
grid = [ line.strip() for line in file ]
file.close()

antennas = defaultdict(list)
for r, row in enumerate(grid):
	for c, x in enumerate(row):
		if x != '.':
			antennas[x].append((r, c))
		
antinodes = set()
for x, m in antennas.items():
	for i in range(len(m) - 1):
		for j in range(i + 1, len(m)):
			(ax, ay), (bx, by) = m[i], m[j]
			(dx, dy) = ax - bx, ay - by
			while 0 <= ax < len(grid) and 0 <= ay < len(grid[0]):
				antinodes.add((ax, ay))
				ax += dx
				ay += dy
			while 0 <= bx < len(grid) and 0 <= by < len(grid[0]):
				antinodes.add((bx, by))
				bx -= dx
				by -= dy
print(len(antinodes))
