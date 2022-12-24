# December 23rd, 2022

file = open(f"{__file__}/../input.txt", "r")

tile = set()
up = set()
down = set()
left = set()
right = set()

for i, line in enumerate(file):
	for j, c in enumerate(line.strip()):
		if c != "#":
			tile.add((i, j))
			if c == "^": up.add((i, j))
			elif c == "v": down.add((i, j))
			elif c == "<": left.add((i, j))
			elif c == ">": right.add((i, j))
maxr = i
maxc = j

m = set([ (0, 1, 0) ])
i = 0
while (maxr, maxc - 1, 3) not in m:
	i += 1
	up = set((r - 1, c) if r - 1 != 0 else (maxr - 1, c) for r, c in up)
	down = set((r + 1, c) if r + 1 != maxr else (1, c) for r, c in down)
	left = set((r, c - 1) if c - 1 != 0 else (r, maxc - 1) for r, c in left)
	right = set((r, c + 1) if c + 1 != maxc else (r, 1) for r, c in right)

	new = set()
	for x in m:
		r, c, score = x
		for d in [ (r, c), (r - 1, c), (r + 1, c), (r, c - 1), (r, c + 1) ]:
			if d in tile and d not in up and d not in down and d not in left and d not in right:
				if (score in [ 0, 2 ] and d == (maxr, maxc - 1)) or (score == 1 and d == (0, 1)): 
					new.add((d[0], d[1], score + 1))
				else:
					new.add((d[0], d[1], score))
	m = new
print(i)
