# December 9th, 2022

file = open(f"{__file__}/../input.txt", "r")

c = 0
for line in file:
	line = line.strip().split(" | ")

	ssd = dict()
	five = set()
	six = set()

	one = None
	four = None

	for digit in line[0].split(" "):
		s = "".join(sorted(c for c in digit))
		if len(s) == 2:
			ssd[s] = 1
			one = set(c for c in s)
		elif len(s) == 3:
			ssd[s] = 7
		elif len(s) == 4:
			ssd[s] = 4
			four = set(c for c in s)
		elif len(s) == 7:
			ssd[s] = 8
		elif len(s) == 5:
			five.add(s)
		else:
			six.add(s)

	# 3
	for digit in five:
		if set(c for c in digit).intersection(one) == one:
			ssd[digit] = 3
			five.remove(digit)
			break

	# 2, 5
	digit = five.pop()
	if len(set(c for c in digit).intersection(four)) == 3:
		ssd[digit] = 5
		ssd[five.pop()] = 2
	else:
		ssd[digit] = 2
		ssd[five.pop()] = 5
	
	# 9
	for digit in six:
		if set(c for c in digit).intersection(four) == four:
			ssd[digit] = 9
			six.remove(digit)
			break

	# 0, 6
	digit = six.pop()
	if set(c for c in digit).intersection(one) == one:
		ssd[digit] = 0
		ssd[six.pop()] = 6
	else:
		ssd[digit] = 6
		ssd[six.pop()] = 0

	s = 0
	for digit in line[1].split(" "):
		s *= 10
		s += ssd["".join(sorted(c for c in digit))]
	c += s
print(c)
