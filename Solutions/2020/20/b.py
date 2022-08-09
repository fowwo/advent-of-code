# August 8th, 2022

file = open(f"{__file__}/../input.txt", "r")

tiles = []
for _ in range(144):
	id = int(file.readline().strip()[5:-1])
	tile = []
	for _ in range(10):
		tile.append([ x for x in file.readline().strip() ])
	tiles.append((id, tile))
	file.readline()
file.close()

def rotateClockwise(tile):
	return [ [ tile[len(tile) - j - 1][i] for j in range(len(tile)) ] for i in range(len(tile)) ]
def flipHorizontal(tile):
	return [ list(reversed(tile[i])) for i in range(len(tile)) ]
def fits(tile, x, y):
	if (x - 1, y) in jigsaw:
		a = jigsaw[(x - 1, y)][1]
		for i in range(10):
			if a[i][-1] != tile[i][0]:
				return False
	if (x + 1, y) in jigsaw:
		a = jigsaw[(x + 1, y)][1]
		for i in range(10):
			if a[i][0] != tile[i][-1]:
				return False
	if (x, y - 1) in jigsaw:
		a = jigsaw[(x, y - 1)][1]
		for i in range(10):
			if a[-1][i] != tile[0][i]:
				return False
	if (x, y + 1) in jigsaw:
		a = jigsaw[(x, y + 1)][1]
		for i in range(10):
			if a[0][i] != tile[-1][i]:
				return False
	return True

# Assemble
jigsaw = { (0, 0): tiles.pop() }
while len(tiles):
	spaces = set()
	for tile in jigsaw:
		spaces.add((tile[0] - 1, tile[1]))
		spaces.add((tile[0], tile[1] - 1))
		spaces.add((tile[0] + 1, tile[1]))
		spaces.add((tile[0], tile[1] + 1))
	spaces = [ x for x in spaces if x not in jigsaw ]
	tile = tiles.pop(0)
	
	id = tile[0]
	tile = tile[1]
	found = False
	for _ in range(4):
		for space in spaces:
			if fits(tile, space[0], space[1]):
				jigsaw[space] = (id, tile)
				found = True
				break
		if found: break
		tile = rotateClockwise(tile)
	if not found:
		tile = flipHorizontal(tile)
		for _ in range(4):
			for space in spaces:
				if fits(tile, space[0], space[1]):
					jigsaw[space] = (id, tile)
					found = True
					break
			if found: break
			tile = rotateClockwise(tile)
	if not found:
		tiles.append((id, tile))

# Remove borders
mn = min(jigsaw)
mx = max(jigsaw)
m = []
for i in range(mn[0], mx[0] + 1):
	n = []
	for j in range(mn[1], mx[1] + 1):
		n.append([ x[1:9] for x in jigsaw[(i, j)][1][1:9] ])
	m.append(n)

# Merge
m = [ [ m[j // 8][i // 8][i % 8][j % 8] for j in range(len(m) * 8) ] for i in range(len(m) * 8) ]

# Find correct monster orientation
monster = [ "                  # ",	"#    ##    ##    ###",	" #  #  #  #  #  #   " ]
body = set((i, j) for j in range(len(monster[0])) for i in range(len(monster)) if monster[i][j] == '#')
found = False
for _ in range(4):
	for i in range(len(m) - len(monster) + 1):
		for j in range(len(m[0]) - len(monster[0]) + 1):
			if all(m[i + o[0]][j + o[1]] == '#' for o in body):
				found = True
				break
	if found: break
	m = rotateClockwise(m)
if not found:
	m = flipHorizontal(m)
	for _ in range(4):
		for i in range(len(m) - len(monster) + 1):
			for j in range(len(m[0]) - len(monster[0]) + 1):
				if all(m[i + o[0]][j + o[1]] == '#' for o in body):
					found = True
					break
		if found: break
		m = rotateClockwise(m)

# Find monsters
s = set()
for i in range(len(m) - len(monster)):
	for j in range(len(m[0]) - len(monster[0])):
		if all(m[i + o[0]][j + o[1]] == '#' for o in body):
			for o in body:
				s.add((i + o[0], j + o[1]))

# Count non-monsters
c = 0
for i in range(len(m)):
	for j in range(len(m)):
		if m[i][j] == '#' and (i, j) not in s:
			c += 1
print(c)
