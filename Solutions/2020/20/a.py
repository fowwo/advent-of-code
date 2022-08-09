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
	return [ [ tile[9 - j][i] for j in range(10) ] for i in range(10) ]
def flipHorizontal(tile):
	return [ list(reversed(tile[i])) for i in range(10) ]
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

# Multiply
mn = min(jigsaw)
mx = max(jigsaw)
print(jigsaw[mn][0] * jigsaw[mx][0] * jigsaw[(mn[0], mx[1])][0] * jigsaw[(mx[0], mn[1])][0])
