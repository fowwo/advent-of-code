# July 11th, 2022

class Cart:
	def __init__(this, row, column, direction):
		this.row = row
		this.column = column
		this.direction = direction
		this.rotation = 0
	def position(this):
		return (this.row, this.column)
	def straight(this):
		if this.direction == 0: # Up
			this.row -= 1
		elif this.direction == 1: # Right
			this.column += 1
		elif this.direction == 2: # Down
			this.row += 1
		else: # Left
			this.column -= 1
	def curve(this, track):
		if track == '/':
			if this.direction == 0 or this.direction == 2:
				this.direction += 1
			else:
				this.direction -= 1
		else:
			if this.direction == 0 or this.direction == 2:
				this.direction -= 1
			else:
				this.direction += 1
		this.direction %= 4
		this.straight()
	def intersection(this):
		if this.rotation == 0:
			this.direction -= 1
		elif this.rotation == 2:
			this.direction += 1
		this.rotation += 1
		this.direction %= 4
		this.rotation %= 3
		this.straight()

file = open("input.txt", 'r')
carts = []
straight = set()
curveForward = set()
curveBackward = set()
intersection = set()
i = 0
for line in file:
	line = line.strip('\n')
	for j in range(len(line)):
		if line[j] == '-' or line[j] == '|':
			straight.add((i, j))
		elif line[j] == '/':
			curveForward.add((i, j))
		elif line[j] == '\\':
			curveBackward.add((i, j))
		elif line[j] == '+':
			intersection.add((i, j))
		elif line[j] != ' ':
			if line[j] == '^':
				carts.append(Cart(i, j, 0))
			elif line[j] == '>':
				carts.append(Cart(i, j, 1))
			elif line[j] == 'v':
				carts.append(Cart(i, j, 2))
			elif line[j] == '<':
				carts.append(Cart(i, j, 3))
			straight.add((i, j))
	i += 1
file.close()

# Move
while True:
	carts = sorted(carts, key=lambda x: (x.row, x.column))
	for cart in carts:
		if cart.position() in straight:
			cart.straight()
		elif cart.position() in curveForward:
			cart.curve('/')
		elif cart.position() in curveBackward:
			cart.curve('\\')
		else:
			cart.intersection()
		for cart2 in carts:
			if cart != cart2 and cart.position() == cart2.position():
				print(f"{cart.column},{cart.row}")
				exit(0)
