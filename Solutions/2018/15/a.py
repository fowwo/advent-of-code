# July 26th, 2022

class Unit:
	def __init__(this, type, r, c):
		this.type = type
		this.r = r
		this.c = c
		this.hp = 200
		this.ap = 3
	def move(this):
		arr = [[(this.r, this.c)]]
		memo = set()
		memo.add((this.r, this.c))
		found = False
		while len(arr) and not found:
			new = []
			for x in arr:
				r = x[-1][0]
				c = x[-1][1]
				for y in [(r - 1, c), (r, c - 1), (r, c + 1), (r + 1, c)]:
					if y not in memo and y not in walls:
						unit = None
						for target in units:
							if (target.r, target.c) == y:
								unit = target
								break
						if not unit and not found:
							memo.add(y)
							new.append(x + [y])
						elif unit and unit.type != this.type:
							if not found:
								found = True
								new = []
							new.append(x + [y])
			arr = new
		if found:
			arr.sort(key=lambda x: x[-1])
			arr = [ x[1] for x in arr if x[-1] == arr[0][-1] ]
			arr.sort()
			this.r = arr[0][0]
			this.c = arr[0][1]
		return found
	def attack(this, that):
		that.hp -= this.ap
		return that.hp <= 0

file = open("input.txt", 'r')
walls = set()
units = []
r = 0
for line in file:
	c = 0
	for x in line.strip():
		if x == '#':
			walls.add((r, c))
		elif x != '.':
			units.append(Unit(x, r, c))
		c += 1
	r += 1
file.close()

round = 0
end = False
while not end:
	units.sort(key=lambda x: (x.r, x.c))
	i = 0
	while i < len(units):
		x = units[i]
		targets = [ y for y in units if x.type != y.type and abs(x.r - y.r) + abs(x.c - y.c) == 1 ]
		if len(targets) == 0:
			x.move()
			targets = [ y for y in units if x.type != y.type and abs(x.r - y.r) + abs(x.c - y.c) == 1 ]
		if len(targets):
			targets.sort(key=lambda x: (x.hp, x.r, x.c))
			dead = x.attack(targets[0])
			if dead:
				if units.index(targets[0]) < i: i -= 1
				units.remove(targets[0])
				end = True
				for j in range(1, len(units)):
					if units[0].type != units[j].type:
						end = False
						break
		i += 1
		if end: break
	if not end or i >= len(units): round += 1

hp = 0
for x in units: hp += x.hp
print(hp * round)
