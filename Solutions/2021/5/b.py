# November 13th, 2022

file = open(f"{__file__}/../input.txt", "r")

vents = [ sorted([ int(x) for x in point.split(",") ] for point in vent.strip().split(" -> ")) for vent in file.readlines() ]

# Find overlaps
grid = [ [ 0 ] * 1000 for _ in range(1000) ]
for vent in vents:
	r = vent[0][0]
	c = vent[0][1]
	i = 1 if vent[1][0] > vent[0][0] else -1 if vent[1][0] < vent[0][0] else 0
	j = 1 if vent[1][1] > vent[0][1] else -1 if vent[1][1] < vent[0][1] else 0
	grid[r][c] += 1
	while r != vent[1][0] or c != vent[1][1]:
		r += i
		c += j
		grid[r][c] += 1

# Count overlaps
count = 0
for r in range(len(grid)):
	for c in range(len(grid)):
		if grid[r][c] > 1:
			count += 1
print(count)
