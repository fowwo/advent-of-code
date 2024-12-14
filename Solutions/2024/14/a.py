# December 13th, 2024

import re

file = open(f"{__file__}/../input.txt", "r")

w, h = 101, 103
a = b = c = d = 0
for line in file:
	px, py, vx, vy = map(int, re.findall(r"\-?\d+", line))
	x, y = (px + vx * 100) % w, (py + vy * 100) % h
	if x < w // 2:
		if y < h // 2:
			a += 1
		elif y > h // 2:
			b += 1
	elif x > w // 2:
		if y < h // 2:
			c += 1
		elif y > h // 2:
			d += 1
file.close()

print(a * b * c * d)
