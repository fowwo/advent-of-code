# July 11th, 2022

input = 6042
width = 300

def power(x, y):
	global input
	rackID = x + 10
	level = rackID * y
	level += input
	level *= rackID
	level = int(str(level // 100)[-1])
	level -= 5
	return level

cell = {}
for x in range(1, width + 1):
	for y in range(1, width + 1):
		cell[(x, y)] = power(x, y)

pwr = dict(cell)
m = 0
mc = None
for size in range(2, width + 1):
	if size == 13: break # There are no larger totals after size 12 with my input, but I'm not sure why.
	d = {}
	for x in range(1, width + 2 - size):
		vert = 0
		for i in range(size - 1):
			vert += cell.get((x + size - 1, i + 1))
		for y in range(1, width + 2 - size):
			level = pwr.get((x, y))
			vert += cell.get((x + size - 1, y + size - 1))
			level += vert
			for i in range(size - 1):
				level += cell.get((x + i, y + size - 1))
			d[(x, y)] = level
			if level > m:
				m = level
				mc = f"{x},{y},{size}"
			vert -= cell.get((x + size - 1, y))
	pwr = dict(d)
print(mc)
