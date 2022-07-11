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
while a < b:
	for light in lights:
		light.x += light.dx
		light.y += light.dy
	b = a
	a = distance()
lights = set((light.x - light.dx, light.y - light.dy) for light in lights)

# Display
minx = min(lights, key = lambda light: light[0])[0]
miny = min(lights, key = lambda light: light[1])[1]
maxx = max(lights, key = lambda light: light[0])[0]
maxy = max(lights, key = lambda light: light[1])[1]
for y in range(miny, maxy + 1):
	for x in range(minx, maxx + 1):
		if (x, y) in lights:
			print("#", end = "")
		else:
			print(" ", end = "")
	print()
