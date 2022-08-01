# August 1st, 2022

file = open("input.txt", 'r')
regex = file.readline().strip()[1:-1]
file.close()

rooms = dict()
class Node:
	def __init__(this, x, y):
		this.x = x
		this.y = y
		this.north = None
		this.south = None
		this.east = None
		this.west = None
	def travelNorth(this):
		x = this.x
		y = this.y + 1
		if this.north: return this.north
		if (x, y) in rooms:
			this.north = rooms[(x, y)]
			rooms[(x, y)].south = this
			return rooms[(x, y)]
		n = Node(x, y)
		this.north = n
		n.south = this
		rooms[(x, y)] = n
		return n
	def travelSouth(this):
		x = this.x
		y = this.y - 1
		if this.south: return this.south
		if (x, y) in rooms:
			this.south = rooms[(x, y)]
			rooms[(x, y)].north = this
			return rooms[(x, y)]
		n = Node(x, y)
		this.south = n
		n.north = this
		rooms[(x, y)] = n
		return n
	def travelEast(this):
		x = this.x + 1
		y = this.y
		if this.east: return this.east
		if (x, y) in rooms:
			this.east = rooms[(x, y)]
			rooms[(x, y)].west = this
			return rooms[(x, y)]
		n = Node(x, y)
		this.east = n
		n.west = this
		rooms[(x, y)] = n
		return n
	def travelWest(this):
		x = this.x - 1
		y = this.y
		if this.west: return this.west
		if (x, y) in rooms:
			this.west = rooms[(x, y)]
			rooms[(x, y)].east = this
			return rooms[(x, y)]
		n = Node(x, y)
		this.west = n
		n.east = this
		rooms[(x, y)] = n
		return n

root = Node(0, 0)
rooms[(0, 0)] = root
branches = set()
branches.add((0, root))

c = 0
while len(branches):
	new = set()
	for n in branches:
		c += 1
		i = n[0]
		n = n[1]
		while i < len(regex) and regex[i] in "NEWS":
			if regex[i] == 'N': n = n.travelNorth()
			elif regex[i] == 'E': n = n.travelEast()
			elif regex[i] == 'W': n = n.travelWest()
			else: n = n.travelSouth()
			i += 1
		if i == len(regex): continue
		if regex[i] == '(':
			i += 1
			new.add((i, n))
			depth = 0
			while regex[i] != ')' or depth != 0:
				if regex[i] == '(': depth += 1
				elif regex[i] == ')': depth -= 1
				elif regex[i] == '|' and depth == 0:
					new.add((i + 1, n))
				i += 1
		elif regex[i] == '|' or regex[i] == ')':
			depth = 0
			while regex[i] != ')' or depth != 0:
				if regex[i] == '(': depth += 1
				elif regex[i] == ')': depth -= 1
				i += 1
			new.add((i + 1, n))
	branches = new

# Find furthest room
nodes = [ root ]
memo = set()
memo.add((0, 0))
distance = -1
while len(nodes):
	new = []
	for n in nodes:
		memo.add((n.x, n.y))
		if n.north and (n.north.x, n.north.y) not in memo:
			new.append(n.north)
			memo.add((n.north.x, n.north.y))
		if n.south and (n.south.x, n.south.y) not in memo:
			new.append(n.south)
			memo.add((n.south.x, n.south.y))
		if n.east and (n.east.x, n.east.y) not in memo:
			new.append(n.east)
			memo.add((n.east.x, n.east.y))
		if n.west and (n.west.x, n.west.y) not in memo:
			new.append(n.west)
			memo.add((n.west.x, n.west.y))
	nodes = new
	distance += 1
print(distance)

# Print
# s = rooms.keys()
# minx = min(s, key=lambda x:x[0])[0] * 2 - 1
# miny = min(s, key=lambda x:x[1])[1] * 2 - 1
# maxx = max(s, key=lambda x:x[0])[0] * 2 + 1
# maxy = max(s, key=lambda x:x[1])[1] * 2 + 1
# y = maxy
# while y >= miny:
# 	for x in range(minx, maxx + 1):
# 		if x % 2 == 1 and y % 2 == 1: print('#', end='')
# 		elif x % 2 == 1:
# 			r1 = rooms.get(((x - 1) / 2, y / 2))
# 			r2 = rooms.get(((x + 1) / 2, y / 2))
# 			if (r1 and r1.east) or (r2 and r2.west): print('|', end='')
# 			else: print('#', end='')
# 		elif y % 2 == 1:
# 			r1 = rooms.get((x / 2, (y - 1) / 2))
# 			r2 = rooms.get((x / 2, (y + 1) / 2))
# 			if (r1 and r1.north) or (r2 and r2.south): print('-', end='')
# 			else: print('#', end='')
# 		else:
# 			if (x, y) == (0, 0): print('x', end='')
# 			elif rooms.get((x / 2, y / 2)): print(' ', end='')
# 			else: print('#', end='')
# 	print()
# 	y -= 1
