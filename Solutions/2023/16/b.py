# December 15th, 2023

file = open(f"{__file__}/../input.txt", "r")
grid = [ line.strip() for line in file ]

def energize(p):
	beam = set()
	memo = set()
	m = [ p ]
	while m:
		n = []
		for r, c, d in m:
			if (r, c, d) in memo or r < 0 or r >= len(grid) or c < 0 or c >= len(grid[0]): continue
			beam.add((r, c))
			memo.add((r, c, d))
			x = grid[r][c]
			if x == '\\':
				if d % 2 == 0:
					d = (d - 1) % 4
				else:
					d = (d + 1) % 4
			elif x == '/':
				if d % 2 == 0:
					d = (d + 1) % 4
				else:
					d = (d - 1) % 4
			elif x == '-':
				if d % 2 == 0:
					n.append((r, c - 1, 3))
					n.append((r, c + 1, 1))
					continue
			elif x == '|':
				if d % 2 == 1:
					n.append((r - 1, c, 0))
					n.append((r + 1, c, 2))
					continue
			r, c = [ (r - 1, c), (r, c + 1), (r + 1, c), (r, c - 1) ][d]
			n.append((r, c, d))
		m = n
	return len(beam)

v = 0
for q in range(len(grid)):
	v = max(v, energize((0, q, 2)), energize((len(grid) - 1, q, 0)))
for q in range(len(grid[0])):
	v = max(v, energize((q, 0, 1)), energize((q, len(grid[0]) - 1, 3)))
print(v)
