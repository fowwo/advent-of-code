# July 11th, 2022

input = 6042

def power(x, y):
	global input
	rackID = x + 10
	level = rackID * y
	level += input
	level *= rackID
	level = int(str(level // 100)[-1])
	level -= 5
	return level

m = 0
mc = None
for x in range(1, 299):
	for y in range(1, 299):
		level = 0
		for i in range(3):
			for j in range(3):
				level += power(x + i, y + j)
		if level > m:
			m = level
			mc = f"{x},{y}"
print(mc)
