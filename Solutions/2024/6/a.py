# December 5th, 2024

file = open(f"{__file__}/../input.txt", "r")
grid = [ line.strip() for line in file ]
file.close()

obs = set()
start = None
for r, row in enumerate(grid):
	for c, x in enumerate(row):
		if x == '#':
			obs.add((r, c))
		elif x == '^':
			start = (r, c)

r, c = start
v = set()
d = [ (-1, 0), (0, 1), (1, 0), (0, -1) ]
i = 0
while 0 <= r < len(grid) and 0 <= c < len(grid[0]):
	v.add((r, c))
	dr, dc = d[i]
	while (r + dr, c + dc) in obs:
		i = (i + 1) % 4
		dr, dc = d[i]
	r += dr
	c += dc
print(len(v))
