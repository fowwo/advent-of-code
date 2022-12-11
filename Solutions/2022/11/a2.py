# December 10th, 2022 - My initial solution. This is done in a terrible way because I was trying to avoid parsing input.

monkeys = [
	[91, 66],
	[78, 97, 59],
	[57, 59, 97, 84, 72, 83, 56, 76],
	[81, 78, 70, 58, 84],
	[60],
	[57, 69, 63, 75, 62, 77, 72],
	[73, 66, 86, 79, 98, 87],
	[95, 89, 63, 67]
]

c = [ 0 for _ in range(len(monkeys)) ]
for round in range(20):
	for item in list(monkeys[0]):
		monkeys[0].remove(item)
		c[0] += 1
		item *= 13
		item //= 3
		if item % 19 == 0:
			monkeys[6].append(item)
		else:
			monkeys[2].append(item)
	for item in list(monkeys[1]):
		monkeys[1].remove(item)
		c[1] += 1
		item += 7
		item //= 3
		if item % 5 == 0:
			monkeys[0].append(item)
		else:
			monkeys[3].append(item)
	for item in list(monkeys[2]):
		monkeys[2].remove(item)
		c[2] += 1
		item += 6
		item //= 3
		if item % 11 == 0:
			monkeys[5].append(item)
		else:
			monkeys[7].append(item)
	for item in list(monkeys[3]):
		monkeys[3].remove(item)
		c[3] += 1
		item += 5
		item //= 3
		if item % 17 == 0:
			monkeys[6].append(item)
		else:
			monkeys[0].append(item)
	for item in list(monkeys[4]):
		monkeys[4].remove(item)
		c[4] += 1
		item += 8
		item //= 3
		if item % 7 == 0:
			monkeys[1].append(item)
		else:
			monkeys[3].append(item)
	for item in list(monkeys[5]):
		monkeys[5].remove(item)
		c[5] += 1
		item *= 5
		item //= 3
		if item % 13 == 0:
			monkeys[7].append(item)
		else:
			monkeys[4].append(item)
	for item in list(monkeys[6]):
		monkeys[6].remove(item)
		c[6] += 1
		item *= item
		item //= 3
		if item % 3 == 0:
			monkeys[5].append(item)
		else:
			monkeys[2].append(item)
	for item in list(monkeys[7]):
		monkeys[7].remove(item)
		c[7] += 1
		item += 2
		item //= 3
		if item % 2 == 0:
			monkeys[1].append(item)
		else:
			monkeys[4].append(item)

c = sorted(c, reverse=True)
print(c[0] * c[1])
