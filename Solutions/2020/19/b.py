# August 8th, 2022

file = open(f"{__file__}/../input.txt", "r")

rules = dict()
line = file.readline().strip()
while line != '':
	s = line.split(": ")
	r = s[1].split(" | ")
	l = []
	for x in r:
		x = x.split(' ')
		for i in range(len(x)):
			if x[i].isnumeric():
				x[i] = int(x[i])
			else:
				x[i] = x[i][1:-1]
		l.append(tuple(x))
	if len(l) == 1: l = l[0] # List to tuple
	if len(l) == 1: l = l[0] # Tuple to int/char
	rules[int(s[0])] = l
	line = file.readline().strip()
messages = set(line.strip() for line in file)
file.close()

def recursiveGen(string, rule):
	while isinstance(rules[rule], int):
		rule = rules[rule]
	if isinstance(rules[rule], str):
		return set([ string + rules[rule] ])
	elif isinstance(rules[rule], tuple):
		return genTuple(string, rules[rule])
	else:
		s = set()
		for x in rules[rule]:
			if isinstance(x, str):
				s.add(string + x)
			elif isinstance(x, int):
				s = s.union(recursiveGen(string, x))
			else:
				s = s.union(genTuple(string, x))
		return s
def genTuple(string, tuple):
	s = set([ string ])
	for x in tuple:
		if isinstance(x, str):
			s = set(y + x for y in s)
		else:
			n = recursiveGen("", x)
			s = set(y + z for y in s for z in n)
	return s

s = recursiveGen("", 0)

# Filter invalid prefixes and suffixes
c = 0
prefix = set(x[:8] for x in s)
infix = set(x[8:-8] for x in s)
suffix = set(x[-8:] for x in s)
for message in set(messages):
	messages.remove(message)
	if message in s:
		c += 1
	elif len(message) > 24 and message[:8] in prefix and message[-8:] in suffix:
		messages.add(message[8:-8])

set42 = infix # set42 = recursiveGen("", 42) (These sets are the same.)
set31 = recursiveGen("", 31) # set31 and set42 are disjoint.

# Count remaining valid messages
for message in messages:
	message = [ message[8 * i: 8 * i + 8] for i in range(len(message) // 8) ]
	a = 0
	b = len(message) - 1
	while a < len(message) and message[a] in set42: a += 1
	while b >= 0 and message[b] in set31: b -= 1
	if a == b + 1 and a >= len(message) - b:
		c += 1
print(c)
