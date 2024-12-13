# December 12th, 2024

import re

file = open(f"{__file__}/../input.txt", "r")

t = 0
while (line := file.readline().strip()):
	ax, ay = map(int, re.findall(r"\d+", line))
	bx, by = map(int, re.findall(r"\d+", file.readline().strip()))
	px, py = map(int, re.findall(r"\d+", file.readline().strip()))
	file.readline()

	def f(x, y, a, b, c):
		if x == px and y == py: return c
		if x > px or y > py or b == 100: return 401

		v = f(x + bx, y + by, a, b + 1, c + 1)
		if a != 100 and b == 0:
			v = min(v, f(x + ax, y + ay, a + 1, b, c + 3))
		return v

	x = f(0, 0, 0, 0, 0)
	if x != 401:
		t += x
file.close()

print(t)
