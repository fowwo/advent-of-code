# July 10th, 2022

class Light:
	def __init__(this, x, y, dx, dy):
		this.x = x
		this.y = y
		this.dx = dx
		this.dy = dy

file = open("input.txt", 'r')
lights = []
for line in file:
	line = line.strip()
	x = int(line[10:16])
	y = int(line[18:24])
	dx = int(line[36:38])
	dy = int(line[40:42])
	lights.append(Light(x, y, dx, dy))
file.close()

def distance():
	global lights
	dist = 0
	for light in lights:
		dist = max(dist, abs(lights[0].x - light.x) + abs(lights[0].y - light.y))
	return dist

# Minimize distance
a = distance()
b = a + 1
c = 0
while a < b:
	for light in lights:
		light.x += light.dx
		light.y += light.dy
	b = a
	a = distance()
	c += 1
print(c - 1)
