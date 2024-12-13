# December 12th, 2024

import re

file = open(f"{__file__}/../input.txt", "r")

t = 0
while (line := file.readline().strip()):
	ax, ay = map(int, re.findall(r"\d+", line))
	bx, by = map(int, re.findall(r"\d+", file.readline().strip()))
	px, py = map(int, re.findall(r"\d+", file.readline().strip()))
	file.readline()

	px += 10000000000000
	py += 10000000000000

	det = ax * by - ay * bx
	detA = px * by - py * bx
	detB = ax * py - ay * px

	if det != 0 and detA % det == 0 and detB % det == 0:
		a = detA // det
		b = detB // det
		t += 3 * a + b
file.close()

print(t)
